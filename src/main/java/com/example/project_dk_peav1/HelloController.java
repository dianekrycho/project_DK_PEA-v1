package com.example.project_dk_peav1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class HelloController {
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

    @FXML
    void onAddButtonClick(ActionEvent event) {
        Student etudiant;
        String photo = " ";
        if(nameTextField.getText()!="" && genderChoiceBox.getValue()!="default") {
            etudiant = new Student(nameTextField.getText(), genderChoiceBox.getValue(), emailTextField.getText(), birthDatePicker.getValue(), photo, Double.parseDouble(markTextField.getText()), commentTextArea.getText());
            // envoyer étudiant sur SQL
        }

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

