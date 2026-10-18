package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.psu.se411.lab11_threads.model.Transaction;


import edu.psu.se411.lab11_threads.model.CryptoAccount;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);
//number of accounts, initial balance for each acc, and number of transactions
	public static Integer nbAccounts = 10;
	public static Double initialBalance = 1000.0; //before: 10000.0;
	public static Integer nbTransactions = 10000; //before: 1000;
// we have 10*10000 = 100000 transactions 
	
	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		// 1- Create accounts
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (Integer i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i.toString(), initialBalance);
		}
		
		// TODO
		Thread[] threads = new Thread[nbTransactions];

		for (int i = 0; i < nbTransactions; i++) {
		//choose a random sender, receiver
		//Math.random() * nbAccounts: ينتج رقمًا من 0 إلى أقل من 10. و(int) يحذف الكسور، فيصير رقم موقع بالحسابات من 0 إلى 9.
		CryptoAccount sender =
			    accounts[(int) (Math.random() * nbAccounts)];

			CryptoAccount receiver =
			    accounts[(int) (Math.random() * nbAccounts)];
// choose a random amount between 1 and 10
			Double amount = Math.random() * 10 + 1;

			//each transaction has different id, sender, receiver, and amount
			Transaction t = new Transaction("tr_" + i, sender, receiver, amount);
// create a thread for each transaction and start it. حطينا اختيار الحسابات وانشاء التحويل داخل لوب يكررها 10000 مره
		    threads[i] = new Thread(t);
		    threads[i].start();
		}
		//عشان البرنامج ينتظر انتهاء كل التحويلات
		// Wait for all threads to finish
		try {
		    for (Thread thread : threads) {// loop through all threads 
		        thread.join();//wait for each to finish
		    }
		} catch (InterruptedException e) {
		    logger.error("Interrupted while waiting for transactions", e);
		    Thread.currentThread().interrupt();
		    return;
		}
		// Display the total balance after all transactions finish
		// calling the method to get the total balance of all accounts after all transactions are done
		System.out.println("Final total: " + getTotalBalance(accounts));
		
		logger.info("Application is closing...");
	}
	// Calculate the total balance of all accounts
	public static double getTotalBalance(CryptoAccount[] accounts) {
	    double total = 0; //start with 0

	    for (CryptoAccount account : accounts) {// loop through each account
	        total += account.getBalance();// add the balance of each account to total
	    }

	    return total;
	}
}
