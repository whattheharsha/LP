import java.util.*;
public class Exp1_String_Ending_with_abc {
    private static final int q0 = 0,q1=1,q2=2,q3=3;
    public static boolean acceptsString(String input) {
        int state = q0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch(state) {
            case q0:if(c == 'a'){
                        state = q1;
                    } else {
                        state = q0;
                    }
                    break;
            case q1:if(c == 'b'){
                        state = q2;
                    }else if(c == 'a'){
                        state = q1;
                    } else {
                        state = q0;
                    }
                    break;
            case q2:if(c == 'c'){
                        state = q3;
                    } else if (c == 'a') {
                        state = q1;
                    } else {
                        state = q0;
                    }
                    break;
            case q3:if (c == 'a') {
                        state = q1;
                    }else{
                        state = q0;
                    }
                    break;
            }
        }
        return state == q3;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        if (acceptsString(input)) {
            System.out.println("ACCEPTED");
        } else {
            System.out.println("NOT ACCEPTED");
        }
        sc.close();
    }
}