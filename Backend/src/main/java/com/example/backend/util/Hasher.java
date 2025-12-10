package com.example.backend.util;

public class Hasher {

    public static void main(String[] args) {
        int i = 0;
        String toPrint = "dinmor";
        while (i < 5) {
            toPrint = hashPassword(toPrint);
            System.out.println(toPrint); i++;}
    }

    public static String hashPassword(String password) {
        StringBuilder sb = new StringBuilder();

        for (char c : password.toCharArray()) {

            // int value of c times modulo of int value of c to password length
            int intToAppend = (int) c * ((int) c % password.length());

            // if intToAppend is even, part of 7 or 17 table, add a number based on modulo
            if (intToAppend % 2 == 0 || intToAppend % 7 == 0 || intToAppend % 17 == 0) {
                sb.append(intToAppend % 31);
            }

            // Append the char
            char toAppend = (char) intToAppend;
            sb.append(toAppend);
        }

        return sb.toString();
    }
}
