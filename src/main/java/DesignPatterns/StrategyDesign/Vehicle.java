package DesignPatterns.StrategyDesign;

import DesignPatterns.StrategyDesign.DriveStrategy.DriveStrategy;

public class Vehicle {
    DriveStrategy driveObj;

    Vehicle(DriveStrategy driveObj) {
        this.driveObj = driveObj;
    }

    public void drive() {
        driveObj.drive();
    }
}
