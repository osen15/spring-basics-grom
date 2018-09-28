package com.lesson3.dependencyInversionExample;

public class PasswordReminder {

    private DbConnector dbConnector;

    public PasswordReminder(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    public void sendPassword() {
//logic


    }


}
