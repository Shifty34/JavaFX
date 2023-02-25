package com.example.assignment_2;

import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Rectangle;

public interface MyShapeInterface {
    abstract Rectangle getMyBoundingRectangle();
    abstract boolean pointInMyShape( myPoint p);

    static boolean SimilarObjects(myShape S1, myShape S2) {
        if(S1 == S2){
            return true;
        }
        return false;
    }

    static myPoint intersectMyRectangles(myRectangle R1, myRectangle R2){
        if(R1.pTLC == R2.pTLC){
            return R1.pTLC;
        }
        return null;
    }

    static myPoint[] intersectMyShapes(){
        return null;
    };
    default Canvas drawIntersectMyShapes(){
        return null;
    };
}
