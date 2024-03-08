package com.example.final_project;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Product {
    private final StringProperty name;
    private final DoubleProperty price;
    private final StringProperty description;

    public Product(String name, double price, String description) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.description = new SimpleStringProperty(description);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public String toString() {
        return name.get();
    }

    // Method to delete a product
    public static void deleteProduct(ObservableList<Product> products, Product product) {
        products.remove(product);
    }

    // Method to update a product
    public static void updateProduct(Product product, String name, double price, String description) {
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
    }

    // Method to insert a new product
    public static void insertProduct(ObservableList<Product> products, String name, double price, String description) {
        Product product = new Product(name, price, description);
        products.add(product);
    }
}
