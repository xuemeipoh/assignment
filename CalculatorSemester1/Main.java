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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calculator a = new Calculator();
        String ans = "";
        String s = a.input();
        ans = a.AddOrSub(a.MulOrDivFirst(a.parenthesesFirst(a.digitParentheses(s))));
        System.out.println(ans);
        while (true) {
            Scanner sc = new Scanner(System.in);
            String s1 = sc.next();
            if (!Character.isDigit(s1.charAt(0))) {
                ans = a.AddOrSub(a.MulOrDivFirst(a.parenthesesFirst(a.digitParentheses(ans + a.inputAns(s1, ans)))));
                System.out.println(ans);
            }
            else{
                ans = a.AddOrSub(a.MulOrDivFirst(a.parenthesesFirst(a.digitParentheses(a.inputAns(s1, ans)))));
                System.out.println(ans);
            }
        }
    }

}
