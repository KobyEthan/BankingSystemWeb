package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    @Column (name = "Customer_Id")
    private String customerId;
    @Column (name = "First_Name")
    private String firstName;
    @Column (name = "Last_Name")
    private String lastName;
    @Column (name = "Phone_Number")
    private String phoneNumber;
    @Column (name = "User_Id")
    private String userId;
    @Column (name = "Password")
    private String password;
    @Column (name = "Role_Type")
    private String roleType;
    
    public Customer(){}
    
    @Override
    public String toString() {
    return "Customer{" +
            "customerId='" + customerId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            ", roleType='" + roleType + '\'' +
            '}';
    }

    
    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }
    
    public String getCustomerId(){
        return customerId;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public String getUserId(){
        return userId;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRoleType(){
        return roleType;
    }
}