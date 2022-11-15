package com.example.project_dk_peav1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
//import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


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
    private ListView<String> studentsList; // ObservableList<String>
    @FXML
    private Label welcomeText;

    private String imagePath;

    private List<Student> studentObjectList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> genderValues = new ArrayList<String>();
        genderValues.add("Male");
        genderValues.add("Female");
        genderValues.add("trouver une blague");
        ObservableList<String> gender = FXCollections.observableArrayList(genderValues);
        genderChoiceBox.setItems(gender);
        photoImage.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                onPhotoClick(event);
            }
        });
        studentsList.getSelectionModel().selectedItemProperty().addListener(e-> {
            if(studentsList.getSelectionModel().getSelectedIndex()!= -1){
                displayStudentDetails(studentObjectList.get(studentsList.getSelectionModel().getSelectedIndex()));
            } else {
                displayStudentDetails(studentObjectList.get(0));
            }
        });

        manager = new DBManager();
        fetchStudents();
    }

    private void displayStudentDetails(Student selectedStudent) {
        if(selectedStudent!=null){
            this.commentTextArea.setDisable(false);
            this.nameTextField.setDisable(false);
            this.genderChoiceBox.setDisable(false);
            this.emailTextField.setDisable(false);
            this.birthDatePicker.setDisable(false);
            this.markTextField.setDisable(false);
            this.saveButton.setDisable(true);
            this.editButton.setDisable(false);
            this.deleteButton.setDisable(false);
            this.nameTextField.setText(selectedStudent.getName());
            this.genderChoiceBox.setValue(selectedStudent.getGender());
            this.emailTextField.setText(selectedStudent.getEmail());
            this.birthDatePicker.setValue(selectedStudent.getBirth().toLocalDate());
            this.markTextField.setText(Double.toString(selectedStudent.getMark()));
            this.commentTextArea.setText(selectedStudent.getComment());
            this.photoImage.setImage(null);
            this.imagePath = selectedStudent.getPhoto();
            if (imagePath != null && !imagePath.equals("")){
                Image image = new Image(imagePath);
                photoImage.setImage(image);
            }
        }
    }

    public void fetchStudents(){
        List<Student> listStudents=manager.loadStudents();
        if(listStudents!=null){
            ObservableList<Student> students;
            studentsList.getItems().clear();
            students = FXCollections.observableArrayList(listStudents);
            students.forEach(student -> {
                studentsList.getItems().add(student.getName());
            });
            studentObjectList = students;
            final int[] numberOfStudents = {0};
            final double[] markTotal = {0};
            listStudents.forEach(student -> {
                if (student.getMark() != 0) {
                    numberOfStudents[0]++;
                    markTotal[0] += student.getMark();
                }
            });
            DecimalFormat d = new DecimalFormat("#.##");
            double markAverage = markTotal[0] / numberOfStudents[0];
            averageTextField.setText(d.format(markAverage));
        }

    }
    @FXML
    void onAddButtonClick(ActionEvent event) {
        studentsList.getSelectionModel().clearSelection();
        this.nameTextField.setText("");
        this.genderChoiceBox.setValue("");
        this.emailTextField.setText("");
        this.birthDatePicker.setValue(LocalDate.of(1900, Month.JANUARY, 1));
        this.markTextField.setText(String.valueOf(0));
        this.commentTextArea.setText("");
        this.photoImage.setImage(null);
        this.commentTextArea.setDisable(false);
        this.nameTextField.setDisable(false);
        this.genderChoiceBox.setDisable(false);
        this.emailTextField.setDisable(false);
        this.birthDatePicker.setDisable(false);
        this.markTextField.setDisable(false);
        this.saveButton.setDisable(false);
        this.editButton.setDisable(true);
        this.deleteButton.setDisable(true);
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        // on selectionne par defaut le premier de la liste
        fetchStudents();
        studentsList.getSelectionModel().selectFirst();
    }

    @FXML
    void onDeleteButtonCLick(ActionEvent event) {
        Student student=null;
        student = studentObjectList.get(studentsList.getSelectionModel().getSelectedIndex());
        //studentsList.getSelectionModel().getSelectedIndex();
        if(student != null) {
            manager.deleteStudent(student);
        }
        // on selectionne le premier etuiant de la liste après suppression
        fetchStudents();
        studentsList.getSelectionModel().selectFirst();
    }

    @FXML
    void onEditButtonClick(ActionEvent event) {
        //String photo = null;
        int id = studentObjectList.get(studentsList.getSelectionModel().getSelectedIndex()).getId();
        if (birthDatePicker.getValue() == null) {
            birthDatePicker.setValue(LocalDate.of(1900, Month.JANUARY, 1));
        }
        if (!Objects.equals(nameTextField.getText(), "") && !Objects.equals(genderChoiceBox.getValue(), "")){
            Student s= new Student(id,
                    nameTextField.getText(),
                    genderChoiceBox.getValue(),
                    emailTextField.getText(),
                    java.sql.Date.valueOf(birthDatePicker.getValue()),
                    imagePath,
                    Double.parseDouble(markTextField.getText()),
                    commentTextArea.getText());
            manager.editStudent(s);
            imagePath = null;
            photoImage.setImage(null);
            fetchStudents();
            studentsList.getSelectionModel().selectFirst();
        }
        else {
            //Message d'erreur
            JOptionPane.showMessageDialog(null,"Please enter name and gender","Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {
        if (birthDatePicker.getValue() == null) {
            birthDatePicker.setValue(LocalDate.of(1900, Month.JANUARY, 1));
        }
        int id = 0;
        //String photo = null;
        if (!Objects.equals(nameTextField.getText(), "") && !Objects.equals(genderChoiceBox.getValue(), "")){
            Student s= new Student(id,
                    nameTextField.getText(),
                    genderChoiceBox.getValue(),
                    emailTextField.getText(),
                    java.sql.Date.valueOf(birthDatePicker.getValue()),
                    imagePath,
                    Double.parseDouble(markTextField.getText()),
                    commentTextArea.getText());
            manager.addStudent(s);
            imagePath = null;
            photoImage.setImage(null);
            fetchStudents();
        }
        else {
            //Message d'erreur
            JOptionPane.showMessageDialog(null,"Please enter name and gender","Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    @FXML
    void onPhotoClick(MouseEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Image file", "jpg", "jpeg", "png");
        fileChooser.addChoosableFileFilter(extFilter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            this.imagePath = selectedFile.toURI().toString();
            Image image = new Image(imagePath);
            photoImage.setImage(image);
        }
    }
}

