/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorSemester1;

import java.util.Scanner;

/**
 *
 * @author Poh Xue Mei
 */
public class Calculator {

    String s;
    double ans;
    Scanner sc = new Scanner(System.in);

    public String input() {

        System.out.println("Enter a string");
        String s = sc.next();
        return s;
    }
    
    public boolean gotInputAns(String t) {
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == 'a') {
                return true;
            }

        }
        return false;
    }

    public String inputAns(String t, String a) {
        int head = 0;
        int tail = 0;
        while (gotInputAns(t)) {
            for (int i = 0; i < t.length(); i++) {
                if (t.charAt(i) == 'a') {
                    head = i;
                }
                if (t.charAt(i) == 's') {
                    tail = i;
                    break;
                }

            }
            t = t.substring(0, head) + a + t.substring(tail + 1);
        }

        return t;
    }

    
    public boolean gotDigitParentheses(String s) {

        for (int i = 0; i < s.length() - 1; i++) {

            if (Character.isDigit(s.charAt(i)) && s.charAt(i + 1) == '(') {
                return true;
            }
        }
        return false;
    }

    public String digitParentheses(String p) {
        while (gotDigitParentheses(p)) {
            for (int i = 0; i < p.length() - 1; i++) {
                if (Character.isDigit(p.charAt(i)) && p.charAt(i + 1) == '(') {
                    p = p.substring(0, i + 1) + '*' + p.substring(i + 1);
                }
            }
        }
        return p;
    }

    public boolean gotParentheses(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                return true;
            }
        }
        return false;
    }

    public String parenthesesFirst(String p) {
        while (gotParentheses(p)) {
            int head = 0;
            int tail = 0;
            for (int i = p.length() - 1; i >= 0; i--) {
                if (p.charAt(i) == '(') {
                    head = i;
                    for (int j = i + 1; j < p.length(); j++) {
                        if (p.charAt(j) == ')') {
                            tail = j;
                            break;
                        }
                    }
                    break;
                }
            }
            String oper = "";
            for (int i = head + 1; i < tail; i++) {
                oper += p.charAt(i);
            }

            if (tail != p.length() - 1) {
                p = p.substring(0, head) + AddOrSub(MulOrDivFirst(oper)) + p.substring(tail + 1);
            } else {
                p = p.substring(0, head) + AddOrSub(MulOrDivFirst(oper));
            }

        }
        return p;
    }

    public boolean gotAddOrSub(String p) {
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '+' || p.charAt(i) == '-') {
                return true;
            }
        }
        return false;
    }

    public String AddOrSub(String p) {
        while (gotAddOrSub(p)&&p.charAt(0)!='-') {
            int head = 0;
            int tail = 0;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '+' || p.charAt(i) == '-') {
                    int m = i - 1;
                    while (Character.isDigit(p.charAt(m)) || p.charAt(m) == '.') {
                        if (m == 0) {
                            m = -1;
                            break;
                        }
                        m--;
                    }
                    head = m + 1;
                    int n = i + 1;
                    while (Character.isDigit(p.charAt(n)) || p.charAt(n) == '.') {
                        if (n == p.length() - 1) {
                            n = p.length();
                            break;
                        }
                        n++;
                    }
                    tail = n - 1;
                    break;
                }
                
                
                
            }
            String oper = "";
            for (int i = head; i <= tail; i++) {
                oper += p.charAt(i);
            }
            if (tail != p.length() - 1) {
                p = p.substring(0, head) + operation(oper) + p.substring(tail + 1);
            } else {
                p = p.substring(0, head) + operation(oper);
            }
        }
        return p;
    }

    public boolean gotMulOrDiv(String p) {
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' || p.charAt(i) == '/') {
                return true;
            }
        }
        return false;
    }

    public String MulOrDivFirst(String p) {
        while (gotMulOrDiv(p)) {
            int head = 0;
            int tail = 0;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '*' || p.charAt(i) == '/') {
                    int m = i - 1;
                    while (Character.isDigit(p.charAt(m)) || p.charAt(m) == '.') {
                        if (m == 0) {
                            m = -1;
                            break;
                        }
                        m--;
                    }
                    head = m + 1;
                    int n = i + 1;
                    while (Character.isDigit(p.charAt(n)) || p.charAt(n) == '.') {
                        if (n == p.length() - 1) {
                            n = p.length();
                            break;
                        }
                        n++;
                    }
                    tail = n - 1;
                    break;
                }
            }
            String oper = "";
            for (int i = head; i <= tail; i++) {
                oper += p.charAt(i);
            }
            if (tail != p.length() - 1) {
                p = p.substring(0, head) + operation(oper) + p.substring(tail + 1);
            } else {
                p = p.substring(0, head) + operation(oper);
            }

        }
        return p;
    }

    public double operation(String p) {
        double ans = 0;
        for (int i = 0; i < p.length(); i++) {
            double firstValue = 0;
            double secondValue = 0;
            if (p.charAt(i) == '+' || p.charAt(i) == '-' || p.charAt(i) == '*' || p.charAt(i) == '/') {
                String firstValueStrRev = "";
                String firstValueStr = "";
                int m = i - 1;
                while (Character.isDigit(p.charAt(m)) || p.charAt(m) == '.') {
                    firstValueStrRev += p.charAt(m);

                    if (m == 0) {
                        break;
                    }
                    m--;
                }

                for (int j = firstValueStrRev.length() - 1; j >= 0; j--) {
                    firstValueStr += firstValueStrRev.charAt(j);
                }
                firstValue = Double.valueOf(firstValueStr);

                String secondValueStr = "";
                int n = i + 1;
                while (Character.isDigit(p.charAt(n)) || p.charAt(n) == '.') {
                    secondValueStr += p.charAt(n);

                    if (n == p.length() - 1) {
                        break;
                    }
                    n++;
                }

                secondValue = Double.valueOf(secondValueStr);

                switch (p.charAt(i)) {
                    case '+':
                        ans = firstValue + secondValue;
                        break;
                    case '-':
                        ans = firstValue - secondValue;
                        break;
                    case '*':
                        ans = firstValue * secondValue;
                        break;
                    case '/':
                        ans = firstValue / secondValue;
                        break;

                }
            }
        }
        return ans;
    }
}
