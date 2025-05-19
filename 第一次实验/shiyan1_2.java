import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class shiyan1_2 {
    static int k = 0, p = 0, i = 0, c = 0;
    static int c02 = 0, ct1 = 0, st1 = 0;
    static Map<String, Integer> K = new HashMap<>();
    static Map<String, Integer> P = new HashMap<>();
    static Map<String, Integer> I = new HashMap<>();
    static Map<String, Integer> C1 = new HashMap<>();
    static Map<String, Integer> C2 = new HashMap<>();
    static Map<String, Integer> CT = new HashMap<>();
    static Map<String, Integer> ST = new HashMap<>();
    static String s = "";
    static int posi = 0;
    static char ch;
    static List<String> i1 = new ArrayList<>();
    static List<String> c1 = new ArrayList<>();
    static List<String> c2 = new ArrayList<>();
    static List<String> ct = new ArrayList<>();
    static List<String> st = new ArrayList<>();

    static void pre() {
        K.put("int", ++k);
        K.put("void", ++k);
        K.put("break", ++k);
        K.put("float", ++k);
        K.put("while", ++k);
        K.put("do", ++k);
        K.put("struct", ++k);
        K.put("const", ++k);
        K.put("case", ++k);
        K.put("for", ++k);
        K.put("return", ++k);
        K.put("if", ++k);
        K.put("default", ++k);
        K.put("else", ++k);

        P.put("-", ++p);
        P.put("/", ++p);
        P.put("(", ++p);
        P.put(")", ++p);
        P.put("==", ++p);
        P.put("<=", ++p);
        P.put("<", ++p);
        P.put("+", ++p);
        P.put("*", ++p);
        P.put(">", ++p);
        P.put("=", ++p);
        P.put(",", ++p);
        P.put(";", ++p);
        P.put("++", ++p);
        P.put("{", ++p);
        P.put("}", ++p);
    }

    static boolean isA(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    static int readNum(StringBuilder token) {
        int status = 1;
        while (true) {
            if (ch == '#') {
                if (status == 2) return 0;
                else if (status == 4 || status == 7) return 1;
                else return -1;
            }
            if (status == 1) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 2;
                } else return -1;
            } else if (status == 2) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                } else if (ch == '.') {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 3;
                } else if (ch == 'e') {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 5;
                } else if (ch == 'x' && token.length() == 1) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 8;
                } else if (ch == 'x') {
                    return -1;
                } else return 0;
            } else if (status == 3) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 4;
                } else return -1;
            } else if (status == 4) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                } else if (ch == 'e') {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 5;
                } else return 1;
            } else if (status == 5) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 7;
                } else if (ch == '+' || ch == '-') {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 6;
                } else return -1;
            } else if (status == 6) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 7;
                } else return -1;
            } else if (status == 7) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                } else return 1;
            } else if (status == 8) {
                if (isNum(ch)) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                    status = 9;
                } else return -1;
            } else { // status == 9
                if (isNum(ch) || (ch >= 'A' && ch <= 'F') || (ch >= 'a' && ch <= 'f')) {
                    token.append(ch);
                    ch = s.charAt(posi++);
                } else {
                    long ans = 0;
                    for (int j = 2; j < token.length(); j++) {
                        char cChar = token.charAt(j);
                        if (isNum(cChar)) ans = ans * 16 + (cChar - '0');
                        else if (cChar >= 'A' && cChar <= 'F') ans = ans * 16 + 10 + (cChar - 'A');
                        else ans = ans * 16 + 10 + (cChar - 'a');
                    }
                    token.setLength(0);
                    token.append(ans);
                    return 0;
                }
            }
        }
    }

    static int readToken(StringBuilder token) {
        token.setLength(0);
        while (ch != '#' && ch == ' ') ch = s.charAt(posi++);
        if (ch == '#') return 0;
        if (isA(ch)) {
            while (isA(ch) || isNum(ch)) {
                token.append(ch);
                ch = s.charAt(posi++);
                if (ch == '#') return 1;
            }
            return 1;
        } else if (isNum(ch)) {
            int t = readNum(token);
            if (t == -1) return -1;
            return 2 + t;
        } else if (ch == '\'') {
            ch = s.charAt(posi++);
            if (ch == '#') return -1;
            token.append(ch);
            ch = s.charAt(posi++);
            if (ch != '\'') return -1;
            ch = s.charAt(posi++);
            return 4;
        } else if (ch == '"') {
            ch = s.charAt(posi++);
            while (ch != '"') {
                if (ch == '#') return -1;
                token.append(ch);
                ch = s.charAt(posi++);
            }
            ch = s.charAt(posi++);
            return 5;
        } else {
            token.append(ch);
            ch = s.charAt(posi++);
            if (ch == '#') return 6;
            String two = token.toString() + ch;
            if (P.containsKey(two)) {
                token.append(ch);
                ch = s.charAt(posi++);
            }
            return 6;
        }
    }

    static void solve() throws IOException {
        pre();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String temp;
        while ((temp = reader.readLine()) != null) {
            s += temp;
        }
        s += '#';
        posi = 0;
        ch = s.charAt(posi++);
        StringBuilder out = new StringBuilder();
        out.append("Token :");
        StringBuilder token = new StringBuilder();
        int t;

        while ((t = readToken(token)) != 0) {
            if (token.length() == 0) break;
            if (t == -1) {
                System.out.println("ERROR");
                return;
            }
            if (t == 1) {
                String tokStr = token.toString();
                if (K.containsKey(tokStr)) out.append("(K ").append(K.get(tokStr)).append(")");
                else if (I.containsKey(tokStr)) out.append("(I ").append(I.get(tokStr)).append(")");
                else {
                    I.put(tokStr, ++i);
                    i1.add(tokStr);
                    out.append("(I ").append(i).append(")");
                }
            } else if (t == 2) {
                String tokStr = token.toString();
                if (C1.containsKey(tokStr)) out.append("(C1 ").append(C1.get(tokStr)).append(")");
                else {
                    C1.put(tokStr, ++c);
                    c1.add(tokStr);
                    out.append("(C1 ").append(c).append(")");
                }
            } else if (t == 3) {
                String tokStr = token.toString();
                if (C2.containsKey(tokStr)) out.append("(C2 ").append(C2.get(tokStr)).append(")");
                else {
                    C2.put(tokStr, ++c02);
                    c2.add(tokStr);
                    out.append("(C2 ").append(c02).append(")");
                }
            } else if (t == 4) {
                String tokStr = token.toString();
                if (CT.containsKey(tokStr)) out.append("(CT ").append(CT.get(tokStr)).append(")");
                else {
                    CT.put(tokStr, ++ct1);
                    ct.add(tokStr);
                    out.append("(CT ").append(ct1).append(")");
                }
            } else if (t == 5) {
                String tokStr = token.toString();
                if (ST.containsKey(tokStr)) out.append("(ST ").append(ST.get(tokStr)).append(")");
                else {
                    ST.put(tokStr, ++st1);
                    st.add(tokStr);
                    out.append("(ST ").append(st1).append(")");
                }
            } else if (P.containsKey(token.toString())) {
                out.append("(P ").append(P.get(token.toString())).append(")");
            }
            if (ch == '#') break;
        }

        out.append("\nI :");
        for (int j = 0; j < i1.size(); j++) {
            out.append(i1.get(j));
            if (j != i1.size() - 1) out.append(" ");
        }
        out.append("\nC1 :");
        for (int j = 0; j < c1.size(); j++) {
            out.append(c1.get(j));
            if (j != c1.size() - 1) out.append(" ");
        }
        out.append("\nC2 :");
        for (int j = 0; j < c2.size(); j++) {
            out.append(c2.get(j));
            if (j != c2.size() - 1) out.append(" ");
        }
        out.append("\nCT :");
        for (int j = 0; j < ct.size(); j++) {
            out.append(ct.get(j));
            if (j != ct.size() - 1) out.append(" ");
        }
        out.append("\nST :");
        for (int j = 0; j < st.size(); j++) {
            out.append(st.get(j));
            if (j != st.size() - 1) out.append(" ");
        }
        System.out.println(out.toString());
        System.in.read();
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}