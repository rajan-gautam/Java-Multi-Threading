import java.util.ArrayList;
import java.util.Stack;

import javax.security.auth.login.AccountException;

public class Account {
    public static void main(String [] args){
        SharedAccount sharedAccount = new SharedAccount();


        Stack<Double> moneyAmount = new Stack<>();
        Stack<Character> moneyCurrency  = new Stack<>();

        moneyAmount.add(1.0); moneyCurrency.add('D'); //One dollar
        moneyAmount.add(2.0); moneyCurrency.add('E'); //two Euros
        moneyAmount.add(3.0); moneyCurrency.add('P'); //Three Pounds

        //sums total MoneyAmount
        // Convert Integer number to double value
        double sumToWithdraw = moneyAmount.stream().mapToDouble(Double::doubleValue).sum();

        Consumer consumer = new Consumer() {
            public void run() {

                double withdrawnSum = 0.0; //initially withdrawn sum is 0
                //checks if withdrawn sum is equal to total in the deposit
                while(withdrawnSum != sumToWithdraw) {

                    try {
                        //   System.out.println("asdasda");
                        withdrawnSum += sharedAccount.withdraw(1.0);

                    } catch (AmountExceptions | InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        };

        Producer producer = new Producer() {
            //i will
            public void run() {

                //check if stack is not empty
                while(moneyAmount.size() > 0) {

                    try {
                        //peek()returns the first element of stack but doesnt remove from stack
                        sharedAccount.deposit(moneyAmount.peek(), moneyCurrency.peek());

                        moneyAmount.pop();
                        moneyCurrency.pop();

                    } catch (AmountExceptions | InterruptedException e) {

                        System.out.println(e);
                    }

                }

            }
        };

        producer.start();

        consumer.start();

    }

}
