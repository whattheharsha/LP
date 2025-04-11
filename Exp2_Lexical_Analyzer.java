import java.util.*;
import java.util.regex.*;
public class Exp2_Lexical_Analyzer {
    private static final String K = "int|float|char|if|else|while|return|system";
    private static final String I = "[a-zA-Z_][a-zA-Z0-9_]*";
    private static final String N = "\\d+\\.\\d+|\\d+";
    private static final String O = "[+\\-*/=<>!]+";
    private static final String S = "[(),;{}\\[\\]]";
    private static final String LIT = "\"[^\"]*\"";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to tokenize: ");
        String in = sc.nextLine();
        t(in);
    }
    
    public static void t(String in ) {
        String P = String.format("(%s)|(%s)|(%s)|(%s)|(%s)|(%s)", K, I, N, O, S, LIT);
        Pattern pattern = Pattern.compile(P);
        Matcher matcher = pattern.matcher(in);
        int tc = 0;
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                System.out.println("Keyword: " + matcher.group(1));
            } else if (matcher.group(2) != null) {
                System.out.println("Identifier: " + matcher.group(2));
            } else if (matcher.group(3) != null) {
                System.out.println("Number: " + matcher.group(3));
            } else if (matcher.group(4) != null) {
                System.out.println("Operator: " + matcher.group(4));
            } else if (matcher.group(5) != null) {
                System.out.println("Separator: " + matcher.group(5));
            } else if (matcher.group(6) != null) {
                System.out.println("String Literal: " + matcher.group(6));
            }
            tc++;
        }
        System.out.println("Total Tokens: " + tc);
    }
}