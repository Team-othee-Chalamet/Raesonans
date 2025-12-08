package com.example.backend.util;

public class Hasher {

    public static void main(String[] args) {
        System.out.println(hashPassword("Hejmeddig"));
    }

    public static String hashPassword(String password) {
        StringBuilder sb = new StringBuilder();

        int modifier = password.length();
        int tempMod;

        for (char c : password.toCharArray()) {
            tempMod = (int) c;
            if (modifier > tempMod)
            {sb.append(c % tempMod);}
            else
            {sb.append(c % modifier);}

            modifier = tempMod;
            }

        return sb.toString();
    }
}
