package com.zh.entity.test;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/21 15:19
 */
public class ShapeFactory implements ShapeAndColor {
    @Override
    public Shape getShape(String shape) {
        if (shape.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        if (shape.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }
        if (shape.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
//        if (color.equalsIgnoreCase("RED")){
//            return new Red();
//        }
//        if (color.equalsIgnoreCase("Green")){
//            return new Green();
//        }
        return null;
    }
}
