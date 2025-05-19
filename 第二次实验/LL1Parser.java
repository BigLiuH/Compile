import java.util.*;

public class LL1Parser {
    // 与 C++ 完全对应的全局变量
    static Map<Character, Integer> mp = new HashMap<>();
    static int[][] has = new int[10][10];
    static String[] s = new String[10];
    static Stack<Character> ss = new Stack<>();
    static String s1;
    static Set<Character> vt = new HashSet<>(), vn = new HashSet<>();

    public static void main(String[] args) {
        pre();
        // C++ 中用 while (!ss.empty()) ss.pop(); solve();
        // 这里直接调用 solve()
        while (!ss.empty()) ss.pop();
        solve();
    }

    static void pre() {
        mp.put('E', 1); mp.put('A', 2); mp.put('T', 3); mp.put('B', 4); mp.put('F', 5);
        mp.put('i', 1); mp.put('w', 2); mp.put('e', 3); mp.put('(', 4); mp.put(')', 5); mp.put('#', 6);

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                has[i][j] = -1;

        has[1][1] = 1; has[1][4] = 1;
        has[2][2] = 2; has[2][5] = 3; has[2][6] = 3;
        has[3][1] = 4; has[3][4] = 4;
        has[4][2] = 6; has[4][3] = 5; has[4][5] = has[4][6] = 6;
        has[5][1] = 7; has[5][4] = 8;

        s[1] = "AT";
        s[2] = "ATw"; s[3] = "k";
        s[4] = "BF";
        s[5] = "BFe"; s[6] = "k";
        s[7] = "i"; s[8] = ")E(";

        vt.add('i'); vt.add('w'); vt.add('e'); vt.add('('); vt.add(')');
        vn.add('E'); vn.add('A'); vn.add('T'); vn.add('B'); vn.add('F');
    }

    static char jude(int[] pos) {
        while (pos[0] < s1.length() && s1.charAt(pos[0]) == ' ')
            pos[0]++;
        if (pos[0] >= s1.length()) return '#';
        char c = s1.charAt(pos[0]++);
        if (c == '(' || c == ')') return c;
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
            while (pos[0] < s1.length()) {
                char d = s1.charAt(pos[0]);
                if ((d >= '0' && d <= '9') || (d >= 'a' && d <= 'z')) pos[0]++;
                else break;
            }
            return 'i';
        }
        if (c == '+' || c == '-') return 'w';
        if (c == '*' || c == '/') return 'e';
        return c; // 包含 '#'
    }

    static void solve() {
        Scanner in = new Scanner(System.in);
        s1 = in.nextLine();
        in.close();
        s1 += "#";
        int pos = 0;
        int[] pArr = {pos};

        ss.push('#');
        ss.push('E');

        flag1: while (true) {
            char w = jude(pArr);
            flag2: while (true) {
                char x = ss.pop();
                // 判断终结符
                if (vt.contains(x)) {
                    if (x == w) {
                        continue flag1;
                    } else {
                        System.out.println("False");
                        return;
                    }
                } else {
                    // 非终结符
                    if (vn.contains(x)) {
                        int t = has[mp.get(x)][mp.get(w)];
                        if (t == -1) {
                            System.out.println("False");
                            return;
                        }
                        if (s[t].charAt(0) != 'k') {
                            for (int i = 0; i < s[t].length(); i++) {
                                ss.push(s[t].charAt(i));
                            }
                        }
                        continue flag2;
                    }
                    else if (w == '#') {
                        System.out.println("True");
                        return;
                    }
                    else {
                        System.out.println("False");
                        return;
                    }
                }
            }
        }
    }
}
