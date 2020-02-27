import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import static edu.princeton.cs.algs4.StdRandom.random;

public class test {
    //最大公约数欧几里得算法
    public static  int greatestCommon(int p , int q){
//        System.out.println(p+"  "+q);
        int r = p%q;
        if (r == 0){
            return q;
        }else {
            return greatestCommon(q,r);
        }
    }

    //括号全加的算数表达式求值      这个太傻逼了  我傻逼
    public static double stringEasyExpressionCalculate (String expression){
        Stack<Character> operatorStack = new Stack<Character>();
        Stack<Integer> numbertorStack = new Stack<Integer>();
        int number = 0;
        for (char c : expression.toCharArray()) {
            if ('0' <= c && c <= '9'){
                number = number*10;
                number = number+c-'0';
            }else {
                if (number > 0){
                    numbertorStack.push(number);
                    number = 0;
                }
                if ( c == '+' || c == '-' || c == '*' || c == '/') {
                    operatorStack.push(c);
                }
                if (c == ')'){
                    Character pop = operatorStack.pop();
                    if (pop == '+'){
                        numbertorStack.push(numbertorStack.pop()+numbertorStack.pop());
                    }
                    else if (pop == '-'){
                        numbertorStack.push(numbertorStack.pop()-numbertorStack.pop());
                    }
                    else if (pop == '*'){
                        numbertorStack.push(numbertorStack.pop()*numbertorStack.pop());}
                    else if (pop == '/'){
                        int pop1 = numbertorStack.pop();
                        int pop2 = numbertorStack.pop();
                        numbertorStack.push(pop2/pop1);
                    }
                }
            }
        }
        return numbertorStack.pop();
    }

    public static void main(String[] args) {
        String string = "(((1+20)/3)+((1+2)*3))";
        double v = stringEasyExpressionCalculate(string);
        System.out.println(v);
    }



}
