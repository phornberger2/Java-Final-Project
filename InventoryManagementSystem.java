package com.example.final_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InventoryManagementSystem extends Application {
    private TableView<Product> productTable;
    private ObservableList<Product> productList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 800, 600);

        initializeProductTable();

        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");
        Button insertButton = new Button("Insert");

        deleteButton.setOnAction(event -> deleteProduct());
        updateButton.setOnAction(event -> updateProduct());
        insertButton.setOnAction(event -> insertProduct());

        GridPane bottomButtonsPane = new GridPane();
        bottomButtonsPane.setHgap(10);
        bottomButtonsPane.setVgap(10);
        bottomButtonsPane.setPadding(new Insets(10));
        bottomButtonsPane.setAlignment(Pos.CENTER);
        bottomButtonsPane.add(deleteButton, 0, 0);
        bottomButtonsPane.add(updateButton, 1, 0);
        bottomButtonsPane.add(insertButton, 2, 0);

        borderPane.setCenter(productTable);
        borderPane.setBottom(bottomButtonsPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.show();
    }

    private void initializeProductTable() {
        productTable = new TableView<>();
        productList = FXCollections.observableArrayList();

        TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<Product, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        productTable.getColumns().addAll(nameColumn, priceColumn, descriptionColumn);
        productTable.setItems(productList);
    }

    private void deleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            Product.deleteProduct(productList, selectedProduct);
        } else {
            showAlert("No Product Selected", "Please select a product to delete.");
        }
    }

    private void updateProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            showUpdateWindow(selectedProduct);
        } else {
            showAlert("No Product Selected", "Please select a product to update.");
        }
    }

    private void insertProduct() {
        showInsertWindow();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showUpdateWindow(Product product) {
        Stage updateStage = new Stage();
        updateStage.setTitle("Update Product");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField(product.getName());
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField(Double.toString(product.getPrice()));
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField(product.getDescription());

        Button updateButton = new Button("Update");
        Button cancelButton = new Button("Cancel");

        updateButton.setOnAction(event -> {
            Product.updateProduct(product, nameField.getText(), Double.parseDouble(priceField.getText()), descriptionField.getText());
            productTable.refresh();
            updateStage.close();
        });

        cancelButton.setOnAction(event -> updateStage.close());

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(priceLabel, 0, 1);
        gridPane.add(priceField, 1, 1);
        gridPane.add(descriptionLabel, 0, 2);
        gridPane.add(descriptionField, 1, 2);
        gridPane.add(updateButton, 0, 3);
        gridPane.add(cancelButton, 1, 3);

        Scene scene = new Scene(gridPane, 400, 200);
        updateStage.setScene(scene);
        updateStage.show();
    }

    private void showInsertWindow() {
        Stage insertStage = new Stage();
        insertStage.setTitle("Insert Product");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();

        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");

        addButton.setOnAction(event -> {
            Product.insertProduct(productList, nameField.getText(), Double.parseDouble(priceField.getText()), descriptionField.getText());
            insertStage.close();
        });

        cancelButton.setOnAction(event -> insertStage.close());

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(priceLabel, 0, 1);
        gridPane.add(priceField, 1, 1);
        gridPane.add(descriptionLabel, 0, 2);
        gridPane.add(descriptionField, 1, 2);
        gridPane.add(addButton, 0, 3);
        gridPane.add(cancelButton, 1, 3);

        Scene scene = new Scene(gridPane, 400, 200);
        insertStage.setScene(scene);
        insertStage.show();
    }
}
