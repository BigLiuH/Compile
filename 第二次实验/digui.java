import java.util.*;

public class digui {
    static String s;
    static int i;
    static boolean ERR;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        sc.close();
        s += "#";
        i = 0;
        ERR = false;
        E();
        if (i < s.length() && s.charAt(i) == '#' && !ERR) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    static void E() {
        T();
        flag1:
        while (true) {
            if (s.charAt(i) != '-' && s.charAt(i) != '+') {
                return;
            }
            i++;
            T();
            // goto flag1
        }
    }

    static void T() {
        F();
        flag2:
        while (true) {
            if (s.charAt(i) != '*' && s.charAt(i) != '/') {
                return;
            }
            i++;
            F();
            // goto flag2
        }
    }

    static void F() {
        char ch = s.charAt(i);
        if (isI(ch)) {
            while (isI(s.charAt(i))) {
                i++;
            }
        } else if (ch == '(') {
            i++;
            E();
            if (s.charAt(i) != ')') {
                ERR = true;
                return;
            }
            i++;
        } else {
            ERR = true;
            return;
        }
    }

    static boolean isI(char ch) {
        // 完全按照原 C++ 逻辑（包含同样的判断顺序）
        return (ch >= 'a' && s.charAt(i) <= 'z') || (ch >= '0' && ch <= '9');
    }
}
