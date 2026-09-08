package org.example;
public class SavingAccount extends BankAccount{
    private double interestRate;
    public SavingAccount(String accountId,double balance,double interestRate){
        super(accountId,balance);
        setInterestRate(interestRate);
    }
    public void setInterestRate(double interestRate){
        this.interestRate=interestRate;
    }
    public double getInterestRate(){
        return interestRate;
    }
    @Override
    public void withdraw(double amount){
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
        }else if(amount>getBalance()) {
            System.out.println("余额不足");
        }else{
            System.out.println("取款金额错误");
        }
    }
    public void addInterest(){
        setBalance(getBalance() + getBalance() * getInterestRate());
    }
}
