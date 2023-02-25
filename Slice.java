package com.example.project_4;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import java.text.DecimalFormat;


public class Slice {

    Character key;              // Holds the char of the legend
    int radius;                 // Radius of each slicer
    static int textheight = 25; // Height adjustment for each char in the legend
    double startAngle;          // Starting anlge of the slice
    double realAngle;           // Actual angle of the slice
    double startAngleRad, realAngleRad, endAngleRad; // Angles in radians for calculations
    double probability;         // probability counter for legend
    MyPoint centerPoint;        // Center point of each slice
    MyColor color;              // Color of the slice
    private static DecimalFormat df = new DecimalFormat("0.0000");

    // Constructor
    Slice (MyPoint centerPoint, int radius, double startAngle, double realAngle, MyColor color, Character key, double probability) {
        this.centerPoint = centerPoint;
        this.radius = radius;
        this.startAngle = startAngle;
        this.realAngle = realAngle;
        this.color = color;
        this.startAngleRad = Math.toRadians(startAngle);
        this.realAngleRad = Math.toRadians(realAngle);
        this.endAngleRad = startAngleRad + realAngle;
        this.key = key;
        this.probability = probability;
    }

    // Getter Methods
    public MyPoint getCenter() { return centerPoint; }
    public int getRadius() { return radius; }
    public double getStartAngle() { return startAngle; }
    public double getRealAngle() { return realAngle; }
    public MyColor getColor() { return color; }
    public double getArcLength() { return (double) radius * realAngleRad; }
    public Character getKey() { return key; }
    public double getProbability() { return probability; }

    // Area of each slice
    public double area() {return 0.5 * realAngleRad * Math.pow(radius, 2);}

    // Perimeter of each slice
    public double perimeter() { return getArcLength(); }

    @Override
    public String toString() {
        return "Slice\n" +
                "CenterPoint: " + centerPoint +
                ",\nRadius: " + radius +
                ",\nStartAngle: " + startAngle +
                ",\nRealAngle: " + realAngle +
                ",\nStartAngleRad: " + startAngleRad +
                ",\nRealAngleRad: " + realAngleRad +
                ",\nEndAngleRad: " + endAngleRad +
                ",\nColor: " + color + "\n";
    }

    public void draw(GraphicsContext GC) {
        String probabilityString = df.format(probability);

        // Drawing Each Slice
        GC.setFill(color.getColor());
        GC.fillArc(centerPoint.getX() - radius, centerPoint.getY() - radius,
                2 * radius, 2 * radius, startAngle, realAngle, ArcType.ROUND);

        // Drawing the Legend
        GC.fillText(String.valueOf(key).toUpperCase(), 40, textheight+= 20);
        GC.fillText((": " + probabilityString), 50, textheight);
        GC.fillRect(10,textheight - 10, 25,12);

    }
}
