package com.driver;

public class Main {
    public static void main(String[] args) {
        SavingsAccount s1 = new SavingsAccount("abhi",1000,1,5);

        System.out.println(s1.getBalance());
        System.out.println(s1.getCompoundInterest(2,10) - 1638.52);
        System.out.println(s1.getSimpleInterest(2));
    }
}