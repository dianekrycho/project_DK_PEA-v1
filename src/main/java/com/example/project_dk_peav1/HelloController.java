package com.example.project_dk_peav1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    DBManager manager;
    @FXML
    private Button addButton;
    @FXML
    private Label averageLabel;
    @FXML
    private TextField averageTextField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private Label birthLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Label commentsLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private Label detailsLabel;
    @FXML
    private Button editButton;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private ChoiceBox<String> genderChoiceBox;
    @FXML
    private Label genderLabel;
    @FXML
    private Label listLabel;
    @FXML
    private Label markLabel;
    @FXML
    private TextField markTextField;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private ImageView photoImage;
    @FXML
    private Label photoLabel;
    @FXML
    private Button saveButton;
    @FXML
    private ListView<Student> studentsList; // ObservableList<String>
    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> genderValues = new ArrayList<String>();
        genderValues.add("Male");
        genderValues.add("Female");
        genderValues.add("trouver une blague");
        ObservableList<String> gender = FXCollections.observableArrayList(genderValues);
        genderChoiceBox.setItems(gender);

        studentsList.getSelectionModel().selectedItemProperty().addListener(e-> displayStudentDetails(studentsList.getSelectionModel().getSelectedItem()));

        manager = new DBManager();
        fetchStudents();
    }

    private void displayStudentDetails(Student selectedStudent) {
        if(selectedStudent!=null){
            nameTextField.setText(selectedStudent.getName());
            genderChoiceBox.setValue(selectedStudent.getGender());
            emailTextField.setText(selectedStudent.getEmail());
            birthDatePicker.setValue(selectedStudent.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            markTextField.setText(Double.toString(selectedStudent.getMark()));
            commentTextArea.setText(selectedStudent.getComment());
            // rajouter photos
        }
    }

    public void fetchStudents(){
        List<Student> listStudents=manager.loadStudents();
        if(listStudents!=null){
            ObservableList<Student> students;
            students = FXCollections.observableArrayList(listStudents);
            studentsList.setItems(students);
        }
    }
    @FXML
    void onAddButtonClick(ActionEvent event) {

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void onDeleteButtonCLick(ActionEvent event) {

    }

    @FXML
    void onEditButtonClick(ActionEvent event) {

    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {

    }
}

