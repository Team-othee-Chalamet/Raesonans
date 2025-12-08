package com.example.backend.util;

public class Hasher {

    public static String hashPassword(String password) {
        StringBuilder sb = new StringBuilder();

        int modifier = password.length();
        int tempMod;

        for (char c : password.toCharArray()) {
            tempMod = (int) c;
            if (modifier > tempMod)
            {sb.append(modifier % tempMod);}
            else
            {sb.append(tempMod % modifier);}
            }

        return sb.toString();
    }
}
