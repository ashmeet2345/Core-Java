package DesignPatterns.Decorator.ToppingDecorator;

import DesignPatterns.Decorator.BasePizza;

public class ExtraMushroom extends ToppingDecorator{
    BasePizza pizza;

    public ExtraMushroom(BasePizza pizza) {
        this.pizza = pizza;
    }
    @Override
    public int cost() {
        return pizza.cost()+80;
    }
}
