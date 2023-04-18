package com.example;

public class Approver {
    private String DeveloperName;
    private String Requester_Manager__c;
    private String Requester_VP__c;
    private String DGVP__c;

    public Approver(String DeveloperName, String Requester_Manager__c, String Requester_VP__c, String DGVP__c) {
        this.DeveloperName = DeveloperName;
        this.Requester_Manager__c = Requester_Manager__c;
        this.Requester_VP__c = Requester_VP__c;
        this.DGVP__c = DGVP__c;
    }

    public String getDeveloperName() {
        return this.DeveloperName;
    }

    public void setDeveloperName(String DeveloperName) {
        this.DeveloperName = DeveloperName;
    }

    public String getRequester_Manager__c() {
        return this.Requester_Manager__c;
    }

    public void setRequester_Manager__c(String Requester_Manager__c) {
        this.Requester_Manager__c = Requester_Manager__c;
    }

    public String getRequester_VP__c() {
        return this.Requester_VP__c;
    }

    public void setRequester_VP__c(String Requester_VP__c) {
        this.Requester_VP__c = Requester_VP__c;
    }

    public String getDGVP__c() {
        return this.DGVP__c;
    }

    public void setDGVP__c(String DGVP__c) {
        this.DGVP__c = DGVP__c;
    }

}
