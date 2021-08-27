package com.sber.arm.controller;

import java.util.Random;

public class Generator {

    public String generateNumber(){
        final String alphabet = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 25;i++){
            result.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return result.toString();
    }
}
