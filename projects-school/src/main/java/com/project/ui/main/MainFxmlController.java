package com.project.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import com.project.db.DatabaseConnection;
import com.project.obj.Device;

import java.util.List;
import java.util.ArrayList;

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
    private List<String, Device> deviceList = new ArrayList<>();
    private Device testDevice = new Device();

    public void intializeDatabase() {
        databaseConnection.openConnection();
        databaseConnection.openStatement();

        databaseConnection.makeQuery("CREATE TABLE IF NOT EXISTS pcinfo (" +
                "id INT," +
                "name VARCHAR(55) NOT NULL," + 
                "type VARCHAR(55) NOT NULL" +
                ")"
        );
        System.out.println("База данных была успешно инциализирована!");
    }

    public boolean fieldsAreNull() {
    	if (idField.getText().equals("") && nameField.getText().equals("") && typeField.getText().equals("")) return true;
    	return false;
    }

    public boolean typeFieldNull() {
        if (typeField.getText().equals("")) return true;
        return false;
    }

    public boolean nameFieldNull() {
        if (nameField.getText().equals("")) return true;
        return false;
    }

    public boolean idFieldNull() {
        if (idField.getText().equals("")) return true;
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
    	} else if (idFieldNull() && nameFieldNull() || !typeFieldNull()) {
    		showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Name и ID - пустые!");
    	} else if (typeFieldNull() && nameFieldNull() && !idFieldNull()) {
    		showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и Name - пустые!");
    	} else if (idFieldNull() && typeFieldNull() || !nameFieldNull())  {
    		showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поля: Type и ID - пустые!");
    	} else {
    		databaseConnection.makeQuery("INSERT INTO pcinfo VALUES (" + 
                nameField.getText() +
                "," +
                typeField.getText() +
                ")"
            );
            testDevice =  new Device(idField.getText(), nameField.getText(), typeField.getText());
            deviceList.add(idField.getText(), testDevice); 
            
    	}

        System.out.println("Вписанные вами поля успешно добавились!");
        databaseConnection.closeResources();
    }

    @FXML
    protected void onChangeButtonClick(ActionEvent event) {
        intializeDatabase();

        if (idFieldNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поле idField - пустое! Заполните поле ID - обязательно!");
        } else if (typeFieldNull() && !idFieldNull() && !nameFieldNull()) {
            databaseConnection.makeQuery("UPDATE pcinfo SET name = " + nameField.getText() + " WHERE id = " + idField.getText());
            deviceList.set(idField.getText(), new Device(testDevice.getId(),testDevice.setName(nameField.getText()),testDevice.getType());
        } else if (nameFieldNull() && !typeFieldNull() && !idFieldNull()) {
            databaseConnection.makeQuery("UPDATE pcinfo SET type = " + typeField.getText() + " WHERE id = " + idField.getText());
            deviceList.set(idField.getText(), new Device(testDevice.getId(),testDevice.getName(),testDevice.setType(typeField.getText())));
        } else {
            databaseConnection.makeQuery("UPDATE pcinfo SET name = " + nameField.getText() + ",type = " + typeField.getText() + " WHERE id = " + idField.getText());
            deviceList.set(idField.getText(),new Device(idField.getText(), nameField.getText(), typeField.getText()));
        }
        System.out.println("Выбранные вами поля успешно обновились!");
        databaseConnection.closeResources();
    }

    @FXML
    protected void onDeleteButtonClick(ActionEvent event) {
        intializeDatabase();

        if (idFieldNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поле idField - пустое! Заполните поле ID - обязательно!");
        } else if (!idFieldNull() && typeFieldNull() && nameFieldNull()) {
            databaseConnection.makeQuery("DELETE * FROM pcinfo WHERE id = " + idField.getText());
        }
        System.out.println("Выбранные вами поля успешно удалилось!");
        databaseConnection.closeResources();
    }

}
