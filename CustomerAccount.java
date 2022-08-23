/**
 * File name : CustomerAccount.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: The customer Accounts class for customer objects. all policies should have an account first
 *
 */

public class CustomerAccount {
    String firstName, lastName, address;
    int accountNumber;


    public void createCustomerAccount(int accountNumber, String firstName, String lastName, String address){
        this.accountNumber=accountNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
    }//end createAccount



    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public String toString(){
        return this.accountNumber + " "+ this.firstName + " "+ this.lastName + " "+ this.address + "\n";
    }

} //end CustomerAccount
