package cn.dcube.ahead.base.reflect;

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
			if ("java.lang.Long".equals(field.getType().getName())) {
				value = Long.valueOf(value.toString());
			} else if ("java.lang.Integer".equals(field.getType().getName())) {
				value = Integer.valueOf(value.toString());
			} else if ("java.lang.Short".equals(field.getType().getName())) {
				value = Short.valueOf(value.toString());
			} else if ("java.lang.Byte".equals(field.getType().getName())) {
				value = Byte.valueOf(value.toString());
			} else if ("java.lang.Double".equals(field.getType().getName())) {
				value = Double.valueOf(value.toString());
			} else if ("java.lang.Float".equals(field.getType().getName())) {
				value = Float.valueOf(value.toString());
			} else if ("java.lang.String".equals(field.getType().getName())) {
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
