package cauzy.familytasklist.util.Task;

import java.lang.reflect.Field;

import cauzy.familytasklist.model.enums.AgeGroup;
import cauzy.familytasklist.model.enums.TaskLevel;

public class ValidationTask {

	public static boolean ValidationAssinment(Object obj, AgeGroup ageGroup) {

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
						case MEDIUM:
							if (ageGroup == AgeGroup.CHILD) {
								return false;
							}
							;
							break;
						case HARD	:
							if (ageGroup == AgeGroup.CHILD) {
								return false;
							}
							if (ageGroup == AgeGroup.TEEN) {
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
