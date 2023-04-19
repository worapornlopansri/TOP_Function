package com.example;

public class FunctionInput {
    private String id;

    public FunctionInput() {}

    public FunctionInput(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "id";
    }
}
