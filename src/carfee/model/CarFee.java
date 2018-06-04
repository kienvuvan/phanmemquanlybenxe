/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.model;

/**
 *
 * @author DELL
 */
public class CarFee {
    private String carID;
    private String time;
    private double fee;

    public CarFee() {
    }

    public CarFee(String carID, String time, double fee) {
        this.carID = carID;
        this.time = time;
        this.fee = fee;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    
    
}
