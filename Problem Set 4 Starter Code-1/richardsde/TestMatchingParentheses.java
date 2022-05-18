/*
 * Course: CS2852
 * Spring 2021
 * Problem Set 4
 * Name: Derek Richards
 * Created: 4/1/2021
 */
package richardsde;

/**
 * Test class that tests the MatchingParentheses class.
 */
public class TestMatchingParentheses {
    /**
     * Takes in a string and prints out if its valid (which it should be) or invalid.
     * @param s The string to be tested.
     */
    public static void expectValid(String s) {
        if(MatchingParentheses.doParenthesesMatch(s)) {
            System.out.println("'" + s + "' is valid as expected!");
        } else {
            System.out.println("'" + s + "' is not valid as expected!");
        }
    }

    /**
     * Takes in a string and prints out if its invalid (which it should be) or not.
     * @param s The string to be tested.
     */
    public static void expectInvalid(String s) {
        if(!MatchingParentheses.doParenthesesMatch(s)) {
            System.out.println("'" + s + "' is invalid as expected!");
        } else {
            System.out.println("'" + s + "' is not invalid as expected!");
        }
    }

    public static void main(String[] args) {
        expectValid("");
        expectValid("()");
        expectValid("[]");
        expectValid("{}");
        expectValid("<>");
        expectValid("([])");
        expectValid("{<>}");
        expectValid("([])");
        expectValid("{<((())[][][])>}");

        expectInvalid("(");
        expectInvalid("()(");
        expectInvalid(")");
        expectInvalid(")()");
        expectInvalid("())(");

        expectValid("r(a(c[e]c)a)r");

    }
}
