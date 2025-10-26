package com.project.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import com.project.db.DatabaseConnection;
import java.sql.PreparedStatement;

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

    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public void intializeDatabase() {
        databaseConnection.openConnection();
        databaseConnection.openStatement();

        databaseConnection.makeQuery("CREATE TABLE IF NOT EXISTS pcinfo (" +
                "id INT," +
                "name VARCHAR(55) NOT NULL," + 
                "type VARCHAR(55) NOT NULL" +
                ")"
        );
    }

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

    protected void showPopUpWindow(AlertType alertType, String header, String title, String message) {
    	Alert alert = new Alert(alertType);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(message);

    	alert.showAndWait();
    }

    @FXML
    protected void onAddButtonClick(ActionEvent event) {
        intializeDatabase();
    	if (fieldsAreNull()) {
    		showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Все поля с вводом информации пустые!");
    	} else if (nameFieldAndIdFieldAreNull()) {
    		showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Name и ID - пустые!");
    	} else if (typeFieldAndNameFieldAreNull()) {
    		showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и Name - пустые!");
    	} else if (typeFieldAndIdFieldAreNull())  {
    		showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и ID - пустые!");
    	} else {
    		databaseConnection.makeQuery("INSERT INTO pcinfo VALUES (" + 
                nameField.getText() +
                "," +
                typeField.getText() +
                ")"
            );
    	}
        databaseConnection.closeResources();
    }

    @FXML
    protected void onChangeButtonClick(ActionEvent event) {
        intializeDatabase();
        if (fieldsAreNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Все поля с вводом информации пустые!");
        } else if (nameFieldAndIdFieldAreNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Name и ID - пустые!");
        } else if (typeFieldAndNameFieldAreNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и Name - пустые!");
        } else if (typeFieldAndIdFieldAreNull())  {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и ID - пустые!");
        } else {
            databaseConnection.makeQuery("UPDATE pcinfo SET type = " + typeField.getText() + ", name = " = nameField.getText() + " WHERE id = " + idField.getText());
        }
        databaseConnection.closeResources();
    }

    @FXML
    protected void onDeleteButtonClick(ActionEvent event) {
        intializeDatabase();
        if (fieldsAreNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Все поля с вводом информации пустые!");
        } else if (nameFieldAndIdFieldAreNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Name и ID - пустые!");
        } else if (typeFieldAndNameFieldAreNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и Name - пустые!");
        } else if (typeFieldAndIdFieldAreNull())  {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и ID - пустые!");
        } else {
            databaseConnection.makeQuery("DELETE * FROM pcinfo WHERE id = " + idField.getText() + ", name = " + nameField.getText() + ", type = " + typeField.getText());
        }
        databaseConnection.closeResources();
    }

}
