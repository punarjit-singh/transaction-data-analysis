package org.orgname.models;

public class Transaction {

    private String timestamp;
    private String security;
    private String action;
    private double price;

    public Transaction(String timestamp, String security, String action, double price) {
        this.timestamp = timestamp;
        this.security = security;
        this.action = action;
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSecurity() {
        return security;
    }

    public String getAction() {
        return action;
    }

    public double getPrice() {
        return price;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return timestamp + "," + security + "," + action + "," + price;
    }

}
