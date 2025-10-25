package com.project.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainFxmlController {

    @FXML
    private Button addButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField typeField;

    public boolean fieldsAreNull() {
    	if (idField.getText().equals("") && nameField.getText().equals("") && typeField.getText().equals("")) return true;
    	return false;
    }

    public boolean typeFieldAndNameFieldAreNull() {
    	if (typeField.getText().equals("") && nameField.getText().equals("")) return true;
    	return false;
    }

    public boolean nameFieldAndIdFieldAreNull() {
    	if (idField.getText().equals("") && nameField.getText().equals("")) return true;
    	return false;
    }

    public boolean typeFieldAndIdFieldAreNull() {
    	if (idField.getText().equals("") && typeField.getText().equals("")) return true;
    	return false;
    }

    protected void showError(String header, String title, String message) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(message);

    	alert.showAndWait();
    }

    // doing nothing
    public void doNothing() {

    }

    @FXML
    protected void onAddButtonClick(ActionEvent event) {
    	if (fieldsAreNull()) {
    		showError("Упс!","Ошибка!","Все поля с текстом пустые!");
    	} else if (nameFieldAndIdFieldAreNull()) {
    		showError("Упс!","Ошибка!","Поля: Name и ID - пустые!");
    	} else if (typeFieldAndNameFieldAreNull()) {
    		showError("Упс!","Ошибка!","Поля: Type и Name - пустые!");
    	} else if (typeFieldAndIdFieldAreNull())  {
    		showError("Упс!","Ошибка!","Поля: Type и ID - пустые!");
    	} else {
    		doNothing();
    	}
    }

    @FXML
    protected void onChangeButtonClick(ActionEvent event) {

    }

    @FXML
    protected void onDeleteButtonClick(ActionEvent event) {

    }

}
