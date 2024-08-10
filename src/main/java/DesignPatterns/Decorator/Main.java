package DesignPatterns.Decorator;

import DesignPatterns.Decorator.ToppingDecorator.ExtraMushroom;
import DesignPatterns.Decorator.ToppingDecorator.ToppingDecorator;

public class Main {
    public static void main(String[] args) {
        BasePizza pizza=new ExtraMushroom(new Farmhouse());
        System.out.println(pizza.cost());
    }
}
