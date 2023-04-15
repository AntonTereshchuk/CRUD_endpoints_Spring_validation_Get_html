package com.example.map_elements_reading_on_web_server;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Element {

    private int id;

    @Size(max =  10)
    private String element;

    public int getId() {
        return id;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
