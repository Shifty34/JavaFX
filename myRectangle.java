    package com.example.assignment_2;
    import javafx.scene.canvas.GraphicsContext;
    import java.util.Optional;
    import javafx.scene.shape.Rectangle;

    public class myRectangle extends myShape {

        myPoint pTLC;
        double width, height;
        myColor rc;

        Rectangle rectangle;

        myRectangle(myPoint p, double w, double h, myColor rc) {

            super();

            this.pTLC= p; this.width = w; this.height=h;
            this.rc = Optional.ofNullable(rc).orElse(myColor.GOLD);
            rectangle = new Rectangle();
            rectangle.setX(pTLC.getX());
            rectangle.setY(pTLC.getY());
            rectangle.setHeight(h);
            rectangle.setWidth(w);
            rectangle.setFill(rc.getRealColor());


        }

        public myPoint getpTLC() {
            return pTLC;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        public myColor getColor() {
            return rc;
        }

        public Rectangle getRectangle(){
            return this.rectangle;
        }

        @Override
        public String toString() {
            return "TLC: ("+ pTLC.getX() + "," + pTLC.getY() + "),\tWidth: " + width +
                    ",\tHeight: " + height + ",\tArea: " + area() + ",\tperimeter: " + perimeter();
        }

        @Override
        public void Draw(GraphicsContext gc) {
            gc.setFill(rc.getRealColor());
            gc.setStroke(rc.getRealColor());
            gc.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
                    rectangle.getHeight());
            gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
                    rectangle.getHeight());
        }


        public Rectangle getMyBoundingRectangle(){
            return rectangle;
        }

        public boolean pointInMyShape(myPoint p){
            if(Math.abs(p.getY()) <= Math.abs(this.pTLC.getY())){
                return true;
            }
            else if(Math.abs(p.getX()) <= Math.abs(this.pTLC.getX())){
                return true;
            }

            return false;
        }




        @Override
        public double area() {return width * height; }

        @Override
        public double perimeter() {return 2* (width + height); }


    }
