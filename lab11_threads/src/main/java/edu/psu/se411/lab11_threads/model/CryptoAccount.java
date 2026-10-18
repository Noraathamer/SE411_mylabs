package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {
	private String id;
	private Double balance;
	
	public CryptoAccount(String id, Double balance) {
		this.id = id;
		this.balance = balance;
	}
	//deposit money into the account
	public void addToBalance(Double amount) {
		// TODO
		balance += amount;
	}
	//withdraw money from the account
	public void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		// TODO
		if (balance < amount) {
			throw new InsufficientBalanceException("Insufficient balance to retrieve " + amount);
		}
		balance -= amount;
		
	}
	
	public String getId() {
		return id;
	}
	public Double getBalance() {
		return balance;
	}
	
}
