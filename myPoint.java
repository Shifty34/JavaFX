
package com.example.assignment_2;

import javafx.scene.shape.Line;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class myPoint{
    private double x;
    private double y;
    private myColor color;

    public myPoint(float px, float py) {
        x = px;
        y = py;
    }

    public myPoint(double px, double py) {
        x = px;
        y = py;
    }

    public myPoint() {

    }


    public void setPoint(float x,float y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY(){
        return y;
    }


    public void myToString() {
        System.out.println("(" + x + "," + y + ")");
    }
    public double distanceToOrigin(){
        double dist = sqrt(pow(x,2)+pow(y,2));
        return dist;
    }

    public double AngleOfPoint(){
        double a = distanceToOrigin();
        double b = this.x;

        double n = Math.cos((a)/(b));

        return n*100;
    }


    public myColor getColor() {
        return color;
    }

    public void setColor(myColor color) {
        this.color = color;
    }

    public void shift(double px, double py) {setPoint((float) (x+px), (float) (y+py));}

    public Line makeLine( myPoint xy){
        Line line = new Line();
        line.setStartX(this.x);
        line.setStartY(this.y);
        line.setEndX(xy.getX());
        line.setEndY(xy.getY());

        return line;
    }


    public float setX(float x) {
        return x;
    }

    public float setY(float y) {
        return y;
    }

    public double angleX(myPoint p1) {
        return Math.atan(p1.y/p1.x);
    }
}