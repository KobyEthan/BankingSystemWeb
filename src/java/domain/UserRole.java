package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_Role")
public class UserRole implements Serializable {
    @Id
    @Column (name = "Role_Type")
    private String roleType;
    @Column (name = "All_Users_Power")
    private Boolean allUsersPower;
    @Column (name = "View_Accounts")
    private Boolean viewAccounts;
    @Column (name = "Edit_Accounts")
    private Boolean editAccounts;
    @Column (name = "Create_Accounts")
    private Boolean createAccounts;
    @Column (name = "View_Transactions")
    private Boolean viewTransactions;
    @Column (name = "Transfer_Funds")
    private Boolean transferFunds;
    @Column (name = "View_Customers")
    private Boolean viewCustomers;
    @Column (name = "Edit_Customers")
    private Boolean editCustomers;
    @Column (name = "Create_Customers")
    private Boolean createCustomers;
    @Column (name = "Create_User_Roles")
    private Boolean createUserRoles;
    
    public UserRole() {
    }
    
    public UserRole(String roleType, boolean allUsersPower, boolean viewAccounts, boolean editAccounts, boolean createAccounts, boolean viewTransactions, boolean transferFunds, boolean viewCustomers, boolean editCustomers, boolean createCustomers, boolean createUserRoles) {
        this.roleType = roleType;
        this.allUsersPower = allUsersPower;
        this.viewAccounts = viewAccounts;
        this.editAccounts = editAccounts;
        this.createAccounts = createAccounts;
        this.viewTransactions = viewTransactions;
        this.transferFunds = transferFunds;
        this.viewCustomers = viewCustomers;
        this.editCustomers = editCustomers;
        this.createCustomers = createCustomers;
        this.createUserRoles = createUserRoles;
    }
    
    @Override
    public String toString() {
    return "UserRoles{" +
            "roleType='" + roleType + '\'' +
            ", allUsersPower=" + allUsersPower +
            ", viewAccounts=" + viewAccounts +
            ", editAccounts=" + editAccounts +
            ", createAccounts=" + createAccounts +
            ", viewTransactions=" + viewTransactions +
            ", Transfer_Funds=" + transferFunds +
            ", viewCustomers=" + viewCustomers +
            ", editCustomers=" + editCustomers +
            ", createCustomers=" + createCustomers +
            ", createUserRoles=" + createUserRoles +
            '}';
        }
    
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Boolean getAllUsersPower() {
        return allUsersPower;
    }

    public void setAllUsersPower(Boolean allUsersPower) {
        this.allUsersPower = allUsersPower;
    }

    public Boolean getViewAccounts() {
        return viewAccounts;
    }

    public void setViewAccounts(Boolean viewAccounts) {
        this.viewAccounts = viewAccounts;
    }

    public Boolean getEditAccounts() {
        return editAccounts;
    }

    public void setEditAccounts(Boolean editAccounts) {
        this.editAccounts = editAccounts;
    }

    public Boolean getCreateAccounts() {
        return createAccounts;
    }

    public void setCreateAccounts(Boolean createAccounts) {
        this.createAccounts = createAccounts;
    }

    public Boolean getViewTransactions() {
        return viewTransactions;
    }

    public void setViewTransactions(Boolean viewTransactions) {
        this.viewTransactions = viewTransactions;
    }

    public Boolean getTransferFunds() {
        return transferFunds;
    }

    public void setTransferFunds(Boolean transferFunds) {
        this.transferFunds = transferFunds;
    }

    public Boolean getViewCustomers() {
        return viewCustomers;
    }

    public void setViewCustomers(Boolean viewCustomers) {
        this.viewCustomers = viewCustomers;
    }

    public Boolean getEditCustomers() {
        return editCustomers;
    }

    public void setEditCustomers(Boolean editCustomers) {
        this.editCustomers = editCustomers;
    }

    public Boolean getCreateCustomers() {
        return createCustomers;
    }

    public void setCreateCustomers(Boolean createCustomers) {
        this.createCustomers = createCustomers;
    }

    public Boolean getCreateUserRoles() {
        return createUserRoles;
    }

    public void setCreateUserRoles(Boolean createUserRoles) {
        this.createUserRoles = createUserRoles;
    }
}

