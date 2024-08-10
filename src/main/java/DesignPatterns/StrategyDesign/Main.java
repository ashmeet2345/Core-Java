package DesignPatterns.StrategyDesign;

import Interfaces.Car;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new OffroadVehicle();
        vehicle.drive();
    }
}
