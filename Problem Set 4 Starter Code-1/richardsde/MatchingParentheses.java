/*
 * Course: CS2852
 * Spring 2021
 * Problem Set 4
 * Name: Derek Richards
 * Created: 4/1/2021
 */
package richardsde;

/**
 * A class that only calls one method to see if a set of parentheses match up correctly.
 */
public class MatchingParentheses {
    /**
     * Checks a set of parentheses to see if they match or not.
     * @param s The string to be checked.
     * @return True if the parentheses match, false otherwise.
     */
    public static boolean doParenthesesMatch(String s){
        boolean valid = true;
        char mostRecentParen = 0;
        ArrayListStack<Character> parenStack = new ArrayListStack<>();
        for (int i = 0; i < s.length(); i++){
            char parentheses = s.charAt(i);
            if (!parenStack.isEmpty()){
                mostRecentParen = parenStack.peek();
            } else if (parenStack.isEmpty()){
                mostRecentParen = 0;
            }

            if (parentheses == '(' || parentheses == '{'
                    || parentheses == '[' || parentheses == '<'){
                parenStack.push(parentheses);
            }

            // We want to check that, if the most recent character is a closing parentheses,
            // it matches the most recently opened parentheses.
            switch (parentheses) {
                case ')':
                    if (mostRecentParen == '{' || mostRecentParen == '[' || mostRecentParen == '<'){
                        valid = false;
                    } else if (mostRecentParen == '('){
                        parenStack.pop();
                    } else if (parenStack.isEmpty()){
                        valid = false;
                    }
                    break;
                case '}':
                    if (mostRecentParen == '(' || mostRecentParen == '[' || mostRecentParen == '<'){
                        valid = false;
                    } else if (mostRecentParen == '{'){
                        parenStack.pop();
                    } else if (parenStack.isEmpty()){
                        valid = false;
                    }
                    break;
                case ']':
                    if (mostRecentParen == '(' || mostRecentParen == '{' || mostRecentParen == '<'){
                        valid = false;
                    } else if (mostRecentParen == '['){
                        parenStack.pop();
                    } else if (parenStack.isEmpty()){
                        valid = false;
                    }
                    break;
                case '>':
                    if (mostRecentParen == '(' || mostRecentParen == '{' || mostRecentParen == '['){
                        valid = false;
                    } else if (mostRecentParen == '<'){
                        parenStack.pop();
                    } else if (parenStack.isEmpty()){
                        valid = false;
                    }
                    break;
            }

        }

        if (!parenStack.isEmpty()){
            valid = false;
        }

        return valid;
    }
}
