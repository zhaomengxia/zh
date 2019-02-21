package com.zh.entity.test;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/21 15:28
 */
public class FactoryProducerDemo {
    public static void main(String[] args){
        ShapeAndColor shapeAndColor=FactoryProducer.get("SHAPE");
        Shape shape1=shapeAndColor.getShape("CIRCLE");
        shape1.draw();
        Shape shape2=shapeAndColor.getShape("RECTANGLE");
        shape2.draw();
        Shape shape3=shapeAndColor.getShape("SQUARE");
        shape3.draw();
        ShapeAndColor shapeAndColor1=FactoryProducer.get("COLOR");
        Color color1=shapeAndColor1.getColor("RED");
        color1.draw();
        Color color2=shapeAndColor1.getColor("GREEN");
        color2.draw();
    }
}
