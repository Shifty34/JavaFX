package com.example.project_4;

import javafx.scene.canvas.GraphicsContext;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class StudentsDatabase {

    private final String url;
    private final String username;
    private final String password;
    private Connection connection;
    private PreparedStatement preStatement;
    private ResultSet rs;

    Map<Character, Integer> grades = new HashMap<Character, Integer>();

    // Constructor
    public StudentsDatabase(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        this.connection =getConnection(url, username, password);
    }

    // Get DB Connection
    private Connection getConnection(String host, String username, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(host, username, password);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return conn;
    }

    // Create ALl Tables
    public void createAllTables() throws SQLException {
        String[] validateTables = {
                "DROP TABLE IF EXISTS classes;",
                "DROP TABLE IF EXISTS student;",
                "DROP TABLE IF EXISTS courses;",
                "DROP TABLE IF EXISTS schedule;",
                "DROP TABLE IF EXISTS gradeAggregate;"};

        // Dropping Tables
        try {
            for (String validateTable : validateTables) {
                preStatement = connection.prepareStatement(validateTable);
                preStatement.executeUpdate();
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        // Schedule Table
        String createScheduleTable = """
                CREATE TABLE schedule (courseId CHAR(12),
                sectionNumber VARCHAR(8),
                title VARCHAR(64),
                year INT,
                semester CHAR(6),
                instructor VARCHAR(24),
                department CHAR(16),
                program VARCHAR(48),
                PRIMARY KEY(courseId, sectionNumber))""";

        // Course Table
        String createCourseTable = """
                CREATE TABLE courses (courseId CHAR(12) PRIMARY KEY,
                courseTitle VARCHAR(64),
                department CHAR(16),program VARCHAR(48));""";

        // Student Table
        String createStudentTable = """
                CREATE TABLE student(
                studentId INT PRIMARY KEY,
                firstName VARCHAR(32),
                lastName VARCHAR(32),
                email VARCHAR(64),
                gender CHAR CHECK(gender = 'F' OR gender = 'M' OR gender = 'U')
                );""";

        // Classes Table
        String createClassesTable = """
                CREATE TABLE classes (studentId INT, FOREIGN KEY(studentId) REFERENCES student(studentId),
                courseId CHAR(12), FOREIGN KEY(courseId) REFERENCES courses(courseId),
                sectionNumber VARCHAR(8),
                year INT,
                semester CHAR(6),
                grade CHAR CHECK(grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' or grade = 'F' OR grade = 'W'),
                PRIMARY KEY(studentId, courseId, sectionNumber)
                );""";

        preStatement = connection.prepareStatement(createScheduleTable);
        preStatement.executeUpdate();
        preStatement = connection.prepareStatement(createCourseTable);
        preStatement.executeUpdate();
        preStatement = connection.prepareStatement(createStudentTable);
        preStatement.executeUpdate();
        preStatement = connection.prepareStatement(createClassesTable);
        preStatement.executeUpdate();

    }

    // Fill All Tables with loaded data
    public void populateTables() throws IOException {

        // Setting Grade to Null
        try {
            String settingGradeToNull = "UPDATE classes SET grade = NULL;\n";
            preStatement = connection.prepareStatement(settingGradeToNull);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Disabling Foreign Keys Checking to properly insert all valid values
        try {
            String query = "SET FOREIGN_KEY_CHECKS=0\n";
            preStatement = connection.prepareStatement(query);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Populating Schedule Table
        try {
            String courseId, sectionNumber, title, year, semester, instructor, department , program ;
            BufferedReader br = new BufferedReader(new FileReader("/Users/iftheharahmed/Desktop/javaFx-student-database/src/sample/ScheduleSpring2022.txt"));
            String line;
            String query;

            boolean flag = false;
            while( (line = br.readLine()) != null ){
                if(!flag){
                    flag = true;
                    continue;
                }
                String[] temp = line.split("\t");
                courseId = temp[0];
                sectionNumber = temp[1];
                title = temp[2];
                year = (temp[3]);
                semester = temp[4];
                instructor = temp[5];
                department = temp[6];
                program = temp[7];
                int parsedYear = Integer.parseInt(year);
                query = "INSERT INTO schedule VALUES ('" + courseId + "', '" + sectionNumber + "', '" + title + "', '" +
                        parsedYear + "', '" + semester + "', '" + instructor + "', '" + department + "', '" + program + "');";
                preStatement = connection.prepareStatement(query);
                preStatement.executeUpdate();
            }
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n-----------------------------------------------\n");

        // Populating Student
        try {
            String studentId, firstName, lastName, email, gender;
            int studentIdParsed;
            char genderParsed;
            BufferedReader br = new BufferedReader(new FileReader("/Users/iftheharahmed/Desktop/javaFx-student-database/src/sample/student.txt"));
            String line;
            String query;

            while ((line = br.readLine()) != null) {
                String[] temp = line.split("\t");
                studentId = temp[0];
                firstName = temp[1];
                lastName = temp[2];
                email = (temp[3]);
                gender = temp[4];
                studentIdParsed = Integer.parseInt(studentId);
                genderParsed = gender.charAt(0);
                query = "INSERT INTO student VALUES ('" + studentIdParsed + "', '" + firstName + "', '" + lastName + "', '" +
                        email + "', '" + genderParsed + "');";

                System.out.println(query);
                preStatement = connection.prepareStatement(query);
                preStatement.executeUpdate();
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }

        System.out.println("\n-----------------------------------------------\n");


        // Populating Courses
        try {
            String query = "INSERT INTO courses SELECT courseId, title, department, program FROM schedule";
            preStatement = connection.prepareStatement(query);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Populating Classes
        try {
            String courseId, studentId, sectionNo, year, semester, grade;
            int studentIdParsed, yearParsed;

            BufferedReader br = new BufferedReader(new FileReader("/Users/iftheharahmed/Desktop/javaFx-student-database/src/sample/classes.txt"));
            String line;
            String query;

            while ((line = br.readLine()) != null) {
                String[] temp = line.split("\t");
                courseId = temp[0];
                studentId = temp[1];
                sectionNo = temp[2];
                year = temp[3];
                semester = temp[4];
                grade = temp[5];
                studentIdParsed = Integer.parseInt(studentId);
                yearParsed = Integer.parseInt(year);
                query = "INSERT INTO classes VALUES (" + studentIdParsed + ", '" + courseId + "', '" + sectionNo + "', " +
                        yearParsed + ", '" + semester + "', '" + grade + "');";
                System.out.println(query);
                preStatement = connection.prepareStatement(query);
                preStatement.executeUpdate();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        // Enabling all foreign key checks for future inputs
        try {
            String query = "SET FOREIGN_KEY_CHECKS=1\n";
            preStatement = connection.prepareStatement(query);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Updates student table
    public void updateStudentTables(String id, String fieldName, String replaceWith) {
        String query = "UPDATE student SET " + fieldName + " = '" + replaceWith + "' WHERE studentId = '" +
                id + "';";
        try {
            preStatement = connection.prepareStatement(query);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates course table
    public void updateCoursesTables(String id, String fieldName, String replaceWith) {
        String query = "UPDATE courses SET " + fieldName + " = '" + replaceWith + "' WHERE courseId = '" +
                id + "';";
        try {
            preStatement = connection.prepareStatement(query);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates class table
    public void updateClassesTables(String id, String fieldName, String replaceWith) {
        String query = "UPDATE classes SET " + fieldName + " = '" + replaceWith + "' WHERE studentId = '" +
                id + "';";
        try {
            preStatement = connection.prepareStatement(query);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Aggregates all specified grades and return it as a hash map
    public void mapAggregateGrade(String cID) {

        try {
            String query = "CREATE TABLE gradeAggregate(" +
                    "grade CHAR PRIMARY KEY," +
                    "numberStudents INT);";
            preStatement = connection.prepareStatement(query);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String groupGrades = "INSERT INTO gradeAggregate " +
                    "SELECT grade, count(*) " +
                    "FROM classes " +
                    "WHERE courseID = '" +
                    cID +
                    "' GROUP BY grade";
            preStatement = connection.prepareStatement(groupGrades);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String queryGradeAggregate = "SELECT * FROM gradeAggregate";
            preStatement = connection.prepareStatement(queryGradeAggregate);
            rs = preStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                String G = rs.getString("grade");
                int number = rs.getInt("numberStudents");
                grades.put(G.charAt(0), number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void draw(int n, int width, int height, double startingAngle, int radius, GraphicsContext GC) {
        HistogramAlphaBet H = new HistogramAlphaBet(grades);
        HistogramAlphaBet.MyPieChart pieChart = H.new MyPieChart(n, new MyPoint(width, height), startingAngle, radius);
        pieChart.draw(GC);

    }


}
