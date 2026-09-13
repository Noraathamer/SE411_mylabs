package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class EX2 {

    public static void main(String[] args) {

        wallet wallet = new wallet(500);

        try {
            wallet.withdraw(700);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}