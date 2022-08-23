/**
 * File name : Rating Engine.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: Rating Engine class used to compute for the premium
 *
 */


import java.text.DecimalFormat;
import java.util.Objects;

public class RatingEngine {
    double premiumCharged, purchasePrice, vehiclePriceFactor;
    int dlYears, carAge, policyNumber;

    static final DecimalFormat df = new DecimalFormat("###.##");

    public void calculatePremium(int policyNumber) {

        double totalPremium = 0;

        System.out.println("VEHICLE DETAILS:");
        System.out.println("************************************************");
        for (Vehicle vehicle : PASApp.vehicleCollection) {
            if (Objects.equals(vehicle.getPolicyNumber(), policyNumber)) {
               purchasePrice = vehicle.getPurchasePrice();
                dlYears = vehicle.getDlYears();
                carAge = vehicle.getCarAge();
                this.policyNumber = vehicle.getPolicyNumber();


                if (carAge < 1) {
                    vehiclePriceFactor = .01;

                } else if (carAge < 3) {
                    vehiclePriceFactor = .008;

                } else if (carAge < 5) {
                    vehiclePriceFactor = .007;

                } else if (carAge < 10) {
                    vehiclePriceFactor = .006;

                } else if (carAge < 15) {
                    vehiclePriceFactor = .004;

                } else if (carAge < 20) {
                    vehiclePriceFactor = .002;

                } else if (carAge < 40) {
                    vehiclePriceFactor = .001;

                }

                premiumCharged = (vehicle.getPurchasePrice() * vehiclePriceFactor) + ((vehicle.getPurchasePrice() / 100) / dlYears);
                premiumCharged = Double.parseDouble(df.format(premiumCharged));
                System.out.println("Plate Number: "+vehicle.getPlateNumber() );
                System.out.println("Vehicle Details: "+ vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getPlateNumber() + " " +vehicle.getVehicleYear() + " " + vehicle.getType() + " " + vehicle.getColor());
                System.out.println("Premium Cost: $" + premiumCharged);
                totalPremium = totalPremium + premiumCharged;
                System.out.println();
            }

            }


        totalPremium = Double.parseDouble(df.format(totalPremium));
        System.out.println("************************************************");
        System.out.println("TOTAL PREMIUM FOR POLICY " + policyNumber + " is $" + totalPremium);
        System.out.println();
        }


    }//end RatingEngine

