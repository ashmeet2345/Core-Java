package DesignPatterns.StrategyDesign;

import DesignPatterns.StrategyDesign.DriveStrategy.DriveStrategy;
import DesignPatterns.StrategyDesign.DriveStrategy.SportsDriveStrategy;

public class OffroadVehicle extends Vehicle {
    OffroadVehicle() {
        super(new SportsDriveStrategy());
    }
}
