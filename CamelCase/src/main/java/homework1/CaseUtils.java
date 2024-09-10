package homework1;

import java.util.HashSet;
import java.util.Set;

public class CaseUtils {

    /**
     * Converts delimiter-separated words into camelCase, with each word starting
     * with an uppercase character and the rest in lowercase.
     *
     * Delimiters separate the words, and the first character after a delimiter is capitalized.
     * The first letter may or may not be capitalized based on the capitalizeFirstLetter flag.
     *
     * Returns {@code null} if the input is {@code null} and an empty string ("") if only delimiters are present.
     * Capitalization uses Unicode title case, equivalent to standard upper case.
     *
     * @param input  the string to convert, may be {@code null}
     * @param capitalizeFirstLetter if true, the first letter is capitalized
     * @param delimiters characters separating the words, {@code null} or empty array means whitespace
     * @return camelCase string, or {@code null} if the input is null
     */
    public String convertToCamelCase(String input, final boolean capitalizeFirstLetter, final char... delimiters) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        input = input.toLowerCase();

        final int inputLength = input.length();
        final int[] resultCodePoints = new int[inputLength];
        int outputOffset = 0;

        final Set<Integer> delimiterSet = createDelimiterSet(delimiters);
        boolean capitalizeNext = false;

        if(capitalizeFirstLetter){
            capitalizeNext = true;
        }

        for (int i = 0; i < inputLength; ) {
            final int codePoint = input.codePointAt(i);

            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = true;
               if (outputOffset == 0) {
                   capitalizeNext = false;
               }
               i += Character.charCount(codePoint);
            } else if (capitalizeNext) {
                final int titleCaseCodePoint = Character.toTitleCase(codePoint);
                resultCodePoints[outputOffset++] = titleCaseCodePoint;
                i += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
            } else {
                resultCodePoints[outputOffset++] = codePoint;
                i += Character.charCount(codePoint);
            }
        }

        if (outputOffset != 0){
            return new String(resultCodePoints, 0, outputOffset);
        }

    return "";
    }

    /**
     * Converts an array of delimiters to a hash set of code points. Code point of space(32) is added
     * as the default value. The generated hash set provides O(1) lookup time.
     *
     * @param delimiters  set of characters to determine capitalization, null means whitespace
     * @return Set
     */
    private static Set<Integer> createDelimiterSet(final char[] delimiters) {

        Set<Integer> delimiterHashSet = new HashSet<Integer>();
        delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
        if (delimiters == null || delimiters.length == 0) {
            return delimiterHashSet;
        }

        for (int index = 0; index < delimiters.length; index++) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }

        return delimiterHashSet;
    }

    /**
     * {@code CaseUtils} instances should not be constructed directly;
     * the class should be used statically.
     *
     * The constructor is public to support tools requiring a JavaBean instance.
     */
    public CaseUtils() {

    }
}
