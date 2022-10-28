package com.example.project_dk_peav1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id ;
    private String name;
    private String gender;
    private String email;
    private LocalDate birth;
    private String photo;
    private double mark;
    private String comment;

    public Student(String name, String gender, String email, LocalDate birth, String photo, double mark, String comment) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.photo = photo;
        this.mark = mark;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", photo='" + photo + '\'' +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                '}';
    }

    List<String> connect_to_sql(String query, int columnIndex) {
        List<String> result=new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "dianoetjojo");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result.add(rs.getString(columnIndex));
            }
            System.out.println(result);

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}

