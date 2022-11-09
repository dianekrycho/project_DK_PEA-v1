package com.example.project_dk_peav1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public List<Student> loadStudents(){
        List<Student> studentAll= new ArrayList<Student>();
        Connection myConn= this.Connector();
        try {
            Statement myStmt= myConn.createStatement();
            String sql = "select * from studentstable";
            ResultSet myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Student s = new Student(myRs.getInt("id"),
                        myRs.getString("name_"),
                        myRs.getString("gender"),
                        myRs.getString("email"),
                        myRs.getDate("birthDate"),
                        myRs.getString("photo"),
                        myRs.getDouble("mark"),
                        myRs.getString("commentary")
                );
                studentAll.add(s);
            }
            this.close(myConn, myStmt, myRs);
            return studentAll;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }
    public Connection Connector(){
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "dianoetjojo");
            return connection;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myStmt != null)
                myStmt.close();
            if (myRs != null)
                myRs.close();
            if (myConn != null)
                myConn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addStudent(Student student){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "INSERT INTO studenttable (name,gender) VALUES (?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, student.getName());
            myStmt.setString(2, student.getGender());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
}
