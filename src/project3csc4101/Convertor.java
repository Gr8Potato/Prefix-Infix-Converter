// DISCLAIMER: I DO NOT SUPPORT PEOPLE PLAGIARIZING MY CODE. I DO NOT TAKE RESPONSIBILITY FOR THE UNLAWFUL ACTIONS OF OTHERS.

package project3csc4101;

/**
 * This program converts prefix expressions to infix expressions and evaluates
 * it. This program supports addition, subtraction, multiplication, and
 * division.
 */
import java.util.Stack;

public class Convertor {

    private static class InvalidCharacterException extends Exception {

        public InvalidCharacterException(String errorMessage) {
            System.out.println(errorMessage);
        }
    }

    private static class PrefixParsingException extends Exception {

        public PrefixParsingException(String errorMessage) {
            System.out.println(errorMessage);
        }
    }

    /**
     * This method converts a prefix expression to infix and evaluates it. The
     * result is then printed to the console
     *
     * @param Expression - Prefix string to be evaluated and converted to infix
     */
    private static void prefixToInfix(String Expression) throws Exception {
        Stack<String> str_stack = new Stack<>(); //for prefix to infix conversion
        Stack<Integer> int_stack = new Stack<>(); //for mathematical evaluation
        String[] str_array = Expression.split(" ");

        {
            int operator_count = 0;
            int operand_count = 0;
            for (String str_array1 : str_array) {
                if (is_operator(str_array1)) {
                    operator_count++;
                } else if (isNumeric(str_array1)) {
                    operand_count++;
                } else {
                    throw new InvalidCharacterException("EXPRESSION CONSISTS OF TOKEN(S) THAT ARE NOT NUMERICAL OPERANDS OR SUPPORTED OPERATORS (+, *, -, /)");
                }
            }

            if (operator_count != operand_count - 1) {
                throw new PrefixParsingException("NUMBER OF OPERATORS MUST ALWAYS BE ONE LESS THAN THE NUMBER OF OPERANDS");

            }
        }

        if (!is_operator(str_array[0])) {
            throw new PrefixParsingException("FIRST TOKEN CANNOT BE AN OPERAND IN PREFIX NOTATION");
        }
        if (is_operator(str_array[str_array.length - 1])) {
            throw new PrefixParsingException("LAST TOKEN CANNOT BE AN OPERATOR IN PREFIX NOTATION");

        }

        for (int i = str_array.length - 1; i >= 0; i--) {
            if (is_operator(str_array[i])) {
                str_stack.push("(" + str_stack.pop() + " " + str_array[i] + " " + str_stack.pop() + ")");
                switch (str_array[i]) {
                    case "+" ->
                        int_stack.push(int_stack.pop() + int_stack.pop());
                    case "*" ->
                        int_stack.push(int_stack.pop() * int_stack.pop());
                    case "-" ->
                        int_stack.push(int_stack.pop() - int_stack.pop());
                    case "/" ->
                        int_stack.push(int_stack.pop() / int_stack.pop());
                }
            } else if (isNumeric(str_array[i])) {
                str_stack.push(str_array[i]);
                int_stack.push(Integer.parseInt(str_array[i]));
            }
        }
        System.out.println(Expression + " --> " + str_stack.pop() + " = " + int_stack.pop());
    }

    /**
     * This method determines whether a character is an operator or not. This
     * program supports addition, subtraction, multiplication, and division.
     *
     * @param str - String being evaluated
     * @return boolean that's true if string is an operator, false otherwise
     */
    private static boolean is_operator(String str) {
        switch (str) {
            case "+", "*", "-", "/" -> {
                return true;
            }
        }
        return false;
    }

    /**
     * This method determines whether a String is an number or not.
     *
     * @param strNum - String being evaluated
     * @return boolean that's true if string is an number, false otherwise
     */
    public static boolean isNumeric(String strNum) {
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
