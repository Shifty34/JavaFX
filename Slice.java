package com.example.assignment3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Slice{

    myPoint center;
    int radius;
    double startAngle;
    double angle;

    double rstartAngle, rAngle, rEndAngle;

    myColor color;

    Slice(myPoint center, int radius, double startAngle, double angle, myColor color){
            this.center = center;
            this.radius = radius;
            this.startAngle = startAngle;
            this.angle = angle;
            this.color = color;

            this.rAngle = Math.toRadians(angle);

        }


    public void  setColor(myColor color) {this.color = color;}

    public myPoint getCenter() {return center;}
    public int getRadius() {return radius;}
    public double getstartAngle() {return startAngle;}
    public double getAngle() {return angle;}

    public double getArcLength() {return (double) radius * rAngle;}

    public myColor getColor() {return color;}

    public double area() {return 0.5 *rAngle * Math.pow(radius, 2); }

    public double Perimeter() {return getArcLength();}

    public String toString(){


        return "Slice: Center(" + center.getX() + ", " + center.getY() + ") Radius: " + radius
                + " (Starting Angle) : " + startAngle + ", (Angle): " + angle;


    }


    public void Draw(GraphicsContext gc){
        gc.setFill(color.getRealColor());
        gc.fillArc(center.getX()-radius, center.getY() - radius, 2.0 * radius, 2.0*radius, startAngle, angle, ArcType.ROUND);

    }



}
