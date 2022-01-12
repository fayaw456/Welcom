/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author W10X64_AUG-2020
 */
public class ASSIGNMENT1 extends Application {

    Connection con;
    PreparedStatement prs;

    @Override
    public void start(Stage primaryStage) throws IOException {

//LABLE AND BUTTON CREATION/////////////////////////////////      
        Label SIDLable = new Label("SID");
        TextField SIDText = new TextField();

        Label StudIDLable = new Label("StudID");
        TextField StudIDText = new TextField();

        Label FirstnameLable = new Label("Firstname");
        TextField FirstnameText = new TextField();

        Label LastnameLable = new Label("Lastname");
        TextField LastnameText = new TextField();

        Label SectionLable = new Label("Section");
        TextField SectionText = new TextField();

        Label DepartmentLable = new Label("Department");

        ComboBox dept = new ComboBox();
        dept.getItems().addAll("CS", "IS", "IT", "SE");

        Button buttonInsert = new Button("INSERT");
        Button buttonUpdate = new Button("UPDATE");
        Button buttonSelect = new Button("SELECT");
        Button buttonDisplay = new Button("DISPLAY");
        Button buttonExit = new Button("EXIT");

//PANE CREATION/////////////////////////////////////////////
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(500, 300);

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(SIDLable, 0, 0);
        gridPane.add(SIDText, 1, 0);

        gridPane.add(StudIDLable, 0, 1);
        gridPane.add(StudIDText, 1, 1);

        gridPane.add(FirstnameLable, 0, 2);
        gridPane.add(FirstnameText, 1, 2);

        gridPane.add(LastnameLable, 0, 3);
        gridPane.add(LastnameText, 1, 3);

        gridPane.add(SectionLable, 0, 4);
        gridPane.add(SectionText, 1, 4);

        gridPane.add(DepartmentLable, 0, 5);
        gridPane.add(dept, 1, 6);

        gridPane.add(buttonInsert, 1, 8);
        gridPane.add(buttonUpdate, 1, 9);
        gridPane.add(buttonSelect, 2, 9);
        gridPane.add(buttonDisplay, 1, 10);
        gridPane.add(buttonExit, 2, 10);

        buttonExit.setStyle("-fx-background-color:darkslateblue; -fx-text-fill:white");

        gridPane.setStyle("-fx-background-color:PINK;");

        SIDLable.setStyle("-fx-font:normal bold 16px 'serif';");
        StudIDLable.setStyle("-fx-font:normal bold 16px 'serif';");
        FirstnameLable.setStyle("-fx-font:normal bold 16px 'serif';");
        LastnameLable.setStyle("-fx-font:normal bold 16px 'serif';");
        SectionLable.setStyle("-fx-font:normal bold 16px 'serif';");
        DepartmentLable.setStyle("-fx-font:normal bold 16px 'serif';");

        Scene scene = new Scene(gridPane);

        primaryStage.setTitle("PROJECT-1");
        primaryStage.setScene(scene);
        primaryStage.show();

//TABLE CREATION//////////////////////////////////////////////////////
        TableView table = new TableView();
        gridPane.addColumn(2, table);

//CONNECTION CALLING///////////////////////////////////////////////// 
        DBConnectionC db = new DBConnectionC();

//INSERTING INTO THE TABLE/////////////////////////////////////////// 
        buttonInsert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBConnectionC db = new DBConnectionC();

                Alert A = new Alert(Alert.AlertType.INFORMATION);

                String sql = "Insert into STUDENTS(SID, STUDID, FULLNAME, LASTNAME,SECTION,DEPARTEMENT) Values (?,?,?,?,?,?)";
                String lbl = SIDText.getText();
                String lbl1 = StudIDText.getText();
                String lbl2 = FirstnameText.getText();
                String lbl3 = LastnameText.getText();
                String lbl4 = SectionText.getText();
                String lbl5 = dept.getSelectionModel().getSelectedItem().toString();
                try {
                    con = db.connMethod();
                    try {
                        prs = con.prepareStatement(sql);
                        prs.setString(1, lbl);
                        prs.setString(2, lbl1);
                        prs.setString(3, lbl2);
                        prs.setString(4, lbl3);
                        prs.setString(5, lbl4);
                        prs.setString(6, lbl5);

                        int i = prs.executeUpdate();
                        if (i == 1) {

                            System.out.println("Data Inserted succsecfully");
                        }

                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(ASSIGNMENT1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    A.setContentText("Data Inserted successfuly");
                    A.showAndWait();
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(ASSIGNMENT1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });

//UPDATE THE TABLE//////////////////////////////////////////////////
        buttonUpdate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert A = new Alert(Alert.AlertType.INFORMATION);
                
                DBConnectionC db = new DBConnectionC();
                Connection con = null;
                try {
                    con = db.connMethod();
                    String val = FirstnameText.getText();
                    String val1 = "Aman";
                    String sql = "UPDATE STUDENTS SET FULLNAME='" + val + "' WHERE FULLNAME='" + val1 + "'";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate();

                    A.setContentText("Updated successfuly");
                    A.showAndWait();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

//DISPLAY THE TABLE//////////////////////////////////////////////////
        buttonDisplay.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;

            @Override
            public void handle(ActionEvent event) {

                DBConnectionC obj;
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                    table.setStyle("-fx-background-color:red; -fx-font-color:yellow ");
                    obj = new DBConnectionC();
                    c = obj.connMethod();
                    String SQL = "SELECT * from STUDENTS";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                        table.getColumns().addAll(col);
                        System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }
            }
        });

//SELECT DISTNICT///////////////////////////////////////////////////        
        buttonSelect.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;

            //private TableView tbl;
            @Override
            public void handle(ActionEvent event) {

                DBConnectionC db = new DBConnectionC();
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                    c = db.connMethod();
                    String SQL = "SELECT distinct SECTION from STUDENTS";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        col.setMinWidth(100);
                        table.getColumns().addAll(col);
                        // System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        // System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }

            }
        });

//EXIT FROM THE GUL////////////////////////////////////////////////   
        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
