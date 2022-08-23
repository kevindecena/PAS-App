/**
 * File name : Vehicle.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: Vehicle class for the vehicle objects
 *
 */


public class Vehicle {
    private String make, model, type, fuelType, color, vehicleYear, plateNumber;
    private double purchasePrice;
    int policyNumber, carAge, dlYears, accountNumber;


    public void setVehicle(int policyNumber, String make, String model, String plateNumber, String vehicleYear, String type, String fuelType, double purchasePrice, String color, int accountNumber, int carAge, int dlYears){
        this.policyNumber=policyNumber;
        this.make=make;
        this.model=model;
        this.vehicleYear=vehicleYear;
        this.type=type;
        this.fuelType=fuelType;
        this.purchasePrice=purchasePrice;
        this.color=color;
        this.plateNumber=plateNumber;
        this.accountNumber=accountNumber;
        this.carAge = carAge;
        this.dlYears=dlYears;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getDlYears() {
        return dlYears;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getCarAge() {
        return carAge;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }


    public String toString() {
        return "Vehicles: " +
                "make= " + make +
                ", model= " + model  +
                ", type= " + type +
                ", fuelType= " + fuelType +
                ", color= " + color +
                ", vehicleYear= " + vehicleYear +
                ", purchasePrice= " + purchasePrice +
                ", policyNumber= " + policyNumber +
                ", carAge = " + carAge +
                ", DlYears= " + dlYears ;
    }

}//end Vehicle
