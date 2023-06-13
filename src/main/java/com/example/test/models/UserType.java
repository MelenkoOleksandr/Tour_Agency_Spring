package com.example.test.models;

public enum UserType {
    ADMIN("ADMIN"),
    TRAVEL_AGENT("TRAVEL_AGENT"),
    CUSTOMER("CUSTOMER");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UserType getUserTypeByName(String name) {
        for (UserType userType : UserType.values()) {
            if (userType.getName().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}
