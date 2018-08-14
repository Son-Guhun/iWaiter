package com.example.songuhun.objects;

public class Order {
    private Dish baseDish;
    private String additionalDetails;
    private boolean processed;

    public Order(Dish baseDish, String additionalDetails) {
        this.baseDish = baseDish;
        this.additionalDetails = additionalDetails;
    }

    public Dish getBaseDish() {
        return baseDish;
    }

    public String getAdditionalDetails() {

        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public void setBaseDish(Dish baseDish) {
        this.baseDish = baseDish;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
