package edu.spu.se411.lab06_logging;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;
import edu.spu.se411.lab06_logging.model.WalletAccount;

public class App {
	//2:
	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		//
		logger.info("Application is starting...");
		//
		WalletAccount account = new WalletAccount(1000);
		
		//
		logger.debug("Wallet account created.");
		
        try {
        	//
        	logger.debug("Withdraw operation.");
        	//
            account.withdraw(1500);
        } catch (InsufficientFundsException e) {
        	//
        	logger.warn("InsufficientFundsException object created.");
        	logger.error("Exception thrown: " + e.getMessage());
        	//
            System.out.println("Exception caught: " + e.getMessage());
        }

        try {
        	//
        	logger.debug("Deposit operation.");
        	//
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
        	//
        	logger.error("Exception thrown: " + e.getMessage());
        	//
            System.out.println("Exception caught: " + e.getMessage());
        }
        logger.info("Application is ending...");
	}

}
