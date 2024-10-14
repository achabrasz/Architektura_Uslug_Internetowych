package org.example;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Builder
@Getter
class ElementDto implements Serializable {
    private String name;
    private int value;
    private String categoryName;

    @Override
    public String toString() {
        return "DTO Element: " + name + ", Value: " + value + ", Category: " + categoryName;
    }
}
