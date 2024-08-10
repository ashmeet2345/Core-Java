package DesignPatterns.StrategyDesign.DriveStrategy;

public class SportsDriveStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Sports Drive strategy");
    }
}
