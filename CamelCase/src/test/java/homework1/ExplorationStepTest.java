package homework1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ExplorationStepTest {
    CaseUtils caseUtils = new CaseUtils();
    String input;
    boolean capitalizeFirstLetter;
    char[] delimiters;


    @Test
    void testSimpleCase() {
        input = "integrazione e test di sistemi software";
        capitalizeFirstLetter = false;
        assertEquals("integrazioneETestDiSistemiSoftware", caseUtils.convertToCamelCase(input, capitalizeFirstLetter));
    }

    @Test
    void testCapitalizeFirstLetter() {
        input = "integrazione e test di sistemi software";
        capitalizeFirstLetter = true;
        assertEquals("IntegrazioneETestDiSistemiSoftware", caseUtils.convertToCamelCase(input, capitalizeFirstLetter));
    }

    @Test
    void testSimpleCaseWithoutDelimiters(){
        input = "stringasenzaspazi";
        capitalizeFirstLetter = false;
        assertEquals("stringasenzaspazi", caseUtils.convertToCamelCase(input, capitalizeFirstLetter));
    }

    @Test
    void testSimpleCaseWithDelimiter(){
        input = "stringasenzaspazi";
        capitalizeFirstLetter = false;
        delimiters = new char[]{'a'};
        assertEquals("stringSenzSpZi", caseUtils.convertToCamelCase(input, capitalizeFirstLetter, delimiters));
    }
}