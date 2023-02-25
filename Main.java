package com.example.project_4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;


import java.util.Scanner;

public class Main extends Application {


    // Global Inputs for testing all classes and methods
    GraphicsContext GC;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        // CANVAS: variable width and height
        double x = 1000;
        double y = 700;

        // Setting up the window
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, x,y);
        Canvas canvas = new Canvas(x,y);
        GC = canvas.getGraphicsContext2D();


        // Make this user changeable
        String  host = "jdbc:mysql://localhost:3306/Schedule2022",
                username = "root",
                password = "Zombieslug5!";
        StudentsDatabase db = new StudentsDatabase(host, username,password);

        db.createAllTables();
        db.populateTables();

        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        int menu;
        String gradeAggregateOfCourse = null;

        System.out.println("Welcome to the Project 4 with Databases. \nImporting Data into database...\n");
        System.out.println("Select one of the menu options or press -1 to print PIE CHART\n");

        while (flag) {
            System.out.println("1. Update Student Data");
            System.out.println("2. Update Course Data");
            System.out.println("3. Update Class Data");
            System.out.println("4. Print Pie Chart");
            System.out.print("Option: ");
            menu = scan.nextInt();
            scan.nextLine();
            System.out.println("");

            if (menu == 1) {
                String studentID, itemField, replaceValWith;
                System.out.print("Enter Student ID: ");
                studentID = scan.next();
                System.out.print("Enter Field Name: ");
                itemField = scan.next();
                System.out.print("Replace with: ");
                replaceValWith = scan.next();
                db.updateStudentTables(studentID, itemField, replaceValWith);
                System.out.println("\nDatabase Updated.");
            }
            else if (menu == 2) {
                String courseID, fieldName, replaceCourseWith;
                System.out.print("Enter Couse ID: ");
                courseID = scan.nextLine();
                System.out.print("Enter Field Name: ");
                fieldName = scan.next();
                System.out.print("Replace with: ");
                replaceCourseWith = scan.next();
                System.out.println(courseID + " " + fieldName + " " + replaceCourseWith);
                db.updateCoursesTables(courseID, fieldName, replaceCourseWith);
                System.out.println("\nDatabase Updated.");
            }
            else if (menu == 3) {
                String studentIdClasses, fieldNameInClasses, replaceInClasses;
                System.out.print("Enter Student ID: ");
                studentIdClasses = scan.next();
                System.out.print("Enter Field Name: ");
                fieldNameInClasses = scan.next();
                System.out.print("Replace with: ");
                replaceInClasses = scan.next();
                db.updateClassesTables(studentIdClasses, fieldNameInClasses, replaceInClasses);
                System.out.println("\nDatabase Updated.");
            }
            else if (menu == 4) {
                System.out.println("Please Enter your course Id: ");
                gradeAggregateOfCourse = scan.nextLine();
                flag = false;
            }
            else {
                System.out.println("Invalid Input, Please try again.");
            }


        }

        // Input values for updating the Student Table
//        String studentID = "1", itemField = "gender", replaceValWith = "U";
//        db.updateStudentTables(studentID, itemField, replaceValWith);
//
//        // Input values for updating the Course Table
//        String courseID = "10000 PP", fieldName = "department", replaceCourseWith = "Eng";
//        db.updateCoursesTables(courseID, fieldName, replaceCourseWith);
//
//        // Input values for updating the Classes Table
//        String studentIdClasses = "2", fieldNameInClasses = "grade", replaceInClasses = "F";
//        db.updateClassesTables(studentIdClasses, fieldNameInClasses, replaceInClasses);

        //Input values for setting the aggregate grade for a class into a table
        // 3    String gradeAggregateOfCourse = "30400 F";
        db.mapAggregateGrade(gradeAggregateOfCourse);

        // Drawing the values into a pie chart
        int numberOfGradesPrinted = 6, radius = 300;
        int widthOfPie = 500;
        int height = 350;
        double startingAngle = 0;
        db.draw(numberOfGradesPrinted, widthOfPie, height, 0, radius, GC);

        // Showing drawing in window
        pane.getChildren().add(canvas);
        stage.setTitle("Drawing Shapes");
        stage.setScene(scene);
        stage.show();

    }
}