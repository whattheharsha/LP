import java.io.*;

public class Fstnflw {

    static char ntermnl[], termnl[];
    static int ntlen, tlen;
    static String grmr[][], fst[], flw[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the non-terminals:");
        ntermnl = br.readLine().toCharArray();
        ntlen = ntermnl.length;

        System.out.println("Enter the terminals:");
        termnl = br.readLine().toCharArray();
        tlen = termnl.length;

        System.out.println("Specify the grammar (Enter 9 for epsilon production):");
        grmr = new String[ntlen][];
        for (int i = 0; i < ntlen; i++) {
            System.out.println("Enter the number of productions for " + ntermnl[i] + ":");
            int n = Integer.parseInt(br.readLine());
            grmr[i] = new String[n];
            System.out.println("Enter the productions:");
            for (int j = 0; j < n; j++)
                grmr[i][j] = br.readLine();
        }

        fst = new String[ntlen];

        for (int i = 0; i < ntlen; i++) fst[i] = first(i);
            System.out.println("First set:");
        for (int i = 0; i < ntlen; i++) 
        	System.out.println(ntermnl[i] + ": " + removeDuplicates(fst[i]));

        flw = new String[ntlen];
        for (int i = 0; i < ntlen; i++) flw[i] = follow(i);
            System.out.println("Follow set:");
        for (int i = 0; i < ntlen; i++) 
        	System.out.println(ntermnl[i] + ": " + removeDuplicates(flw[i]));
    }

    static String first(int i) {
        String temp = "";
        for (String production : grmr[i]) {
            for (int k = 0; k < production.length(); k++) {
                char symbol = production.charAt(k);
                if (isTerminal(symbol)) {
                    temp += symbol;
                    break;
                } else {
                    int index = getNonTerminalIndex(symbol);
                    if (index != -1) {
                        String firstSet = first(index);
                        temp += firstSet.replace("9", "");
                        if (!firstSet.contains("9"))
                        	break; 
                    }
                }
                if (k == production.length() - 1) temp += "9"; 
            }
        }
        return temp;
    }

    static String follow(int i) {
        String temp = i == 0 ? "$" : "";
        for (int j = 0; j < ntlen; j++) {
            for (String production : grmr[j]) {
                for (int l = 0; l < production.length(); l++) {
                    if (production.charAt(l) == ntermnl[i]) {
                        if (l == production.length() - 1) {
                            if (j != i) temp += follow(j);
                        } else {
                            char next = production.charAt(l + 1);
                            if (isTerminal(next)) temp += next;
                            else {
                                int index = getNonTerminalIndex(next);
                                if (index != -1) {
                                    String firstSet = fst[index];
                                    temp += firstSet.replace("9", "");
                                    if (firstSet.contains("9")) temp += follow(j);
                                }
                            }
                        }
                    }
                }
            }
        }
        return temp;
    }
    static boolean isTerminal(char symbol) {
        for (char t : termnl) if (symbol == t) return true;
        return false;
    }
    static int getNonTerminalIndex(char symbol) {
        for (int i = 0; i < ntlen; i++) if (ntermnl[i] == symbol) return i;
        return -1;
    }
    static String removeDuplicates(String str) {
        boolean[] seen = new boolean[256];
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) if (!seen[ch]) { seen[ch] = true; sb.append(ch); }
        return sb.toString();
    }
}