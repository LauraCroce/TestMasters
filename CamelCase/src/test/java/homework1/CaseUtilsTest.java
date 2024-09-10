package homework1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di unit tests per la classe CaseUtils.
 */
class CaseUtilsTest {
    CaseUtils caseUtils = new CaseUtils();

    @ParameterizedTest
    @MethodSource("toCamelCaseArgs")
    void testToCamelCase(String expected, String str, final boolean capitalizeFirstLetter, final char... delimiters) {
        assertEquals(expected, caseUtils.convertToCamelCase(str, capitalizeFirstLetter, delimiters));
    }

    static Stream<Arguments> toCamelCaseArgs() {
        return Stream.of(

                // T1: empty string, capitalizeFirstLetter = false, 1 delimiter
                Arguments.of("", "", false, new char[]{'a'}),
                // T2: null string, capitalizeFirstLetter = false, 1 delimiter
                Arguments.of(null, null, false, new char[]{'a'}),
                // T3: string length > 1, capitalizeFirstLetter = true, 1 delimiter
                Arguments.of("CMelCSeConvention", "camel case convention", true, new char[]{'a'}),
                // T4: string length > 1, capitalizeFirstLetter = false, null delimiters array
                Arguments.of("camelCaseConvention", "camel case convention", false, null),
                // T5: string length > 1, capitalizeFirstLetter = false, empty delimiters array
                Arguments.of("camelCaseConvention", "camel case convention", false, new char[]{}),

                // T6: string length 1, capitalizeFirstLetter = false, 1 delimiter
                // (Delimiter is not present in the string)
                Arguments.of("c", "c", false, new char[]{'a'}),
                // T7: string length 1, capitalizeFirstLetter = false, 1 delimiter
                // (Delimiter is present in the string)
                Arguments.of("", "c", false, new char[]{'c'}), // failed

                // T8: string length 1, capitalizeFirstLetter = false, > 1 delimiters
                // (Delimiters are not present in the string)
                Arguments.of("c", "c", false, new char[]{'a', 'b'}),
                // T9: string length 1, capitalizeFirstLetter = false, > 1 delimiters
                // (One delimiter is present in the string and the others are not)
                Arguments.of("", "c", false, new char[]{'a', 'c'}), // failed
                // T10: string length 1, capitalizeFirstLetter = false, > 1 delimiters
                // (All delimiters are present in the string)
                Arguments.of("", "c", false, new char[]{'c', 'c'}), // failed

                // T11: string length 1, capitalizeFirstLetter = true, empty delimiters array
                Arguments.of("C", "c", true, new char[]{}),

                // T12: string length > 1, capitalizeFirstLetter = false, > 1 delimiters
                Arguments.of("caElCaseConEntion", "camel case convention", false, new char[]{'m', 'v'}),

                // CREATIVITY TEST CASES
                // Input string is already camel case formatted,
                Arguments.of("camelcaseconvention", "camelCaseConvention", false, null),
                // Input string contains special characters
                Arguments.of("uneÉcole", "une école", false, null),
                Arguments.of("uneCole", "une école", false, new char[]{'é'}),
                // Input string contains numbers
                Arguments.of("junit5TestingFramework", "JUnit5 Testing Framework", false, null),
                Arguments.of("junitTestingFramework", "JUnit5 Testing Framework", false, new char[]{'5'})
        );
    }
}

