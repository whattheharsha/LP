import java.util.*;
public class Exp4_Left_Factoring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of productions:");
        int n = sc.nextInt();
        sc.nextLine();
        String[] productions = new String[n];
        System.out.println("Enter the Productions:");
        for(int i = 0; i < n; i++) {
            productions[i] = sc.nextLine();
        }
        eliminateleftfact(productions);
    }
    
    private static void eliminateleftfact(String[] productions) {
        boolean leftfactored = false;
        for (String production : productions) {
            String[] parts = production.split("->");
            String lhs = parts[0].trim();
            String[] rhs = parts[1].split("\\|");
            String prefix = findcommonprefix(rhs);
            if (!prefix.isEmpty()) {
                leftfactored = true;
                System.out.println(lhs + "->" + prefix + lhs + "'");
                List<String> newrhs = new ArrayList<>();
                for (String r : rhs) {
                    if (r.startsWith(prefix)) {
                        String suffix = r.substring(prefix.length()).trim();
                        if (suffix.isEmpty()) {
                            suffix = "";
                        }
                        newrhs.add(suffix);
                    } else {
                        newrhs.add(r);  
                    }
                }
                System.out.println(lhs + "'->" + String.join("|", newrhs));
            }
        }
        if (!leftfactored) {
            System.out.println("Given productions do not have left factoring");
        }
    }
    
    private static String findcommonprefix(String[] rhs) {
        String prefix = rhs[0];
        for (int i = 1; i < rhs.length; i++) {
            while (rhs[i].indexOf(prefix) != 0) {  
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}