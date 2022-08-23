/**
 * File name : Policy.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: Policy class
 *
 */

public class Policy {

    int policyNumber, accountNumber;
    double policyPremium;
    String effectiveDate, expiryDate;



    public void createPolicy(int policyNumber, String effectiveDate, String expiryDate, int accountNumber){
    this.policyNumber = policyNumber;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.accountNumber=accountNumber;

    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    public int getAccountNumber() {
        return accountNumber;
    }


    public String toString(){
        return ("Policy Number: " + this.policyNumber
                + "  Policy Effective Date: " + this.effectiveDate
                + "  Policy Expiry Date: " + this.expiryDate
                + "  Account Number " + accountNumber
                + "  Total Policy Premium: " + policyPremium + "\n");
    }
}//end Policy
