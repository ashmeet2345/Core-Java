package DesignPatterns.Factory;

public class ShapeFactory{

    Shape getShape(String shapeName){

        switch(shapeName){
            case "Rectangle" -> {
                return new Rectangle();
            }
            case "Circle" -> {
                return  new Circle();
            }
            default -> {
                return null;
            }
        }
    }
}
