package org.example;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
class Sport implements Serializable {
    private String name;
    private List<Sportsman> sportsmen;

    @Override
    public String toString() {
        return "Category: " + name;
    }

    public void printAll() {
        System.out.println(this);
        this.getSportsmen().stream().forEach(System.out::println);
    }
}
