package com.example;

import java.util.LinkedList;

abstract class Shape{

    protected int Type;
    public Shape(int Type){
        this.Type = Type;
    }

    public static final int SHAPE_RIGHT_TRIANGLE = 1;
    public static final int SHAPE_SQUARE = 2;
    public static final int SHAPE_CIRCLE = 3;

}

class RightTriangle extends Shape{

    public int height, width;

    public RightTriangle(int height, int width){
        super(SHAPE_RIGHT_TRIANGLE);
        this.height = height;
        this.width = width;
    }

}

class Square extends Shape{

    public int side;

    public Square(int side){
        super(SHAPE_SQUARE);
        this.side = side;
    }

}

class Circle extends Shape{

    public int radius;

    public Circle(int radius){
        super(SHAPE_CIRCLE);
        this.radius = radius;
    }

}

public class PolymorphismRefactoringPractice {

    public static void main(String[] args) {

        LinkedList<Shape> shapeList = new LinkedList<Shape>();

        shapeList.add(new RightTriangle(3, 4));
        shapeList.add(new Square(9));
        shapeList.add(new Circle(4));

        for (Shape s : shapeList) {
            printShape(s);
        }
    }

    static void printShape(Shape s){

        if(s.Type == Shape.SHAPE_RIGHT_TRIANGLE){
            RightTriangle rt = (RightTriangle) s;
            System.out.println("Got a Right Triangle, Area is " +
                                rt.height * rt.width / 2);
        }
        else if(s.Type == Shape.SHAPE_SQUARE){
            Square ss = (Square) s;
            System.out.println("Got a Square, Area is " +
                    ss.side * ss.side);
        }
        else if(s.Type == Shape.SHAPE_CIRCLE){
            Circle c = (Circle) s;
            System.out.println("Got a Circle, Area is " +
                    c.radius * c.radius * Math.PI);
        }

    }

}
