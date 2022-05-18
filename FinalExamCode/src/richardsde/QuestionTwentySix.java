package richardsde;

public class QuestionTwentySix {

    public boolean doParenthesesMatch(String message){
        boolean match = true;

        if (message.contains("(") && !message.contains(")")){
            match = false;
        } else if (message.contains(")") && !message.contains("(")){
            match = false;
        } else if (!message.contains("(") && !message.contains(")")){
            match = true;
        } else {
            int startIndex = message.indexOf("(");
            int endIndex = message.lastIndexOf(")");
            match = doParenthesesMatch(message.substring(startIndex + 1, endIndex));
        }

        return match;
    }
}
