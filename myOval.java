package com.example.assignment_2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class myOval extends myShape{


    myPoint center;
    public static double width;
    public static double height;

    private myColor color;



    myOval(float x, float y, double width, double height, myColor color) {
        super();
        this.color = color;
        this.center = new myPoint(x,y);
        this.width = width;
        this.height = height;
        this.center = new myPoint((float) (x + width/2), (float) (y + height/2));
    }

    myOval(myPoint p, double r, myColor color){
        center = p;
        width = r;
        height = r;
        this.color = color;
    }

    public boolean similarObject(myShape S) {

        if (S.getClass().toString().equals("class MyCircle")) {
            myCircle C = (myCircle) S;
            return true;
        } else {
            return false;
        }

    }

    public double getArea(){
        return Math.PI * width * height * 0.5;
    }
    public double getPerimeter(){
        return 2 * Math.PI * Math.sqrt((width + height)/2);
    }
    public myPoint getCenter() { return center; }
    public double getRadius(double angle) {
        double semiW = width/2;
        double semiH = height/2;
        double temp = Math.pow(semiW,2) * Math.pow(Math.sin(Math.toRadians(angle)), 2);
        temp += Math.pow(semiH,2) * Math.pow(Math.cos(Math.toRadians(angle)), 2);
        return (semiH * semiW) / (Math.sqrt(temp));
    }
    public void setAxes(double width, double height){
        this.width = width;
        this.height = height;
    }
    public void setCenter(double x, double y){
        this.center.setX((float) x);
        this.center.setY((float) y);
    }

    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(color.getRealColor());
        gc.fillOval(center.getX(), center.getY(), myOval.width,
                myOval.height);
        gc.strokeOval(center.getX(), center.getY(), myOval.width,
                myOval.height);
    }

    @Override
    public String toString() {
        return "Axes: " + width + " , " + height + " Perimeter: \t" + perimeter() + "Area: \t" + area();
    }

    public Rectangle getMyBoundingRectangle(){
        myPoint tlc = new myPoint(center.getX()+width, center.getY()+height);
        myRectangle rc = new myRectangle(tlc, width, height, myColor.MEDIUMVIOLETRED);
        return rc.getRectangle();
    };

    public boolean pointInMyShape(myPoint p){
        if(Math.abs(p.getX()) <= Math.abs(center.getX()+width) ){
            return true;
        }
        else if(Math.abs(p.getY()) <= Math.abs(center.getY()+height) ){
            return true;
        }
        return false;
    }




    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }

}
