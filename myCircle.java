package com.example.assignment_2;
import java.util.Optional;

public class myCircle extends myOval{

    myPoint center;
    int radius;
    myColor color;

        public myCircle(myPoint p, int r, myColor color){
            super(p, r, color);



            this.center = p;
            this.radius = r;
            this.color = Optional.ofNullable(color).orElse(myColor.GREEN);

        }

    public myPoint getCenter() {return center;}
    public int getRadius() {return radius;}
    public myColor getColor(){return color;}

        @Override
        public boolean similarObject(myShape S) {

            if (S.getClass().toString().equals("class MyCircle")) {
                myCircle C = (myCircle) S;
                return (this.radius == C.getRadius());
            } else {
                return false;
            }

        }

    @Override
    public String toString(){
        return "Circle Center: (" + center.getX() + ", " + center.getY() + ")" + "\n"
                + "Radius: " + radius + "\n" + "Perimeter: " + perimeter() + "\n" +
                "Area: " + area();
    }


}
