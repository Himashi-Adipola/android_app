package com.example.myapplication.model;

public class Vehicle {
        public String vehicleNumber;
        public int pId;
        public String NIC;
        public String fName;
        public String lName;
        public String policyholder_email;
        public String pAddress;
        public String pGender;
        public String pDOB;
        public int pContactNo;
        public String accessList;
        public int isDeleted;
        public Object created_at;
        public Object updated_at;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
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

    public String getPolicyholder_email() {
        return policyholder_email;
    }

    public void setPolicyholder_email(String policyholder_email) {
        this.policyholder_email = policyholder_email;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpGender() {
        return pGender;
    }

    public void setpGender(String pGender) {
        this.pGender = pGender;
    }

    public String getpDOB() {
        return pDOB;
    }

    public void setpDOB(String pDOB) {
        this.pDOB = pDOB;
    }

    public int getpContactNo() {
        return pContactNo;
    }

    public void setpContactNo(int pContactNo) {
        this.pContactNo = pContactNo;
    }

    public String getAccessList() {
        return accessList;
    }

    public void setAccessList(String accessList) {
        this.accessList = accessList;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Object getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Object created_at) {
        this.created_at = created_at;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Object updated_at) {
        this.updated_at = updated_at;
    }
}
