package com.zh.entity.test;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/21 15:26
 */
public class FactoryProducer {
    public static ShapeAndColor get(String s){
        if (s.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        }
        if (s.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
