package br.com.renan.project.domain.query.impl;

import br.com.renan.project.domain.entity.Shoe;
import br.com.renan.project.domain.query.Filter;

import java.time.LocalDate;

/**
 * Created by Renan.
 */
public class ShoeFilter implements Filter<Shoe> {

    private int size;
    private String category;
    private String color;
    private Double price;
    private String bride;
    private String description;
    private LocalDate dateRegister;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBride() {
        return bride;
    }

    public void setBride(String bride) {
        this.bride = bride;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }
}
