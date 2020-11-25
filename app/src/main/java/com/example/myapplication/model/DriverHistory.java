package com.example.myapplication.model;

import java.util.Date;

public class DriverHistory {
    private int pId;
    private String NIC;
    private String fName;
    private String lName;
    private String pAddress;
    private Date pDOB;
    private int pContactNo;
    private String vehicleNumber;
    private int rId;
    private String rDescription;
    private double rCost;
    private Date rDate;
    private String place;
    private int adminId;
    private int agId;
    private int isAccepted;

    public DriverHistory() {
    }

    public DriverHistory(int pId, String NIC, String fName, String lName, String pAddress, Date pDOB, int pContactNo, String vehicleNumber, int rId, String rDescription, double rCost, Date rDate, String place, int adminId, int agId, int isAccepted) {
        this.pId = pId;
        this.NIC = NIC;
        this.fName = fName;
        this.lName = lName;
        this.pAddress = pAddress;
        this.pDOB = pDOB;
        this.pContactNo = pContactNo;
        this.vehicleNumber = vehicleNumber;
        this.rId = rId;
        this.rDescription = rDescription;
        this.rCost = rCost;
        this.rDate = rDate;
        this.place = place;
        this.adminId = adminId;
        this.agId = agId;
        this.isAccepted = isAccepted;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getNic() {
        return NIC;
    }

    public void setNic(String NIC) {
        this.NIC = NIC;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public Date getpDOB() {
        return pDOB;
    }

    public void setpDOB(Date pDOB) {
        this.pDOB = pDOB;
    }

    public int getpContactNo() {
        return pContactNo;
    }

    public void setpContactNo(int pContactNo) {
        this.pContactNo = pContactNo;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }

    public double getrCost() {
        return rCost;
    }

    public void setrCost(double rCost) {
        this.rCost = rCost;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getAgId() {
        return agId;
    }

    public void setAgId(int agId) {
        this.agId = agId;
    }

    public int getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(int isAccepted) {
        this.isAccepted = isAccepted;
    }
}
