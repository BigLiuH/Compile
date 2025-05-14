import java.util.*;

public class five {
    static String s;
    static int i;
    static boolean ERR;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        sc.close();
        i = 0;
        ERR = false;
        s += "#";
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
        if (isI(s.charAt(i))) {
            // nothing, isI already advanced i
        } else if (s.charAt(i) == '(') {
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

    static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    static boolean isA(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    static boolean isI(char ch) {
        if (isA(ch)) {
            while (i < s.length() && (isA(s.charAt(i)) || isNum(s.charAt(i)))) {
                i++;
            }
            return true;
        }
        if (isNum(ch)) {
            while (i < s.length() && isNum(s.charAt(i))) {
                i++;
            }
            if (s.charAt(i) != 'e' && s.charAt(i) != '.') {
                return true;
            } else {
                i++;
            }
            if (s.charAt(i) == '-' && s.charAt(i - 1) != '.') {
                i++;
            } else if (s.charAt(i) == '-' && s.charAt(i - 1) == '.') {
                ERR = true;
                return false;
            }
            while (i < s.length() && isNum(s.charAt(i))) {
                i++;
            }
            return true;
        }
        return false;
    }
}
