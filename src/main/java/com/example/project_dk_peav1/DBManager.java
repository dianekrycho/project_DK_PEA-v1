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
            String sql = "select * from students";
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
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "password");
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
        if (student.getBirth() == null){
            student.setBirth(Date.valueOf("01/01/1900"));
        }
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "INSERT INTO students (id,name_,gender,email,birthDate,photo,mark,commentary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, 0);
            myStmt.setString(2, student.getName());
            myStmt.setString(3, student.getGender());
            myStmt.setString(4, student.getEmail());
            myStmt.setDate(5, student.getBirth());
            myStmt.setString(6, student.getPhoto());
            myStmt.setDouble(7, student.getMark());
            myStmt.setString(8, student.getComment());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }

    public void deleteStudent(Student student){
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        int id = student.getId();
        try {
            myConn = this.Connector();
            String sql = "DELETE FROM students WHERE id = ?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, id);
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }
    public void editStudent(Student student){
        if (student.getBirth() == null){
            student.setBirth(Date.valueOf("01/01/1900"));
        }
        Connection myConn=null;
        PreparedStatement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = this.Connector();
            String sql = "UPDATE students SET name_ = ?,gender = ?,email = ?,birthDate = ?,photo = ?,mark = ?,commentary = ? WHERE id  = ?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, student.getName());
            myStmt.setString(2, student.getGender());
            myStmt.setString(3, student.getEmail());
            myStmt.setDate(4, student.getBirth());
            myStmt.setString(5, student.getPhoto());
            myStmt.setDouble(6, student.getMark());
            myStmt.setString(7, student.getComment());
            myStmt.setInt(8, student.getId());
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
