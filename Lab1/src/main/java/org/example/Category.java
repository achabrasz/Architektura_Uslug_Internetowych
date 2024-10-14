package org.example;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
class Category implements Serializable {
    private String name;
    private List<Element> elements;

    @Override
    public String toString() {
        return "Category: " + name;
    }

    public void printAll() {
        System.out.println(this);
        this.getElements().stream().forEach(System.out::println);
    }
}
