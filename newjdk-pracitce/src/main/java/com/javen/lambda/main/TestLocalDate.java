package com.javen.lambda.main;

import java.time.Instant;
import java.time.LocalDateTime;

public class TestLocalDate {
    public static void main(String[] args) {
        System.out.println(Instant.now());
        System.out.println(Instant.EPOCH);
        System.out.println(LocalDateTime.now().minusDays(1).getDayOfWeek());
    }
}
