package DesignPatterns.StrategyDesign.DriveStrategy;

public class NormalDriveStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Normal Drive strategy");
    }
}
