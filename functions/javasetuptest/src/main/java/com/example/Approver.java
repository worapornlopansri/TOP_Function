package com.example;

public class Approver {
    private final String DeveloperName;
    private final String Requester_Manager__c;
    private final String Requester_VP__c;
    private final String DGVP__c;

    public Approver(String developerName, String requesterManager, String requesterVP, String DGVP) {
        this.DeveloperName = developerName;
        this.Requester_Manager__c = requesterManager;
        this.Requester_VP__c = requesterVP;
        this.DGVP__c = DGVP;
    }

    public String getDeveloperName() {
        return this.DeveloperName;
    }

    public String getRequester_Manager__c() {
        return this.Requester_Manager__c;
    }

    public String getRequester_VP__c() {
        return this.Requester_VP__c;
    }

    public String getDGVP__c() {
        return this.DGVP__c;
    }

}
