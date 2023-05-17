package domain;
/**
 *
 * @author kobym
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/*Liability inherets from account because it is a subclass of account*/
@Entity
@DiscriminatorValue(value="Liability")
public class Liability extends Account implements Serializable {
@Column (name = "Interest_Rate")
private double interestRate;
@Column (name = "Monthly_Payment")
private double monthlyPayment;
@Column (name = "Payment_Date")
private String paymentDate;
    
    public Liability(){}
    
    @Override
    public String toString() {
        return super.toString() + "\n" + "InterestRate: " + getInterestRate() + "\n" + "Transaction Fee:" + getMonthlyPayment() + "\n";
    }
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }
    public void setMonthlyPayment(double monthlyPayment){
        this.monthlyPayment = monthlyPayment;
    }
    
    public void setPaymentDate(String paymentDate){
        this.paymentDate = paymentDate;
    }
    
    public double getInterestRate(){
        return interestRate;
    }
    
    public double getMonthlyPayment(){
        return monthlyPayment;
    }
    
    public String getPaymentDate(){
        return paymentDate;
    }
        
}