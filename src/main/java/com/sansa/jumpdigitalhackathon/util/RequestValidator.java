package com.sansa.jumpdigitalhackathon.util;

import java.lang.reflect.Field;

public class RequestValidator {

    public static boolean validateNotNullFields(Object obj) {
        // Get Request Class
        Class<?> clazz = obj.getClass();

        // Obtains all fields
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // Makes all fields accesible
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                // If at least one field is null, returns false
                if (value == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // If there are no null fields, returns true
        return true;
    }
}
