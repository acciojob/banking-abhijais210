package com.driver;

public class Main {
    public static void main(String[] args) {
        SavingsAccount s1 = new SavingsAccount("abhi",450.0,200,10);
        try {
            s1.withdraw(300);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(s1.getBalance());
        System.out.println(s1.getCompoundInterest(2,2));
        System.out.println(s1.getSimpleInterest(2));
    }
}