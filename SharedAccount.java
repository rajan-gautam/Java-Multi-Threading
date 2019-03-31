
import java.sql.SQLOutput;
import java.util.Scanner;

public class SharedAccount {

    private double balance;
    private char currency;		// 'D' 'E' 'P'
    //set balance and set currency should be private because we have deposit and withdraw.

    public SharedAccount(){
        balance = 0.00;
        currency = 'N'; //
    }

    //getters
    private double getBalance(){  return balance; }
    private char getCurrency() {return currency; }

    //deposit
    public synchronized void deposit(double amount, char currency) throws AmountExceptions, InterruptedException {

        while(!((this.currency == 'N')||(this.currency == currency))) {            //If we have some balance and try to deposit more in the same currency, It will wait until some money is withdrawn (notify() if withdraw is called)
            System.out.println("deposit on hold");
            wait();
        }

//        if(this.currency == 'N')
//            this.currency = currency;
        this.balance = this.balance + amount;
        this.currency = currency;
        System.out.println("$" + amount + " " + getCurrency() + " was deposited.");
        notify();
//        if(this.currency == currency) {
//
//        }
//        else
//            throw new AmountExceptions("Wrong Currency!!");
    }

    //withdraw
    public synchronized double withdraw(double withdrawAmount) throws AmountExceptions, InterruptedException {
       //checks if withdraw amount is greater. if positive, waits for the deposit
        //System.out.println(withdrawAmount + "  to withdraw, from " + getBalance());
        while(!(withdrawAmount <= getBalance())) {
            System.out.println("withdrawal on hold");
            wait();
        }
        this.balance = this.balance - withdrawAmount;
        System.out.println("You withdrew: $" + withdrawAmount + "\nYour remaining balance in the account is: $" + getBalance() + "\n");

        //if balance is 0, sets currency to N.
        if(getBalance() == 0)
            currency = 'N';

        notify();
        return withdrawAmount;
//        if (balance) {
//
//        }
//        else
//            throw new AmountExceptions("Transaction Cancelled! Not sufficient Amount!!");

    }

}