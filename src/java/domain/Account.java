package domain;

import static database.TransactionDA.findTransactionsByAccountNumber;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Account_Type")
public class Account implements Serializable {
    
    public static final String ASSET = "Asset";
    public static final String LIABILITY = "Liability";
    
    @Id
    @Column(name = "Account_Number")
    private String accountNumber;
    @Column(name = "Account_Type")
    private String accountType;
    @Column (name = "Customer_Id")
    private String customerId;
    @Column (name = "Account_Name")
    private String accountName;
    @Column (name = "Date_Opened")
    private Date dateOpened;
    @Transient
    private double balance;


    public Account(){}
    
public void withdraw(double amount) {
    if (this.getAccountType().equals(Account.ASSET)) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    } else if (this.getAccountType().equals(Account.LIABILITY)) {
        balance += amount;
    } else {
        throw new IllegalStateException("Invalid account type");
    }
}

public void deposit(double amount) {
    if (this.getAccountType().equals(Account.ASSET)) {
        balance += amount;
    } else if (this.getAccountType().equals(Account.LIABILITY)) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    } else {
        throw new IllegalStateException("Invalid account type");
    }
}

    public void setAccountNumber(String accountNumber){
    this.accountNumber = accountNumber;
    }
    
    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }
    
    public void setAccountName(String accountName){
        this.accountName = accountName;
    }
    
    public void setDateOpened(Date dateOpened){
        this.dateOpened = dateOpened;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    
    public String getAccountNumber(){
        return accountNumber;
    }
    
    public String getCustomerId(){
        return customerId;
    }
    
    public String getAccountName(){
        return accountName;
    }
    
    public Date getDateOpened(){
        return dateOpened;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public static double getAccountBalance(String accountNumber) {
        ArrayList<Transaction> transactions = findTransactionsByAccountNumber(accountNumber);
        double balance = 0.0;
        for (Transaction transaction : transactions) {
        balance += transaction.getTransactionAmount();
            }
        return balance;
    }
    
    public String getAccountType(){
        return accountType;
    }
    
}
