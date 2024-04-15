package cauzy.familytasklist.util.FamilyPeople;

import java.lang.reflect.Field;

public class ValidationFamilyPeople {
	
	public static boolean validateStringSizeMin(Object obj) {
        if (obj == null) {
            return false;
        }

        Class<?> clazz = obj.getClass();
        clazz = clazz.getSuperclass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            StringSizeMin annotation = field.getAnnotation(StringSizeMin.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value instanceof String) {
                        String stringValue = (String) value;
                        if (stringValue.length() <= annotation.value()) {
                            return false; // Falha na validação
                        }
                    } else {
                        throw new IllegalArgumentException("O campo " + field.getName() + " não é uma String.");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Trata o erro adequadamente
                }
            }
        }
        return true; // Todos os campos validados com sucesso
    }
	
	public static boolean ValidationMinAge(Object obj) {
        if (obj == null) {
            return false;
        }

        Class<?> clazz = obj.getClass();
        clazz = clazz.getSuperclass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            MinAge annotation = field.getAnnotation(MinAge.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value instanceof Integer) {
                        Integer integerValue = (Integer) value;
                        if (integerValue < annotation.value()) {
                            return false; // Falha na validação
                        }
                    } else {
                        throw new IllegalArgumentException("O campo " + field.getName() + " não é uma Integer.");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Trata o erro adequadamente
                }
            }
        }
        return true; // Todos os campos validados com sucesso
    }

}
