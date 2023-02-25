package com.example.assignment_2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class myShape implements MyShapeInterface{
    myPoint p;
    myColor c;
    private GraphicsContext gc = null;

    myShape() {
        this.p = p;
        this.c = c.MEDIUMVIOLETRED;
    }

    myShape(double x, double y, myColor c){
        setPoint( x, y);
        this.c = c.MEDIUMVIOLETRED;}

    public void setPoint(double x, double y){
        this.p.setPoint((float) x, (float) y);
    }

    public myPoint getPoint() {
        return p;
    }

    public double getX() {
        return p.getX();
    }

    public double getY() {
        return p.getY();
    }

    public Color myColor() {
        return c.getRealColor();
    }

    public abstract void Draw(GraphicsContext gc);

    public double area(){return 0;}
    public double perimeter(){return 0;}

    public String toString() {
        System.out.println("No Shape yet created.");
        return "No Shape yet created.";
    }

    public void Draw() {
//       gc.setFill(Paint.valueOf(c.getHexColor()));
//      gc.fillRect(0,0,canvas.getWidth());
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }


    public Color getRealColor(){
        return Color.valueOf(c.getHexColor());
    }
}
