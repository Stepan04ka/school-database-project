/*
  Symbol '*' means - need's to be replaced.
  Last file change: 13:28, 27th October 2025 year.
*/

package com.project.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;

import com.project.db.DatabaseConnection;
import com.project.obj.Device;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private TableView<Device> table;

    @FXML
    private TableColumn<Device, String> nameColumn;

    @FXML
    private TableColumn<Device, String> idColumn;

    @FXML
    private TableColumn<Device, String> typeColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField typeField;

    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private ObservableList<String, Device> deviceList = FXCollections.observableArrayList();
    private Device testDevice = new Device();

    public void updateTable() {
        table = new TableView<Device>(deviceList);

        nameColumn = new TableColumn<Device, String>("Name");
        idColumn = new TableColumn<Device, String>("ID");
        typeColumn = new TableColumn<Device, String>("Type");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.getColumns().addAll(nameColumn, idColumn, typeColumn);
        table.setItems(deviceList);
    }

    public void cleanTable() {
        table = null;
        nameColumn = null;
        idColumn = null;
        typeColumn = null;
    }


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
        cleanTable();

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
        updateTable();
        System.out.println("Вписанные вами поля успешно добавились!");
        databaseConnection.closeResources();
    }

    @FXML
    protected void onChangeButtonClick(ActionEvent event) {
        intializeDatabase();
        cleanTable();

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
        updateTable();
        System.out.println("Выбранные вами поля успешно обновились!");
        databaseConnection.closeResources();
    }

    @FXML
    protected void onDeleteButtonClick(ActionEvent event) {
        intializeDatabase();
        cleanTable();

        if (idFieldNull()) {
            showPopUpWindow(AlertType.ERROR,"Упс!","Ошибка!","Поле idField - пустое! Заполните поле ID - обязательно!");
        } else if (!idFieldNull() && typeFieldNull() && nameFieldNull()) {
            databaseConnection.makeQuery("DELETE * FROM pcinfo WHERE id = " + idField.getText());
        }
        updateTable();
        System.out.println("Выбранные вами поля успешно удалилось!");
        databaseConnection.closeResources();
    }

}