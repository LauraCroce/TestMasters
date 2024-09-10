package homework1;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        String input, output;
        boolean capitalizeFirstLetter;
        char[] delimiters;
        CaseUtils caseUtils = new CaseUtils();

        input = "camel case";
        capitalizeFirstLetter = false;
        delimiters = null;
        output = caseUtils.convertToCamelCase(input, capitalizeFirstLetter, delimiters);

        System.out.println("test: " + input);
        System.out.println("output: " + output);
    }
}