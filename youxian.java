import java.util.*;

public class youxian {
    static Set<Character> vt = new HashSet<>();
    static Map<Character, Integer> mp = new HashMap<>();
    static int[][] has = {
        {0,0,0,0,0,0,0},
        {0,1,2,2,1,2,1},
        {0,1,1,2,1,2,1},
        {0,2,2,2,3,2,1},
        {0,1,1,0,1,0,1},
        {0,1,1,0,1,0,1},
        {0,2,2,2,0,2,0}
    };

    static String s;
    static List<Character> ss;

    public static void main(String[] args) {
        pre();
        solve();
    }

    static void pre() {
        mp.put('w', 1);
        mp.put('e', 2);
        mp.put('(', 3);
        mp.put(')', 4);
        mp.put('i', 5);
        mp.put('#', 6);

        // 正确的终结符初始化方式
        vt.add('w');
        vt.add('e');
        vt.add('(');
        vt.add(')');
        vt.add('i');
        vt.add('#');
    }

    static char jude(char[] input, int[] pos) {
        while (pos[0] < input.length && input[pos[0]] == ' ')
            pos[0]++;
        if (pos[0] >= input.length) return '#';
        char c = input[pos[0]++];
        if (c == '(' || c == ')') return c;
        if (Character.isLetterOrDigit(c)) {
            while (pos[0] < input.length && Character.isLetterOrDigit(input[pos[0]]))
                pos[0]++;
            return 'i';
        }
        if (c == '+' || c == '-') return 'w';
        if (c == '*' || c == '/') return 'e';
        return c; // 包括 #
    }

    static boolean reduce() {
        int top = ss.size() - 1;
        char cur = ss.get(top);
        if (cur == 'i') {
            ss.remove(top);
            ss.add('N');
            return true;
        }
        if (cur == ')') {
            if (top < 2 || ss.get(top-1) != 'N' || ss.get(top-2) != '(')
                return false;
            ss.remove(top);
            ss.remove(top-1);
            ss.remove(top-2);
            ss.add('N');
            return true;
        }
        if (cur == 'N') {
            if (top < 2 || ss.get(top-2) != 'N')
                return false;
            ss.remove(top);
            ss.remove(top-1);
            ss.remove(top-2);
            ss.add('N');
            return true;
        }
        return false;
    }

    static void solve() {
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        in.close();
        char[] input = (s + "#").toCharArray();
        int[] pos = {0};
        ss = new ArrayList<>();
        ss.add('#');

        int steps = 0;
        while (true) {
            char ch = jude(input, pos);
            while (true) {
                if (++steps > 3000) {
                    System.out.println("false");
                    return;
                }
                int j = ss.size() - 1;
                if (ss.get(j) == 'N' && ss.get(j) != '#') j--;
                char stackSym = ss.get(j);
                int rel = has[ mp.get(stackSym) ][ mp.get(ch) ];
                if (rel == 2 || rel == 3) {
                    ss.add(ch);
                    break;
                } else if (rel == 1) {
                    if (!reduce()) {
                        System.out.println("false");
                        return;
                    }
                } else {
                    if (ch == '#' && stackSym == '#')
                        System.out.println("True");
                    else
                        System.out.println("False");
                    return;
                }
            }
        }
    }
}
