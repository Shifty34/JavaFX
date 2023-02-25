package com.example.assignment1;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.util.Optional;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;


enum myColor{
    MAROON(128,0,0,255),
    MEDIUMVIOLETRED(199,21,133,255),
    DARKORANGE(255,140,0,255),
    GREEN(0,128,0,255),
    GOLD(255,215,0,255);

    private int r;
    private int g;
    private int b;
    private int a;
    private int argb;

    myColor(int r, int g, int b, int l){

        setR(r);
        setG(g);
        setB(b);
        setA(l);
        setARGB(r, g, b,l);
    }

    private void setB(int b) {
        if(b >= 0 && b<= 255){
            this.b = b;
        }
    }

    private void setG(int g) {
        if(g >= 0 && g<= 255){
            this.g = g;
        }
    }

    private void setR(int r) {
        if(r >= 0 && r<= 255){
            this.r = r;
        }
    }

    private void setARGB(int r, int g, int b, int l){
        this.argb = (r << 24) & 0xFF000000 |
                (g << 16) & 0x00FF0000|
                (b << 8) & 0x0000FF00|
                l;
        //opacity is always max
    }
    
    //Getter Functions
    public int getG() {
        return g;
    }
    public int getR() {
        return r;
    }
    public int getB() {
        return b;
    }
    public int getArgb() {
        return argb;
    }
    
    public String getHexColor(){ 
        
        return "0x" + Integer.toHexString(argb).toUpperCase();
    }

    public void setA(int a) {
        this.a = a;
    }

    public Color getRealColor(){
        return Color.valueOf(this.getHexColor());
    }
    
    
    
}

public class myPoint{
    private double x;
    private double y;
    private myColor color;

    public myPoint(float px, float py) {
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
}

public abstract class myShape{
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

    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(rc.getRealColor());
        gc.setStroke(rc.getRealColor());
        gc.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
                rectangle.getHeight());
        gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
                    rectangle.getHeight());
    }




    @Override
    public double area() {return width * height; }

    @Override
    public double perimeter() {return 2* (width + height); }


}


public class myOval extends myShape{

    
        private  myPoint center;
        private static double width;
        private static double height;

        private myColor color;

        myOval(float x, float y, double width, double height, myColor color) {
            super();
            this.color = color;
            this.center = new myPoint(x,y);
            this.width = width;
            this.height = height;
            this.center = new myPoint((float) (x + width/2), (float) (y + height/2));
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
        //gc.setStroke(colo.getRealColor());
        //gc.strokeRect(super.getX(), super.getY(), width, height);
        gc.fillOval(center.getX(), center.getY(), myOval.getWidth(),
                myOval.getHeight());
        gc.strokeOval(center.getX(), center.getY(), myOval.getWidth(),
                    myOval.getHeight());
//            gc.fillOval(100, 100, 100,
//                    200);
        }

    private static double getHeight() {
            return height;
    }

    private static double getWidth() {
        return width;
    }

}


public class Assignment1 extends Application {
    public void start(Stage stage) throws IOException {
        myPoint xy = new myPoint(100,100);
        myPoint zy = new myPoint(115,115);
//        myPoint zy = new myPoint(100,4);
//        myPoint nx = new myPoint(100,4);
//        Line line1 = xy.makeLine(zy);
//        Line line2 = xy.makeLine(nx);
        //FXMLLoader fxmlLoader = new FXMLLoader(Assignment1.class.getResource("hello-view.fxml"));
//
//        Group gline = new Group(line1);
//        Group nLine = new Group(line2);

        Group root = new Group();
        Scene s = new Scene(root, 300, 300, Color.WHITE);

        final Canvas canvas = new Canvas(250,250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        myOval myOV = new myOval(5,5, 145,145, myColor.DARKORANGE);
        myOV.Draw(gc);
        System.out.println(myOV.getArea());
        System.out.println(myOV.getCenter());
        System.out.println(myOV.perimeter());


        myRectangle myRC = new myRectangle(xy, 100, 100, myColor.MEDIUMVIOLETRED);
        myRC.Draw(gc);
        System.out.println(myRC.area());
        System.out.println(myRC.perimeter());

        myOval myOV2 = new myOval(50,50, 100,100, myColor.GOLD);
        myOV2.Draw(gc);
        System.out.println(myOV2.getArea());
        System.out.println(myOV2.getCenter());
        System.out.println(myOV2.perimeter());

        myRectangle myRC2 = new myRectangle(zy, 70, 70, myColor.GREEN);
        myRC2.Draw(gc);
        System.out.println(myRC.area());
       System.out.println(myRC.perimeter());

        myOval myOV3 = new myOval(80,80, 70,70, myColor.MAROON);
        myOV3.Draw(gc);
        System.out.println(myOV3.getArea());
        System.out.println(myOV3.getCenter());
        System.out.println(myOV3.perimeter());




        Ellipse rectangle = new Ellipse();
//        gc.setFill(Color.BLUE);
//        gc.setStroke(Color.BLUE);
        //gc.strokeRect(super.getX(), super.getY(), width, height);
//        gc.fillOval(150, 150, 100,
//                100);

        root.getChildren().add(canvas);

        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Scene scene = new Scene(gline, 320, 240);
        //Scene nscene = new Scene(nLine, 320, 240);
        stage.setTitle("Assignment_1");
        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);


        myPoint xy = new myPoint(100,100);
        myRectangle myRC = new myRectangle(xy, 100, 100, myColor.MEDIUMVIOLETRED);
        System.out.println(myRC.area());


        Color c = Color.BLUE;
        System.out.println(Color.BLUE);

        //myPoint xy = new myPoint(3,4);
        xy.myToString();

        double test = xy.distanceToOrigin();
        System.out.println(test);

        double degrees = xy.AngleOfPoint();
        System.out.println(degrees);


        myColor blue = null;
        blue = blue.MEDIUMVIOLETRED;

        System.out.println(blue.getHexColor());
        System.out.println(Color.MEDIUMVIOLETRED);


    }
}
