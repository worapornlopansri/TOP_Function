package com.example;

public class Approver {
    private final String developerName;
    private final String requesterManager;
    private final String requesterVP;
    private final String DGVP;

    public Approver(String developerName, String requesterManager, String requesterVP, String DGVP) {
        this.developerName = developerName;
        this.requesterManager = requesterManager;
        this.requesterVP = requesterVP;
        this.DGVP = DGVP;
    }

    public String getDeveloperName() {
        return this.developerName;
    }

    public String getRequesterManager() {
        return this.requesterManager;
    }

    public String getRequesterVP() {
        return this.requesterVP;
    }

    public String getDGVP() {
        return this.DGVP;
    }
}
