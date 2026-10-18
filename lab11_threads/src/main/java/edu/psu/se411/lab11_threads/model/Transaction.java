package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class Transaction implements Runnable {
	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;
	
	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		super();
		if(id == null || sender == null || receiver == null || amount < 0) throw new IllegalArgumentException();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
//f-Use the “synchronized” keyword on one method to implement a simple solution to the problem.
	private static synchronized void transfer( // Execute one transfer iside this method at a time using a shared lock
	        CryptoAccount sender, CryptoAccount receiver, Double amount)
	        throws InsufficientBalanceException {

	    sender.retrieveFromBalance(amount);
	    receiver.addToBalance(amount);
	}
	
	public void execute() {
		// TODO
		  try {
		       // before sync:sender.retrieveFromBalance(amount);
		       // before sync:receiver.addToBalance(amount);
			  
			// After: Call the synchronized method to transfer money safely
			  transfer(sender, receiver, amount);
		    } catch (InsufficientBalanceException e) {
		        System.err.println(e.getMessage());
		    }
	}
	
	@Override
	public void run() {
	    execute();
	}
	
	public CryptoAccount getSender() {
		return sender;
	}
	public void setSender(CryptoAccount sender) {
		this.sender = sender;
	}
	public CryptoAccount getReceiver() {
		return receiver;
	}
	public void setReceiver(CryptoAccount receiver) {
		this.receiver = receiver;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
}
