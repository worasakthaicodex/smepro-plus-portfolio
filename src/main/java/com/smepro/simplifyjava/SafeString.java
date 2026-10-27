package com.smepro.simplifyjava;

import java.util.Arrays;



/**
 * SafeString is a utility class that provides helper methods
 * to safely validate and check string values, especially for null,
 * blank, or special placeholder values like "0" or "-".
 */
public class SafeString {

    /**
     * Checks whether all provided strings have valid values.
     * A string is considered valid if it is not null, not blank,
     * and not equal to "0" or "-".
     *
     * @param strings the varargs array of strings to check
     * @return true if all strings have valid values; false otherwise
     */
    public static boolean checkAllHaveValidValue(String... strings) {
        return Arrays.stream(strings)
            .allMatch(str -> str != null && !str.trim().isEmpty()
                             && !str.trim().equals("0")
                             && !str.trim().equals("-"));
    }

    /**
     * Checks whether a string has value.
     * A string is considered to have value if it is not null,
     * not blank, and not equal to "-".
     *
     * @param value the string to check
     * @return true if the string has value; false otherwise
     */
    public static boolean haveValue(String value) {
        if (value == null) return false;
        if (value.equals("-")) return false;
        if (value.isBlank()) return false;
        return true;
    }

    /**
     * Checks whether a string has a meaningful non-placeholder value.
     * A string is considered to have value if it is not null, not blank,
     * and not equal to "0" or "-".
     *
     * @param value the string to check
     * @return true if the string has a meaningful value; false otherwise
     */
    public static boolean haveValueString(String value) {
        if (value == null) return false;
        if (value.equals("0")) return false;
        if (value.equals("-")) return false;
        if (value.isBlank()) return false;
        return true;
    }
    
    /**
     * Returns true if the input string is non-null and non-blank,
     * so it can be used as a filter predicate to skip null or blank values.
     *
     * @param value the input string to check
     * @return true if value is non-null and non-blank; false otherwise
     */
    public static String skipNull(String value) {
        if( value == null) {return  "";}
         return value;
    }
    
    
}

