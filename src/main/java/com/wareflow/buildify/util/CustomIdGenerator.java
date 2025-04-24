package com.wareflow.buildify.util;

import com.wareflow.buildify.constants.IdPrefix;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CustomIdGenerator {

    public static String generateId(IdPrefix prefix) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String randomStr = getRandomAlphaNumeric(6);
        return prefix.name() + "-" + date + "-" + randomStr;
    }

    private static String getRandomAlphaNumeric(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 테스트용 main
    public static void main(String[] args) {
        System.out.println(generateId(IdPrefix.USR)); // 고객
        System.out.println(generateId(IdPrefix.ADM)); // 관리자
        System.out.println(generateId(IdPrefix.PRD)); // 상품
        System.out.println(generateId(IdPrefix.CAT)); // 상품
    }
}
