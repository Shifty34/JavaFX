package com.example.assignment_2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

public class myArc extends myShape{

    myPoint center;
    myPoint p1, p2;
    double startAngle;
    double angle;
    double rstartAngle, rAngle, rendAngle;

    myColor color;

    myOval o;
    double a,b;


    myArc(myOval o, double startAngle, double angle, myColor color) {

        center = new myPoint();

        this.o = o;
        this.a = o.width;
        this.b = o.height;
        this.center = o.getCenter();
        this.color = color;

        this.startAngle = startAngle;
        this.angle = angle;
        this.rstartAngle = Math.toRadians(startAngle);
        this.rendAngle = Math.toRadians(startAngle + angle);

        double x = center.getX();
        double y = center.getY();

        int x1 = (int) (x + (double) (a * b) / Math.sqrt(Math.pow(b, 2) + Math.pow(a * Math.tan(rstartAngle), 2)));
        int y1 = (int) (y + (double) (a * b * Math.tan(rstartAngle)) / (Math.sqrt(Math.pow(b, 2) + Math.pow(a * Math.tan(rstartAngle), 2))));
        int x2 = (int) (x + (double) (a * b) / Math.sqrt(Math.pow(b, 2) + Math.pow(a * Math.tan(rendAngle), 2)));
        int y2 = (int) (y + (double) (a * b * Math.tan(rendAngle)) / Math.sqrt(Math.pow(b, 2) + Math.pow(a * Math.tan(rendAngle), 2)));

        this.p1 = new myPoint((float) x1, (float) y1);
        this.p2 = new myPoint(x2, y2);

    }

    public double RadiusX(){
       return 0;
    }
        myArc(myOval o, myPoint p1, myPoint p2, myColor color) {

            center = new myPoint();

            this.o = o;
            this.a = o.getWidth();
            this.b = o.getHeight();
            this.center = o.getCenter();

            this.p1 = p1;
            this.p2 = p2;
            this.startAngle = center.angleX(p1);
            this.angle = center.angleX(p2) - startAngle;

            this.color = Optional.ofNullable(color).orElse(myColor.GREEN);

            this.rstartAngle = Math.toRadians(startAngle);
            this.rAngle = Math.toRadians(angle);
            this.rendAngle = Math.toRadians(startAngle + angle);
        }

        myArc(myArc A, myColor color){

            center = new myPoint();

            this.o = A.getOval();
            this.a = o.getWidth();
            this.b = o.getHeight();
            this.center = o.getCenter();
            this.color = Optional.ofNullable(color).orElse(myColor.GOLD);

            this.startAngle = A.startAngle;
            this.angle= A.getAngle();
            this.rstartAngle= Math.toRadians(startAngle);
            this.rAngle = Math.toRadians(angle);
            this.rendAngle = Math.toRadians(startAngle+angle);

            this.p1 =  A.getStartPoint();
            this.p2 = A.getEndPoint();
        }

        public myOval getOval() {return o;}
        public myPoint getCenter() {return center;}
        public double getStartAngle() {return startAngle;}
        public double getAngle() {return angle;}
        public myPoint getStartPoint() {return p1;}
        public myPoint getEndPoint() {return p2;}
        public myColor getColor() {return color;}


        @Override
        public double area() {
            double HpM = (double) (b + a);
            double HnM = (double) (b-a);
            double HxW = (double) (a*b);

            return (int) (0.5*HxW*(rAngle-(Math.atan(HnM*Math.sin(2*rendAngle)) / (HpM + HnM *Math.cos(2.0*rendAngle)))-
                            Math.atan(HnM * Math.sin(2.0*rstartAngle)) / (HpM + HnM * Math.cos(2.0*rstartAngle))));
        }

        @Override
        public double perimeter() {return (int) (rAngle * Math.sqrt(0.5 * (double) (a*a + b*b)));}

         public Rectangle getMyBoundingRectangle() {return o.getMyBoundingRectangle();}

        public boolean pointInMyShape(myPoint p){
            double pAngle = center.angleX(p);
            double dx = center.getX() - p.getX();
            double dy = center.getY() - p.getY();

            return Math.pow(b*dx, 2) + Math.pow(a * dy, 2) <= Math.pow(a*b,2) && pAngle >= startAngle
                    && pAngle <= startAngle + angle;
        }

    @Override
    public String toString() {

        return "Center: (" + center.getX() + "," + center.getY() + ")" + "Angle: " + angle + "Perimeter: "+ perimeter()+
                "Area: " + area();
    }


    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(this.color.getRealColor());
        gc.setStroke(this.color.getRealColor());
        gc.strokeArc(center.getX(), center.getY(), o.getWidth(),
                o.getHeight(),this.startAngle, this.angle, ArcType.ROUND);
        gc.fillArc(center.getX(), center.getY(), o.getWidth(),
                o.getHeight(),this.startAngle, this.angle, ArcType.ROUND);
    }
}
