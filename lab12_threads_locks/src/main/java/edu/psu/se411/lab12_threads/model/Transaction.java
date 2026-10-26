package edu.psu.se411.lab12_threads.model;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

public class Transaction implements Runnable {
	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;
	
	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		super();
		if(amount < 0) throw new IllegalArgumentException("Amount used in transaction cannot be negative");
		
		if(id == null || sender == null || receiver == null || amount < 0) throw new IllegalArgumentException();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
	
	@Override
	public void run() {
		try {
			execute();
		} catch (InsufficientBalanceException e) {
			//System.out.println(e.getMessage());
		}
	}
	//نقفل الحسابين بترتيب ثابت حسب الـID عشان نتجنب الـDeadlock، ونحمي عملية التحويل كاملة. هذا يفترض إن لكل حساب ID مختلف.
	public void execute() throws InsufficientBalanceException {
	    if (sender == receiver)
	        throw new IllegalArgumentException(
	            "Cannot make a transaction where the sender and the receiver are the same account");

	    // Order accounts by ID to prevent deadlock.
	    CryptoAccount first;
	    CryptoAccount second;

	    if (sender.getId().compareTo(receiver.getId()) < 0) {
	        first = sender;
	        second = receiver;
	    } else {
	        first = receiver;
	        second = sender;
	    }

	    // Always lock accounts in ascending ID order.
	    first.getLock().lock();
	    try {
	        second.getLock().lock();
	        try {
	            // Check and transfer while both accounts are locked.
	            if (sender.getBalance() < amount)
	                throw new InsufficientBalanceException(sender.getId());

	            sender.retrieveFromBalance(amount);
	            receiver.addToBalance(amount);
	        } finally {
	            // Release the second lock first.
	            second.getLock().unlock();
	        }
	    } finally {
	        // Always release the first lock.
	        first.getLock().unlock();
	    }
	}
}