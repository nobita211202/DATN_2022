package org.datn.utils;

public class Validate {
    public static boolean isEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
    public static boolean isPhone(String phone) {
        String regex = "^[0-9]{10}$";
        return phone.matches(regex);
    }
    public static boolean isPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        return password.matches(regex);
    }
    public static boolean isName(String name) {
        String regex = "^[a-zA-Z\\s]*$";
        return name.matches(regex);
    }
    public static boolean isNotNumber(String number) {
        String regex = "^[a-zA-Z\\s]*$";
        return number.matches(regex);
    }
    public static boolean isNull(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static boolean isMatched(String str1, String str2) {
        return str1.equals(str2);
    }

}
