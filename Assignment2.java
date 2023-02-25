package com.example.assignment_2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;




public class Assignment2 extends Application {
    public void start(Stage stage)  {

        Group root = new Group();
        Scene s = new Scene(root, 300, 300, Color.WHITE);

        final Canvas canvas = new Canvas(400,400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);

        myOval myOV = new myOval(5,5, 145,145, myColor.DARKORANGE);

        myArc myArc = new myArc(myOV,0,85,myColor.DARKORANGE);
        myArc.Draw(gc);

        myArc myArc2 = new myArc(myOV, 85, 90, myColor.MAROON);
        myArc2.Draw(gc);

        myArc myArc3 = new myArc(myOV, 170, 230,myColor.GOLD);
        myArc3.Draw(gc);

        myArc myArc4 = new myArc(myOV,210,120,myColor.GREEN);
        myArc4.Draw(gc);

        myArc myArc5 = new myArc(myOV, 30, 100,myColor.MEDIUMVIOLETRED );
        myArc5.Draw(gc);

       // Rectangle brc = myOV.getMyBoundingRectangle();
        //gc.strokeRect(brc.getX()-myOV.getWidth(),brc.getY()-myOV.getHeight(), brc.getHeight(),brc.getWidth());


        System.out.println(myArc);
        System.out.println(myArc2);
        System.out.println(myArc3);
        System.out.println(myArc4);
        System.out.println(myArc5);
      //  System.out.println(brc);



        root.getChildren().add(canvas);
        stage.setTitle("Assignment_2");
        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {


        launch(args);


    }
}
