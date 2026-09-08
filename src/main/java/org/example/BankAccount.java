package org.example;
//请你把之前写的 SavingAccount 的 withdraw 方法改造一下：
//删掉里面的 System.out.println 打印语句。
//金额 <= 0 时，抛出 IllegalArgumentException（JDK 自带的，不用新建）。
//余额不足时，自定义一个 InsufficientBalanceException（继承 RuntimeException），并传入清晰的错误消息。

public class BankAccount {
    private String accountId;
    protected double balance;
    public BankAccount(){}
    public BankAccount(String accountId,double balance){
        this.setAccountId(accountId);
        this.setBalance(balance);
    }
    public void setAccountId(String accountId){
        this.accountId=accountId;
    }
    public String getAccountId(){
        return this.accountId;
    }
    
    public void setBalance(double balance){
        if(balance>=0) {
            this.balance = balance;
        }else{
            System.out.println("余额不能为负值");
            this.balance=0.0;
        }
    }
    public double getBalance(){
        return this.balance;
    }
    public void deposit(double amount){
        if(amount>0){
            this.setBalance(this.getBalance() + amount);
        }else{
            System.out.println("存款金额应大于0");
        }

    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.setBalance(this.getBalance() - amount);
        }else if(amount<=0){
            throw new IllegalArgumentException("取款金额应大于零");
        }else{
            throw new InsufficientBalance("当前余额："+getBalance()+"，不足以取款："+amount);
        }
    }
    public void showInfo(){
        String accountId = this.getAccountId();
        double balance = this.getBalance();
        System.out.printf("账号：%s 余额:%.2f \n",accountId,balance);
    }
}
