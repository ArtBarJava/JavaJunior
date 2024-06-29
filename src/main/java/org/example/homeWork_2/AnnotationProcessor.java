package org.example.homeWork_2;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class AnnotationProcessor {
    public static void processAnnotation(Object obj) {

        Random random = new java.util.Random();
        Class<?> objClass = obj.getClass();
        for (Field field : objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(RandomDate.class)) {
                RandomDate annotation = field.getAnnotation(RandomDate.class);
                long min = annotation.min();
                long max = annotation.max();

                if(min < max) {
                    Date date = new Date(random.nextLong(min, max));

                    try {
                        field.setAccessible(true);
                        if (field.getType().isAssignableFrom(Date.class)) {
                            field.set(obj, date);
                        } else if (field.getType().isAssignableFrom(Instant.class)) {
                            field.set(obj, date.toInstant());
                        } else if (field.getType().isAssignableFrom(LocalDate.class)) {
                            field.set(obj, date.toInstant().atZone(ZoneId.of(annotation.zone())).toLocalDate());
                        } else if (field.getType().isAssignableFrom(LocalDateTime.class)) {
                            field.set(obj, date.toInstant().atZone(ZoneId.of(annotation.zone())).toLocalDateTime());
                        }

                    } catch (IllegalAccessException e) {
                        System.err.println("Не удалось вставить значение в поле: " + e.getMessage());
                    }
                } else {
                    System.out.println("Минимальная дата должна быть меньше максимальной");
                }
            }
        }
    }
}