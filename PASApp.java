/**
 * File name : PASApp.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: This class functions as the application where there are user interaction and input.
 *
 */


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PASApp {
//customer account variables
    int accountNumber = 1000;
    String accountFirstName, accountLastName, accountAddress;
//policy variables
    static int policyNumber = 100000;
    String effectiveDate, expiryDate;
//policyholders variables
    String policyHolderFirstName, policyHolderLastName, policyHolderAddress, policyHolderDL, policyHolderBirthdate;
    String policyHolderDLDate;
    int dlYears;

//vehicle variables
    String make, model, type, fuelType, color, vehicleYear, plateNumber;
    double purchasePrice;
    int carAge;
//Claim variables
String claimNumber, accidentDate, accidentAddress, accidentDescription, accidentDamages;
    double estimateDamageCost;
    int claimNo = 10000;



    List<CustomerAccount> customerAccountCollection = new ArrayList<>(50);
    List <Policy> policyCollection = new ArrayList<>(50);
    List <PolicyHolder> policyHolderCollection = new ArrayList<>(50);
    static List <Vehicle> vehicleCollection = new ArrayList<>(50);
    List <Claim> claimCollection = new ArrayList<>(50);
    Scanner input = new Scanner(System.in);


    public void displayMenu(){ //displays the options for the user
        System.out.println("PAS SYSTEM MENU");
        System.out.println("1 - Create a new Customer Account");
        System.out.println("2 - Get a policy quote and buy the policy");
        System.out.println("3 - Cancel a specific policy");
        System.out.println("4 - File an accident claim against a policy");
        System.out.println("5 - Search for a Customer account");
        System.out.println("6 - Search for and display a specific policy");
        System.out.println("7 - Search for and display a specific claim");
        System.out.println("8 - Exit the PAS System");
    }//end displayMenu

    public boolean searchAccountNumber(String accountNumber){ //search customer account by account number
        for (CustomerAccount acct: customerAccountCollection) {
           if( acct.getAccountNumber() == Integer.parseInt(accountNumber)){
               System.out.println("ACCOUNT DETAILS:");
               System.out.println("Account Number: " + acct.getAccountNumber());
               System.out.println("Customer Account Name: " + acct.getFirstName() + " " + acct.getLastName());
               return true;
           }
        }
        return false;
    }//end searchAccountNumber

    public boolean searchAccountName(String accountFirstName, String accountLastName){      //search customer account by name from the collection
        for (CustomerAccount acct: customerAccountCollection) {
            if( acct.getFirstName().equalsIgnoreCase(accountFirstName.trim())  && (acct.getLastName().equalsIgnoreCase(accountLastName.trim()))){
                System.out.println("ACCOUNT DETAILS:");
                System.out.println("Account Number: " + acct.getAccountNumber());
                System.out.println("Customer Account Name: " + acct.getFirstName() + " " + acct.getLastName());
                printFilteredAccount(acct.getAccountNumber());
                return true;
            }
        }
        return false;
    }//end searchAccountName

    public boolean searchPolicy(int policyNumber){      //used to search policy from the collection
        for (Policy policy: policyCollection) {
            if( policy.getPolicyNumber() == (policyNumber)){
                System.out.println("POLICY DETAILS:");
                System.out.println("Customer Account Number: " + policy.getAccountNumber());
                System.out.println("Policy Number: " + policy.getPolicyNumber());
                return true;
            }
        }
        return false;
    }//end searchPolicy

    public boolean searchClaim(String claimNumber) {    //used to search for a specific claim in the collection
        for (Claim claim : claimCollection) {
            if (claim.getClaimNumber().equalsIgnoreCase(claimNumber)) {
                System.out.println("CLAIM DETAILS:");
                return true;
            }
        }
        return false;
    }//end searchClaim

    public boolean checkPlateNumber(String plateNumber) {       //used to check vehicle plate numbers for duplicates
        for (Vehicle car : vehicleCollection) {
            if (car.getPlateNumber().equalsIgnoreCase(plateNumber)){
                System.out.println("Cannot enter duplicate/same car");
                return false;
            }
        }
        return true;
    }//end checkPlateNumber

    public void createCustomerAccount() {       //used to create a new Customer Account and adds to the collection
        CustomerAccount acct = new CustomerAccount();

        boolean isEmpty;
            System.out.println("CUSTOMER ACCOUNT DETAILS"); //the instructions assumed customers are unique
            do {    System.out.println("Enter your First Name: ");
            accountFirstName = input.nextLine().toUpperCase().trim(); isEmpty = accountFirstName.isEmpty();  }while(isEmpty);
            do {    System.out.println("Enter your Last Name: ");
            accountLastName = input.nextLine().toUpperCase().trim(); isEmpty = accountLastName.isEmpty();    }while(isEmpty);
            do{     System.out.println("Enter you address: ");
            accountAddress = input.nextLine(); isEmpty=accountAddress.isEmpty();        }while(isEmpty);


        accountNumber++;
        acct.createCustomerAccount(accountNumber, accountFirstName, accountLastName, accountAddress);
        customerAccountCollection.add(acct);
        System.out.println("Customer Account "+ accountNumber +" Created!");
    }//end createCustomerAccount

    public void createPolicy(String accountNumber) {    //used to create a new policy and adds to the collection
        this.accountNumber= Integer.parseInt(accountNumber);
        Policy policy = new Policy();
        policyNumber++;
        createPolicyHolder(accountNumber,policyNumber );

        boolean isValid;
        do {
            InputChecker checker = new InputChecker();
            System.out.println("Enter effective date of Policy yyyy-mm-dd: ");
            effectiveDate = input.nextLine();
            if (checker.checkDateFormat(effectiveDate)) {
                if(checker.isNotPast(effectiveDate)) {
                    isValid = true;
                }
                else{
                    System.out.println("Cannot create policy effectivity date in the past");
                    isValid = false;
                }
            } else {
                System.out.println("Please input date with correct format (YYYY-MM-DD)");
                isValid = false;
            }
        }while(!isValid);



        LocalDate efDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("uuuu-M-d"));
        expiryDate = String.valueOf(efDate.plusMonths(6));



        policy.createPolicy(policyNumber, effectiveDate, expiryDate, Integer.parseInt(accountNumber));
        policyCollection.add(policy);

        System.out.println("Policy " + policyNumber+ " created!");
        System.out.println("Policy expiry: " + expiryDate);

        System.out.println();

    }//end createPolicy

    public void createPolicyHolder(String accountNumber, int policyNumber){ //used to create new policyholder and adds to the collections
        this.policyNumber=policyNumber;
        this.accountNumber= Integer.parseInt(accountNumber);
        PolicyHolder policyHolder = new PolicyHolder();
        String ans;
        String tempFirst = "", tempLast = "";
        boolean isEmpty;
        do {
            for (CustomerAccount account: customerAccountCollection) {
                if (Objects.equals(account.getAccountNumber(), Integer.parseInt(accountNumber))) {
                    System.out.println("Is the Policy Holder Name same as the Account Holder Name " + account.getFirstName() + " " + account.getLastName() + " ?: (Y/N)");
                    tempFirst = account.getFirstName();
                    tempLast = account.getLastName();
                }
            }
                    ans = input.nextLine();
                if (ans.equalsIgnoreCase("Y")) {
                    policyHolderFirstName = tempFirst;
                    policyHolderLastName = tempLast;
                    break;
                } else if (ans.equalsIgnoreCase("n")) {
                   do{ System.out.println("Enter POLICY HOLDER firstname: ");
                    policyHolderFirstName = input.nextLine(); isEmpty = policyHolderFirstName.isEmpty();    }while(isEmpty);
                   do{ System.out.println("Enter POLICY HOLDER lastname: ");
                    policyHolderLastName = input.nextLine(); isEmpty = policyHolderLastName.isEmpty();      }while(isEmpty);
                    break;
                }


        }while ((!ans.equalsIgnoreCase("Y")) || (!ans.equalsIgnoreCase("N")));

        boolean isValid;
        do {
            InputChecker checker = new InputChecker();
            System.out.println("Enter POLICY HOLDER date of birth YYYY-MM-DD: ");
            policyHolderBirthdate = input.nextLine();
            if (checker.checkDateFormat(policyHolderBirthdate) && !(checker.isNotPast(policyHolderBirthdate))) {
                isValid = true;
            } else {
                System.out.println("Please input VALID date with correct format (YYYY-MM-DD). Date cannot be in the future");
                isValid = false;
            }
        }while(!isValid);

        do{ System.out.println("Enter POLICY HOLDER address: ");
        policyHolderAddress = input.nextLine(); isEmpty = policyHolderAddress.isEmpty();      }while(isEmpty);
        do{ System.out.println("Enter POLICY HOLDER Driver's License Number: ");
        policyHolderDL = input.nextLine(); isEmpty = policyHolderDL.isEmpty();      }while(isEmpty);

        boolean isOK;
        do {
                InputChecker checker = new InputChecker();
                System.out.println("Enter Date on which Driver's license was first issued YYYY-MM-DD:");
                policyHolderDLDate = input.nextLine();
                if (checker.checkDateFormat(policyHolderDLDate) && !(checker.isNotPast(policyHolderDLDate))) {//should be valid date as it is part of the computation
                    isOK = true;
                } else {
                    System.out.println("Please input VALID date with correct format (YYYY-MM-DD). Date cannot be in the future");
                    isOK = false;
                }

        }while(!isOK);



        policyHolder.createPolicyHolder(policyNumber, policyHolderFirstName, policyHolderLastName, policyHolderBirthdate,policyHolderAddress , policyHolderDL, policyHolderDLDate, this.accountNumber);
        policyHolderCollection.add(policyHolder);

        System.out.println("Policy holder " +policyHolderFirstName + " " +policyHolderLastName+" will be added to the policy");//test
    }//end createPolicyHolder

    public void setVehicle(){   //used to create a new set of vehicle and adds it to the collection
        Vehicle car = new Vehicle();
        boolean isEmpty;
        do {
            System.out.println("Enter your vehicle's Plate Number: ");
            plateNumber = input.nextLine().toUpperCase();
        }while(!checkPlateNumber(plateNumber) || (plateNumber.isEmpty())); //plate number should be unique

        do{ System.out.println("Please enter your vehicle Make (e.g. Toyota, Ford, etc.): ");
        make = input.nextLine().toUpperCase(); isEmpty = make.isEmpty();      }while(isEmpty);

        do{ System.out.println("Please enter your vehicle Model: ");
        model = input.nextLine().toUpperCase(); isEmpty = model.isEmpty();      }while(isEmpty);

        boolean isValidYear;
        do {
                try {
                    System.out.println("Please enter your vehicle Year: ");
                    vehicleYear = String.valueOf(Integer.parseInt(input.nextLine()));
                    isValidYear = true;

                    if(Integer.parseInt(vehicleYear) < 1900 || Integer.parseInt(vehicleYear) > (LocalDate.now().getYear()+1)){ //should be valid year as it is part of the computation
                        System.out.println("Please input a valid Year Model 1900 to present year model");
                    }
                } catch (Exception e) {
                    System.out.println("Please input proper Year Model (YYYY)");
                    isValidYear = false;
                }
            } while (!(isValidYear) || !((Integer.parseInt(vehicleYear) >= 1900) && (Integer.parseInt(vehicleYear) <= (LocalDate.now().getYear()+1))));

        carAge = computeVehicleAge(vehicleYear);
        this.dlYears = computeDLYears(policyNumber);


        boolean typeOK;
        do {
            System.out.println("Please choose your vehicle type below (1-4): ");
            System.out.println("1 - Sedan (4-door)");
            System.out.println("2 - Sports car (2-door)");
            System.out.println("3 - SUV");
            System.out.println("4 - Truck");
            type = input.nextLine();

            switch (type){
                case "1":{ type = "4-Door Sedan"; typeOK=true; break;}
                case "2":{ type = "2-Door Sports Car";typeOK = true; break;}
                case "3":{ type = "SUV"; typeOK = true; break;}
                case "4":{ type = "Truck"; typeOK = true; break;}
                default: typeOK = false;
            }
        }while(!typeOK);

        System.out.println(type);

        boolean fuelTypeOK;
        do {
            System.out.println("Please choose your vehicle Fuel type: ");
            System.out.println("1 - Diesel");
            System.out.println("2 - Electric");
            System.out.println("3 - Petrol");

            fuelType = input.nextLine();

            switch (fuelType){
                case "1":{ fuelType = "Diesel"; fuelTypeOK=true; break;}
                case "2":{ fuelType = "Electric";fuelTypeOK = true; break;}
                case "3":{ fuelType = "Petrol"; fuelTypeOK = true; break;}
                default: fuelTypeOK = false;
            }
        }while(!fuelTypeOK);

        System.out.println(fuelType);

        do{ System.out.println("Color: ");
        color = input.nextLine().toUpperCase(); isEmpty = color.isEmpty();      }while(isEmpty);

        boolean isValid;
        do {
            try {
                    System.out.println("Purchase price: ");
                    purchasePrice = Double.parseDouble(input.nextLine());
                    if(purchasePrice <0) {
                        System.out.println("Enter positive value");
                        isValid = false;
                    }
                else {
                    isValid = true;
                    }
            }catch(Exception e) {
                System.out.println("Please input an integer value");
                isValid = false;
            }
        }while(!(isValid) || !(purchasePrice>0));



        car.setVehicle(policyNumber, make, model,plateNumber, vehicleYear, type, fuelType, purchasePrice, color, accountNumber, carAge, this.dlYears);
        vehicleCollection.add(car);

        System.out.println( make +" "+ model + " " +type+" "+ vehicleYear+" "+ plateNumber + " is added to policy number "+ policyNumber);


    }//end setVehicle


    public void fileClaim(int policyNumber){ //used to file a claim against a specific policy
        this.policyNumber=policyNumber;
        claimNo++;
        claimNumber = "C"+ claimNo;

        Claim claim = new Claim();
        System.out.println("Enter details for Claim# " + claimNumber);

        boolean isValid=false, isEmpty;
        do {
            InputChecker checker = new InputChecker();
            System.out.println("Enter date of Accident (YYYY-MM-DD): ");
            accidentDate = input.nextLine();
            if (checker.checkDateFormat(accidentDate)) {
                if (checkCoverage(accidentDate, policyNumber)) {
                    isValid = true;
                }
                else {
                    System.out.println("Date should be WITHIN POLICY COVERAGE.");
                    for (Policy policy: policyCollection) {
                        if( policy.getPolicyNumber() == (policyNumber)){
                            System.out.println("Policy Coverage: " + policy.getEffectiveDate() +" to "+ policy.getExpiryDate());
                        }
                    }
                }
            } else {
                System.out.println("Please input Valid date with correct format (YYYY-MM-DD).");
            }
        }while(!isValid);



        do{     System.out.println("Address where the accident happened: ");
        accidentAddress = input.nextLine(); isEmpty = accountAddress.isEmpty();               }while(isEmpty);
        do{     System.out.println("Additional description of the accident: ");
        accidentDescription = input.nextLine(); isEmpty = accidentDescription.isEmpty();      }while(isEmpty);
        do{     System.out.println("Description of damage to vehicle: ");
        accidentDamages = input.nextLine(); isEmpty = accidentDamages.isEmpty();               }while(isEmpty);

        boolean isInteger;
        do {
            try {
                do {
                    System.out.println("Estimated cost of repairs: ");
                    estimateDamageCost = Double.parseDouble(input.nextLine());
                    if(estimateDamageCost < 0) System.out.println("Cannot be a negative number.");
                }while(estimateDamageCost <0);
                isInteger = true;
            }catch(Exception e) {
                System.out.println("Please input an integer value");
                isInteger = false;
            }
        }while(!isInteger);

        claim.fileClaim(claimNumber,  accidentDate, accidentAddress,  accidentDescription, accidentDamages,  estimateDamageCost, policyNumber);
        claimCollection.add(claim);

        System.out.println("Claim "+ claimNumber +" filed");
        System.out.println();

    }//end fileClaim

    public void printFilteredAccount(int accountNumber){ // used to display a user specified account and its details


        for (CustomerAccount account: customerAccountCollection) {
            if (Objects.equals(account.getAccountNumber(), accountNumber)){
                System.out.println("Account Name: " +account.getFirstName()+ " "+ account.getLastName());
                System.out.println("Account Address: " + account.getAddress());
            }
        }

        System.out.println("************************************************");
        System.out.println("Policies in the account: ");
        for (Policy policy: policyCollection) {
        if (Objects.equals(policy.getAccountNumber(), accountNumber)){
            System.out.println("Policy Number: "+ policy.getPolicyNumber() +"  Expiry: " + policy.getExpiryDate());
            if (checkPolicyStatus(policy.getExpiryDate())){
                System.out.println("Policy Status: ACTIVE");
            }
            else{
                System.out.println("Policy Status: CLOSED");
            }
        }
    }
        System.out.println("************************************************");
        System.out.println();
}//end printFilteredAccount

    public void printFilteredPolicy(int policyNumber) {// used to display a user specified policy and its details
        System.out.println("************************************************");
        for (Policy policy : policyCollection) {
            if (Objects.equals(policy.getPolicyNumber(), policyNumber)) {
                System.out.println("Policy Number: " + policy.getPolicyNumber() + " Expiry: " + policy.getExpiryDate());
                if (checkPolicyStatus(policy.getExpiryDate())){
                    System.out.println("Policy Status: ACTIVE");
                }
                else{
                    System.out.println("Policy Status: CLOSED");
                }

            }
        }

        System.out.println("Policy Holder/s: ");
        for (PolicyHolder policyHolder : policyHolderCollection) {
            if (Objects.equals(policyHolder.getPolicyNumber(), policyNumber)) {
                System.out.println(policyHolder.getPolicyHolderFirstName() + " " + policyHolder.getPolicyHolderLastName());
            }
        }

        System.out.println("Claims under Policy " + policyNumber + " : ");
        for (Claim claim : claimCollection) {
            if (Objects.equals(claim.getPolicyNumber(), policyNumber)) {
                System.out.println(claim.getClaimNumber());
            }
        }

        System.out.println();


    }//end printFilteredPolicy

    public void printFilteredClaims(String claimNumber){    // used to display a user specified claim and its details
             System.out.println("************************************************");
            for (Claim claim: claimCollection) {
                if (claim.getClaimNumber().equalsIgnoreCase(claimNumber)){
                    System.out.println("found claim " + claim.getClaimNumber() + " from Policy number " + claim.getPolicyNumber());
                    System.out.println("Accident date: "+ claim.accidentDate);
                    System.out.println("Accident Description: "+ claim.accidentDescription + " at " + claim.accidentAddress);
                    System.out.println("Estimated Damage Cost: $"+ claim.estimateDamageCost);

                }
            }
            System.out.println("************************************************");
    }//end printFilteredClaims

    public int computeDLYears(int policyNumber){ //used to compute for the drivers' licence age/years
        this.policyNumber = policyNumber;

        for (PolicyHolder policyHolder : policyHolderCollection) {
            if(Objects.equals(policyHolder.getPolicyNumber(), policyNumber)) {
                policyHolderDLDate = policyHolder.getPolicyHolderDLDate();
                LocalDate dlDate = LocalDate.parse(policyHolder.getPolicyHolderDLDate(), DateTimeFormatter.ofPattern("uuuu-M-d"));
                LocalDate today = LocalDate.now();
                Period age = Period.between(dlDate, today);
                dlYears = age.getYears();
                return dlYears;
            }
        }
        return dlYears;
    }//end computeDLYears

    public int computeVehicleAge(String vehicleYear){   //used to compute for the vehicle age
        this.vehicleYear=vehicleYear;
        int year = Integer.parseInt(vehicleYear);
        int year1 = Calendar.getInstance().get(Calendar.YEAR);
        this.carAge = year1-year;
        return this.carAge;

    }//end computeVehicleAge

    public void cancelPolicy(int policyNumber){ //used to cancel a specific policy
        this.policyNumber=policyNumber;
        System.out.println("POLICY FOUND");
        System.out.println("************************************************");
        for (Policy policy: policyCollection) {
            if (Objects.equals(policy.getPolicyNumber(), policyNumber)){
                policy.setExpiryDate(String.valueOf(LocalDate.now().minusDays(1)));
                System.out.println("Policy " + policyNumber + " Cancelled. Expired "+ (LocalDate.now().minusDays(1)));
            }
        }
        System.out.println("************************************************");
    }//end cancelPolicy

    public boolean checkCoverage(String date, int policyNumber) {   //used to check if the date is within the policy coverage

            for (Policy policy : policyCollection) {
                if (Objects.equals(policy.getPolicyNumber(), policyNumber)) {
                    if ((LocalDate.parse(policy.getExpiryDate(),DateTimeFormatter.ofPattern("uuuu-M-d") ).isAfter(LocalDate.parse(date,DateTimeFormatter.ofPattern("uuuu-M-d")))
                            && LocalDate.parse(policy.getEffectiveDate(),DateTimeFormatter.ofPattern("uuuu-M-d")).isBefore(LocalDate.parse(date,DateTimeFormatter.ofPattern("uuuu-M-d"))))
                        || ((LocalDate.parse(date,DateTimeFormatter.ofPattern("uuuu-M-d"))).isEqual((LocalDate.parse(policy.getExpiryDate(),DateTimeFormatter.ofPattern("uuuu-M-d")))))
                            || ((LocalDate.parse(date,DateTimeFormatter.ofPattern("uuuu-M-d"))).isEqual(LocalDate.parse(policy.getEffectiveDate(),DateTimeFormatter.ofPattern("uuuu-M-d"))))){
                        return true;
                    }
                }
            }
            return false;
        }//end checkCoverage

    public boolean checkPolicyStatus(String date){ //used to check the policy status via the expiry date
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-M-d")).isAfter(LocalDate.now().minusDays(1));
    }//end checkPolicyStatus

    public static int getPolicyNumber() {
        return policyNumber;
    }
} //end PASApp
