package edu.psu.se411.lab12_threads.model;

// Added to use explicit locks instead of synchronized.
import java.util.concurrent.locks.ReentrantLock;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {
    private String id;
    private Double balance;

    // Each account has its own lock.
    private final ReentrantLock lock = new ReentrantLock();

    public CryptoAccount(String id, Double balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Account balance cannot be negative");

        this.id = id;
        this.balance = balance;
    }

    // Replaced synchronized with an explicit lock.
    public void addToBalance(Double amount) {
        lock.lock();
        try {
            if (amount < 0)
                throw new IllegalArgumentException("Amount added to balance cannot be negative");

            balance += amount;
        } finally {
            // Always release the lock, even if an exception occurs.
            lock.unlock();
        }
    }

    // Replaced synchronized with an explicit lock.
    public void retrieveFromBalance(Double amount)
            throws InsufficientBalanceException {
        lock.lock();
        try {
            if (amount > balance)
                throw new InsufficientBalanceException(id);

            balance -= amount;
        } finally {
            // Always release the lock, even if an exception occurs.
            lock.unlock();
        }
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        // Protect balance reads with the same account lock.
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    // Allow Transaction to lock both accounts in ID order.
    public ReentrantLock getLock() {
        return lock;
    }
}