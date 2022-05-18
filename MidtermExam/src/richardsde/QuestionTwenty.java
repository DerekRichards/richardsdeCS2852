package richardsde;

import java.util.Stack;

public class QuestionTwenty {

    public boolean MatchingSlashes(String line){
        boolean valid = true;
        ArrayListStack<Character> stack = new ArrayListStack<>();

        for (int i = 0; i < line.length(); i++){
            char slash = line.charAt(i);

            if (slash == '/'){
                stack.push(slash);
            } else if (slash == '\\'){
                if (stack.isEmpty()){
                    valid = false;
                } else{
                    stack.pop();
                }
            }

        }

        if (stack.isEmpty()){
            valid = false;
        }

        return valid;
    }
}
