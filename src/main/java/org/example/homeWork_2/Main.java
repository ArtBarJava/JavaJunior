package org.example.homeWork_2;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    /**
     * В существующий класс ObjectCreator добавить поддержку аннотации RandomDate (по аналогии с Random):
     * 1. Аннотация должна обрабатываться только над полями типа java.util.Date
     * 2. Проверить, что min < max
     * 3. В поле, помеченной аннотацией, нужно вставлять рандомную дату,
     * UNIX-время которой находится в диапазоне [min, max)
     *
     * 4. *** Добавить поддержку для типов Instant, ...
     * 5. *** Добавить атрибут Zone и поддержку для типов LocalDate, LocalDateTime
     */

    /**
     * Примечание:
     * Unix-время - количество милисекунд, прошедших с 1 января 1970 года по UTC-0.
     */

    // FIXME: Заставить аннотацию ставится только над полями
    public static void main(String[] args) {
        Person randomPerson = ObjectCreator.createObj(Person.class);
        System.out.println("Поле Date (но min > max) => " + randomPerson.date);
        System.out.println("Поле String => " + randomPerson.date1);
        System.out.println("Поле Date => " + randomPerson.date2);
        System.out.println("Поле Instant => " + randomPerson.date3);
        System.out.println("Поле LocalDate => " + randomPerson.date4);
        System.out.println("Поле LocalDateTime => " + randomPerson.date5);
    }

    public static class Person {
        @RandomDate(min = 1725689600000L, max = 1715689600000L)
        private Date date;
        @RandomDate
        private String date1;
        @RandomDate
        private Date date2;
        @RandomDate
        private Instant date3;
        @RandomDate
        private LocalDate date4;
        @RandomDate
        private LocalDateTime date5;
    }
}

