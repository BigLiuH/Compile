import java.util.*;

public class LRParser {
    static class Node {
        char ch;
        int num;

        Node(char ch, int num) {
            this.ch = ch;
            this.num = num;
        }
    }

    static Map<Character, Integer> mp = new HashMap<>();
    static Node[][] has = new Node[20][20];
    static Stack<Character> s1 = new Stack<>(); // 符号栈
    static Stack<Integer> s2 = new Stack<>();   // 状态栈
    static String s;
    static String[] ss = {
            "", "EEwT", "ET", "TTeF", "TF", "F(E)", "Fi"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        pre(); // 初始化状态表
        s = scanner.nextLine();
        solve();
    }

    static void pre() {
        mp.put('i', 1);
        mp.put('w', 2);
        mp.put('e', 3);
        mp.put('(', 4);
        mp.put(')', 5);
        mp.put('#', 6);
        mp.put('E', 7);
        mp.put('T', 8);
        mp.put('F', 9);

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                has[i][j] = new Node('a', -1);

        has[0][1] = new Node('s', 5);
        has[0][4] = new Node('s', 4);
        has[0][mp.get('E')] = new Node('a', 1);
        has[0][mp.get('T')] = new Node('a', 2);
        has[0][mp.get('F')] = new Node('a', 3);
        has[1][2] = new Node('s', 6);
        has[1][6] = new Node('c', 100);

        has[2][2] = new Node('r', 2);
        has[2][3] = new Node('s', 7);
        has[2][5] = new Node('r', 2);
        has[2][6] = new Node('r', 2);
        has[3][2] = new Node('r', 4);
        has[3][3] = new Node('r', 4);
        has[3][5] = new Node('r', 4);
        has[3][6] = new Node('r', 4);

        has[4][1] = new Node('s', 5);
        has[4][4] = new Node('s', 4);
        has[4][mp.get('E')] = new Node('a', 8);
        has[4][mp.get('T')] = new Node('a', 2);
        has[4][mp.get('F')] = new Node('a', 3);

        has[5][2] = new Node('r', 6);
        has[5][3] = new Node('r', 6);
        has[5][5] = new Node('r', 6);
        has[5][6] = new Node('r', 6);

        has[6][1] = new Node('s', 5);
        has[6][4] = new Node('s', 4);
        has[6][mp.get('T')] = new Node('a', 9);
        has[6][mp.get('F')] = new Node('a', 3);

        has[7][1] = new Node('s', 5);
        has[7][4] = new Node('s', 4);
        has[7][mp.get('F')] = new Node('a', 10);

        has[8][2] = new Node('s', 6);
        has[8][5] = new Node('s', 11);

        has[9][2] = new Node('r', 1);
        has[9][3] = new Node('s', 7);
        has[9][5] = new Node('r', 1);
        has[9][6] = new Node('r', 1);

        has[10][2] = new Node('r', 3);
        has[10][3] = new Node('r', 3);
        has[10][5] = new Node('r', 3);
        has[10][6] = new Node('r', 3);

        has[11][2] = new Node('r', 5);
        has[11][3] = new Node('r', 5);
        has[11][5] = new Node('r', 5);
        has[11][6] = new Node('r', 5);
    }

    static char jude(int[] pos) {
        while (pos[0] < s.length() && s.charAt(pos[0]) == ' ') pos[0]++;
        if (pos[0] >= s.length()) return '#';

        char c = s.charAt(pos[0]);

        if (c == '(' || c == ')') {
            pos[0]++;
            return c;
        }

        if (Character.isLetterOrDigit(c)) {
            while (pos[0] < s.length() &&
                    Character.isLetterOrDigit(s.charAt(pos[0]))) pos[0]++;
            return 'i';
        }

        if (c == '+' || c == '-') {
            pos[0]++;
            return 'w';
        }

        if (c == '*' || c == '/') {
            pos[0]++;
            return 'e';
        }

        return s.charAt(pos[0]++);
    }

    static void solve() {
        s += "#";
        int[] pos = {0};
        s1.clear();
        s2.clear();

        s1.push('#');
        s2.push(0);

        while (true) {
            char ch = jude(pos);
            while (true) {
                int st = s2.peek();
                Node tmp = has[st][mp.getOrDefault(ch, -1)];

                if (tmp.ch == 's') {
                    s1.push(ch);
                    s2.push(tmp.num);
                    break;
                } else if (tmp.ch == 'r') {
                    String ts = ss[tmp.num];
                    int tn = ts.length() - 1;
                    while (tn-- > 0) {
                        s1.pop();
                        s2.pop();
                    }
                    char nt = ts.charAt(0);
                    s1.push(nt);
                    int state = has[s2.peek()][mp.get(nt)].num;
                    s2.push(state);
                } else if (tmp.ch == 'c') {
                    System.out.println("true");
                    return;
                } else {
                    System.out.println("false");
                    return;
                }
            }
        }
    }
}
