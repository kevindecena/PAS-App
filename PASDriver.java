/**
 * File name : PAS Driver.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: the main method of the program. the program allows users to use the PAS System to create an account,
 * buy policies, file claims, cancel policies, and search accounts/policies/claims.
 *
 */


import java.util.Scanner;
public class PASDriver  {


    public static void main(String[] args) {
        PASApp app = new PASApp();
        RatingEngine rater = new RatingEngine();
        Scanner input = new Scanner(System.in);
        InputChecker checker = new InputChecker();

        String option;
            do{
            app.displayMenu();
            option = input.nextLine();

                switch (option) {//create customer account option
                    case "1" -> {
                        app.createCustomerAccount();
                        System.out.println();
                    }

                    case "2" -> {//create policy option
                        System.out.println("Enter your Account Number: ");
                        String accountNumber = input.nextLine();
                        try {
                            if (app.searchAccountNumber(accountNumber)) { //should have a valid customer account first
                                app.createPolicy(accountNumber);
                                String noOfCars;
                                do {
                                    System.out.println("Enter how many vehicles you want to include in the policy: ");
                                     noOfCars = input.nextLine();   // no of cars to be included in the policy
                                }while(!(checker.isNumber(noOfCars)));
                                    for (int i = 0; i < Integer.parseInt(noOfCars); i++) {
                                        System.out.println("Details for vehicle #" + (i + 1));
                                        app.setVehicle();
                                        rater.calculatePremium(PASApp.getPolicyNumber());
                                    }
                                } else{
                                    System.out.println(accountNumber + " is not a valid Account Number. Please create an Account first.");
                                }
                            } catch(NumberFormatException exception){
                                System.out.println(accountNumber + " is not a valid Account Number. Please create an Account first.");
                            }

                    }

                    case "3" -> {//Cancel a specific policy option
                        System.out.println("Enter policy number to Cancel (1XXXXX): ");
                        String policyNumber = input.nextLine();
                        try {
                            if (app.searchPolicy(Integer.parseInt(policyNumber))) {
                                app.cancelPolicy(Integer.parseInt(policyNumber));

                            } else {
                                System.out.println(policyNumber + " is not a valid policy number. Please buy a policy first");
                            }
                        } catch (NumberFormatException exception) {
                            System.out.println(policyNumber + " is not a valid policy number. Please buy a policy first");
                        }
                        System.out.println();
                    }

                    case "4" -> {//File an accident claim option
                        System.out.println("Enter Policy Number (1XXXXX): ");
                        String policyNumber = input.nextLine();
                        try {
                            if (app.searchPolicy(Integer.parseInt(policyNumber))) { //should have a valid policy first
                                app.fileClaim(Integer.parseInt(policyNumber));
                            } else {
                                System.out.println(policyNumber + " is not a valid policy number. Please buy a policy first");
                            }
                        } catch (NumberFormatException exception) {
                            System.out.println(policyNumber + " is not a valid policy number. Please buy a policy first");
                        }
                        System.out.println();
                    }

                    case "5" -> {//search specific account option
                        System.out.println("Enter registered account firstname: ");
                        String accountFirstName = input.nextLine();
                        System.out.println("Enter registered account lastname: ");
                        String accountLastName = input.nextLine();
                        try {
                            if (app.searchAccountName(accountFirstName, accountLastName)) {
                                System.out.println();
                            } else {
                                System.out.println(accountFirstName+ " "+ accountLastName + " has no valid Account. Please create an Account first.");
                            }
                        } catch (NumberFormatException exception) {
                            System.out.println(accountFirstName+ " "+ accountLastName +  "has no valid Account. Please buy a policy first");
                        }
                        System.out.println();
                    }

                    case "6" -> {//search and display a specific policy number option
                        System.out.println("Enter policy number to search for (1XXXXX): ");
                        String policyNumber = input.nextLine();
                        try {
                            if (app.searchPolicy(Integer.parseInt(policyNumber))) {
                                app.printFilteredPolicy(Integer.parseInt(policyNumber));
                                rater.calculatePremium(Integer.parseInt(policyNumber));
                            } else {
                                System.out.println(policyNumber + " is not a valid policy number. Please buy a policy first");
                            }
                        } catch (NumberFormatException exception) {
                            System.out.println(policyNumber + " is not a valid policy number. Please buy a policy first");
                        }
                        System.out.println();
                    }

                    case "7" -> {//search and display a claim number option
                        System.out.println("Enter Claim Number (C1XXXX); ");
                        String claimNumber = input.nextLine();
                        try {
                            if (app.searchClaim(claimNumber)) {
                                app.printFilteredClaims(claimNumber);
                            } else {
                                System.out.println(claimNumber + " is not a valid Claim number. Please file claim first.");
                            }
                        } catch (NumberFormatException exception) {
                            System.out.println(claimNumber + " is not a valid policy number. Please buy a policy first");
                        }
                        System.out.println();
                    }

                    default -> System.out.println("Please choose from options (1-8) only");
                }//end switch
            }while (!option.equals("8"));

            input.close();//scanner close
    }//End main class

}//End PASDriver