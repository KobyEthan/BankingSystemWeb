package domain;

import database.TransactionDA;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Transaction1")
public class Transaction implements Serializable {
    @Id
    @Column(name = "Transaction_Id")
    private String transactionId;
    @Column(name = "Transaction_Amount")
    private double transactionAmount;
    @Column(name = "Description")
    private String description;
    @Column(name = "Transaction_Date")
    private String transactionDate;
    @Column(name = "Account_Number")
    private String accountNumber;
    @Column(name = "Transaction_Type")
    private String transactionType;
    @Transient
    private double runningBalance;

   public Transaction() {
    this.transactionType = "";
}

    public Transaction(double transactionAmount, String description, String transactionDate,
            String accountNumber, String transactionType) {
        this.transactionAmount = transactionAmount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
    }

    public static void add(Transaction t) {
        TransactionDA.add(t);
    }

    public static Transaction find(String id) {
        return TransactionDA.find(id);
    }

    public static ArrayList<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return TransactionDA.findTransactionsByAccountNumber(accountNumber);
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setRunningBalance(double runningBalance) {
        this.runningBalance = runningBalance;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getDescription() {
        return description;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getRunningBalance() {
        return runningBalance;
    }
}