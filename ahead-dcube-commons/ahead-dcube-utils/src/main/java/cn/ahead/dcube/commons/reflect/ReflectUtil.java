package cn.ahead.dcube.commons.reflect;

import java.lang.reflect.Field;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ReflectUtil {

	public static void setFieldValue(Object event, String filedName, Object filedValue) {
		Field field;
		try {
			field = event.getClass().getDeclaredField(filedName);
			field.setAccessible(true);
			Object value = filedValue;
			String fieldType = field.getType().getTypeName();
			if ("java.lang.Long".equals(fieldType) || "long".equals(fieldType)) {
				value = Long.valueOf(value.toString());
			} else if ("java.lang.Integer".equals(fieldType) || "int".equals(fieldType)) {
				value = Integer.valueOf(value.toString());
			} else if ("java.lang.Short".equals(fieldType) || "short".equals(fieldType)) {
				value = Short.valueOf(value.toString());
			} else if ("java.lang.Byte".equals(fieldType) || "byte".equals(fieldType)) {
				value = Byte.valueOf(value.toString());
			} else if ("java.lang.Double".equals(fieldType) || "double".equals(fieldType)) {
				value = Double.valueOf(value.toString());
			} else if ("java.lang.Float".equals(fieldType) || "float".equals(fieldType)) {
				value = Float.valueOf(value.toString());
			} else if ("java.lang.String".equals(fieldType) || "String".equals(fieldType)) {
				value = value.toString();
			}
			field.set(event, value);
		} catch (NoSuchFieldException e) {
			if (event.getClass().getSuperclass().getName().startsWith("cn.dcube")) {
				setFieldValue(event.getClass().getSuperclass(), filedName, filedValue);
			}
		} catch (Exception e) {
			log.warn("", e);
		}
	}
	
}
