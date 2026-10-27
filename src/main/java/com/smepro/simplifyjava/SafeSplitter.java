package com.smepro.simplifyjava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;



/**
 * SafeSplitter is a utility class that provides a safe and convenient way to split strings.
 * It handles null or empty input gracefully and supports method chaining.
 *
 * <p>Example usage:
 * <pre>{@code
 *     String part = SafeSplitter.split("A,B,C", ",").get(1); // returns "B"
 * }</pre>
 */
public class SafeSplitter {

    private List<String> split;

    /**
     * Constructs a SafeSplitter instance by splitting the input string using the specified delimiter.
     * If the input is null or empty, an empty list is used.
     *
     * @param input     the input string to split
     * @param delimiter the delimiter used for splitting
     */
    private SafeSplitter(String input, String delimiter) {
        if (input == null || input.isEmpty()) {
            split = Collections.emptyList();
        } else {
            split = Arrays.asList(input.split(Pattern.quote(delimiter), -1));
        }
    }

    /**
     * Static factory method to create a SafeSplitter instance.
     *
     * @param input     the input string to split
     * @param delimiter the delimiter used for splitting
     * @return a SafeSplitter instance
     */
    public static SafeSplitter split(String input, String delimiter) {
        return new SafeSplitter(input, delimiter);
    }

    /**
     * Returns the element at the specified index in the split result.
     * If the index is out of bounds, an empty string is returned.
     *
     * @param index the index of the element to return
     * @return the string at the specified index, or an empty string if out of bounds
     */
    public String get(int index) {
        return index < split.size() ? split.get(index) : "";
    }

    /**
     * Returns the list of strings obtained by splitting the input.
     *
     * @return the list of split strings
     */
    public List<String> toList() {
        return split;
    }
}
