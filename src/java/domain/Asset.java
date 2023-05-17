package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author kobym
 */
/*Asset inherets from account because it is a subclass of account*/
@Entity
@DiscriminatorValue(value="Asset")
public class Asset extends Account implements Serializable{
    @Column (name = "Interest_Rate")
    private double interestRate;
    @Column (name = "Transaction_Fee")
    private double transactionFee;
    
    public Asset(){}
    
    @Override
    public String toString() {
        return super.toString() + "\n" + "InterestRate: " + getInterestRate() + "\n" + "Transaction Fee:" + getTransactionFee() + "\n";
    }
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }
    
    public void setTransactionFee(double transactionFee){
        this.transactionFee = transactionFee;
    }
    
    public double getInterestRate(){
        return interestRate;
    }
    public double getTransactionFee(){
        return transactionFee;
    }
}