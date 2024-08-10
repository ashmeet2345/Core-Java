package DesignPatterns.StrategyDesign;

import DesignPatterns.StrategyDesign.DriveStrategy.DriveStrategy;
import DesignPatterns.StrategyDesign.DriveStrategy.NormalDriveStrategy;

public class GoodsVehicle extends Vehicle{
    GoodsVehicle() {
        super(new NormalDriveStrategy());
    }
}
