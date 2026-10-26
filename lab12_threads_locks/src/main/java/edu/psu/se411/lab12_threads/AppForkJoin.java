package edu.psu.se411.lab12_threads;

import java.util.concurrent.ExecutorService;

// Q3 - Modified: use ForkJoinPool instead of Executors.
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.CryptoAccount;
import edu.psu.se411.lab12_threads.model.Transaction;

// Q3 - Modified: create a second main program using ForkJoinPool.
public class AppForkJoin {

    // Q3 - Modified: use the logger for the second main program.
    static Logger logger = LoggerFactory.getLogger(AppForkJoin.class);

    // Q3 - Test with 10 accounts, then change to 1000 accounts.
    public static Integer nbAccounts = 10;

    public static Double initialBalance = 10000.0;

    // Q3 - Modified: use one million transactions in both test cases.
    public static Integer nbTransactions = 1000000;

    public static void main(String[] args) {
        logger.info("Application is starting...");

        // 1- Create accounts
        CryptoAccount[] accounts = new CryptoAccount[nbAccounts];

        for (Integer i = 0; i < nbAccounts; i++) {
            accounts[i] = new CryptoAccount(
                    "acc_" + i.toString(), initialBalance);
        }

        System.out.printf(
                "Initial money volume in system: %f%n%n",
                sumTotalMoneyInAccounts(accounts));

        // Q3 - Modified: replace the thread pool with ForkJoinPool.
        // Use the same worker count as App for the comparison.
        ExecutorService executor = new ForkJoinPool(
                Runtime.getRuntime().availableProcessors());

        long startTime = System.currentTimeMillis();

        // 2- Choose sender and receiver accounts randomly
        for (int i = 0; i < nbTransactions; i++) {
            int sender_index = (int) (Math.random() * nbAccounts);
            int receiver_index = (int) (Math.random() * nbAccounts);

            // Make sure indexes are different
            if (sender_index == receiver_index) {
                sender_index = (sender_index + 1) % nbAccounts;
            }

            assert (sender_index != receiver_index);

            CryptoAccount sender = accounts[sender_index];
            CryptoAccount receiver = accounts[receiver_index];

            // 3- Execute transaction
            Double amount = Math.random() * 100;
            Transaction t = new Transaction(
                    "id_", sender, receiver, amount);

            executor.execute(t);
        }

        // 4- Wait for all transactions to complete
        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 5- Check total money after all transactions
        System.out.printf(
                "Final money volume in system: %f%n%n",
                sumTotalMoneyInAccounts(accounts));

        long endTime = System.currentTimeMillis();

        // Q3 - Modified: display ForkJoinPool time for comparison.
        System.out.println(
                "\nTotal time for multi-threading using ForkJoinPool: "
                + (endTime - startTime) + " milliseconds");

        logger.info("Application is closing...");
    }

    private static Double sumTotalMoneyInAccounts(
            CryptoAccount[] accounts) {

        Double total = 0.0;

        for (CryptoAccount c : accounts) {
            total += c.getBalance();
        }

        return total;
    }
}