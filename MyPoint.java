package com.example.project_4;

public class MyPoint {
    private double x, y;

    // Constructors
    MyPoint() { setPoint(0,0);}
    MyPoint(double x, double y) { setPoint(x, y);}
    MyPoint(MyPoint p) { setPoint(p);}

    // Getter Methods
    public double getX() { return x; }
    public double getY() { return y; }
    public MyPoint getPoint() {return new MyPoint(x,y); };

    // Setter Methods
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    private void setPoint(double x, double y) { this.x = x; this.y = y; }
    private void setPoint(MyPoint p) { this.x = p.getX(); this.y = p.getY(); }

    // toString specifv to MyPoint
    @Override
    public String toString(){
        return "MyPoint: ( " + x + ", " + y + " )";
    }

    // static function: finds distance between two points
    public static double distance(MyPoint point, MyPoint circleP) {
        double dx = circleP.getX() - point.getX();
        double dy = circleP.getY() - point.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
