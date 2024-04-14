package cauzy.familytasklist.util.Task;

import java.lang.reflect.Field;

import cauzy.familytasklist.model.enums.TaskLevel;

public class ValidationTask {

	public static boolean ValidationAssinment(Object obj, TaskLevel taskLevel) {

		if (obj == null) {
			return false;
		}

		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			DificultLevel annotation = field.getDeclaredAnnotation(DificultLevel.class);
			if (annotation != null) {
				field.setAccessible(true);
				try {
					Object value = field.get(obj);
					if (value instanceof TaskLevel) {
						TaskLevel levelValue = (TaskLevel) value;

						switch (levelValue) {
						case EASY:
							if (levelValue != taskLevel) {
								return false;
							}
							;
							break;
						case MEDIUM:
							if (levelValue == TaskLevel.HARD) {
								return false;
							}
							;
						default:
							break;
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace(); // Trata o erro adequadamente
				}
			}
		}
		return true;
	}

}
