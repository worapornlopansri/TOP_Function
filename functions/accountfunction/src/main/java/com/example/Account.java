package com.example;

public class Account {
    private final String id;
    private final String name;
    private final String Location__Latitude__s;
    private final String Location__Longitude__s;

    public Account(String id, String name, String Location__Latitude__s, String Location__Longitude__s) {
        this.id = id;
        this.name = name;
        this.Location__Latitude__s = Location__Latitude__s;
        this.Location__Longitude__s = Location__Longitude__s;
    }

    public String getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }


    public String getLocation__Latitude__s() {
        return this.Location__Latitude__s;
    }


    public String getLocation__Longitude__s() {
        return this.Location__Longitude__s;
    }


}
