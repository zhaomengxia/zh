package com.zh.entity.test;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/21 15:24
 */
public class ColorFactory implements ShapeAndColor {
    @Override
    public Shape getShape(String shape) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if (color.equalsIgnoreCase("RED")){
            return new Red();
        }
        if (color.equalsIgnoreCase("Green")){
            return new Green();
        }
        return null;
    }
}
