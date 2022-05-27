package cn.dcube.ahead.commons.proto.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;

/**
 * Protobuf工具类
 */
@Slf4j
public class ProtoBufUtils {

	/** 避免每次序列化都重新申请Buffer空间 */
	private static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

	/** 缓存Schema */
	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

	private static Objenesis objenesis = new ObjenesisStd(true);

	private static <T> Schema<T> getSchema(Class<T> cls) {
		Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
		if (schema == null) {
			schema = RuntimeSchema.createFrom(cls);
			if (schema != null) {
				cachedSchema.put(cls, schema);
			}
		}
		return schema;
	}

	/**
	 * 序列化
	 * 
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public static <T> byte[] serializer(T obj) {
		Class<T> cls = (Class<T>) obj.getClass();
		try {
			Schema<T> schema = getSchema(cls);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			log.error("protobuf序列化失败");
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T deserializer(byte[] bytes, Class<T> clazz) {
		try {
			T message = (T) objenesis.newInstance(clazz);
			Schema<T> schema = getSchema(clazz);
			ProtostuffIOUtil.mergeFrom(bytes, message, schema);
			return message;
		} catch (Exception e) {
			log.error("protobuf反序列化失败");
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

}
