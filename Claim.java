/**
 * File name : Claim.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: Claim class
 *
 */


public class Claim {
    String claimNumber, accidentDate, accidentAddress, accidentDescription, accidentDamages;
    double estimateDamageCost;
    int policyNumber;

      public void fileClaim(String claimNumber, String accidentDate, String accidentAddress, String accidentDescription, String accidentDamages, double estimateDamageCost, int policyNumber){
          this.claimNumber = claimNumber;
          this.accidentAddress=accidentAddress;
          this.accidentDate=accidentDate;
          this.accidentDamages=accidentDamages;
          this.accidentDescription=accidentDescription;
          this.estimateDamageCost=estimateDamageCost;
          this.policyNumber=policyNumber;

      }

    public String getClaimNumber() {
        return claimNumber;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }


    public String toString() {
        return "Claims: " +
                " Claim Number: " + claimNumber +
                ",  Accident Date: " + accidentDate +
                ",  Accident Address: " + accidentAddress +
                ",  Accident Description: " + accidentDescription +
                ",  Accident Damages: " + accidentDamages +
                ",  Estimated Damage Cost: " + estimateDamageCost +
                ",  Policy Number: " + policyNumber;
    }
}//end Claim
