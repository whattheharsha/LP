import java.util.Scanner;

public class Exp3_Eliminate_Left_Recursion {
    public static void eliminateLeftRecursion(String production){
        String[] parts = production.split("->");
        char nonTerminal = parts[0].charAt(0);
        String[] choices = parts[1].split("/");
        System.out.println("GRAMMAR: "+production);
        if(choices[0].startsWith("" +nonTerminal)){
            String beta = choices[0].substring(1);
            System.out.println(nonTerminal + "is not left recursive.");
            System.out.println(nonTerminal + "->" +choices[1].trim() +nonTerminal+"'");
            System.out.println(nonTerminal + "'->" +beta+nonTerminal+"'epsilon");
        }
        else{
            System.out.println(nonTerminal+"is not left recursive.");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of productions: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter grammar as A->Aa/b: ");
        for(int i=0;i<n;i++){
            String production = sc.nextLine().trim();
            eliminateLeftRecursion(production);
        }
        sc.close();
    }
}