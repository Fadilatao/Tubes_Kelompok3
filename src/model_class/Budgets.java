package model_class;

public class Budgets {
    private double balance ;

    // Membuat konstruktor untuk objek budgets nantinya 
    public Budgets(double initBalance) {
        this.balance = initBalance;
    }

    // membuat setter dan getter 
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    
}
