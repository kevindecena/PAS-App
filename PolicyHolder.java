/**
 * File name : PolicyHolder.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: Policy Holder class
 *
 */



public class PolicyHolder  {
    String policyHolderFirstName, policyHolderLastName, policyHolderAddress, policyHolderDL, policyHolderBirthdate;
    String policyHolderDLDate;
    int policyNumber, accountNumber;

    public void createPolicyHolder(int policyNumber, String policyHolderFirstName, String policyHolderLastName, String policyHolderBirthdate, String policyHolderAddress, String policyHolderDL, String policyHolderDLDate, int accountNumber){
        this.policyNumber = policyNumber;
        this.policyHolderFirstName=policyHolderFirstName;
        this.policyHolderLastName=policyHolderLastName;
        this.policyHolderBirthdate=policyHolderBirthdate;
        this.policyHolderAddress=policyHolderAddress;
        this.policyHolderDL=policyHolderDL;
        this.policyHolderDLDate=policyHolderDLDate;
        this.accountNumber = accountNumber;
    }

    public String getPolicyHolderDLDate() {
        return policyHolderDLDate;
    }

    public String getPolicyHolderFirstName() {
        return policyHolderFirstName;
    }

    public String getPolicyHolderLastName() {
        return policyHolderLastName;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public String toString() {
        return "PolicyHolders: " +
                "policyHolderFirstName=" + policyHolderFirstName
                + ", policyHolderLastName=" + policyHolderLastName
                + ", policyHolderAddress=" + policyHolderAddress
                + ", policyHolderDL='" + policyHolderDL
                + ", policyHolderBirthdate=" + policyHolderBirthdate
                + ", policyHolderDLDate=" + policyHolderDLDate
                + ", policyNumber=" + policyNumber
                + "Account Number= " + accountNumber;
    }

}//end PolicyHolder
