package org.example;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Builder
@Data
@EqualsAndHashCode
class Element implements Serializable {
    private String name;
    private int value;
    private Category category;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Element)) return false;
        Element other = (Element) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Element: " + name + ", Value: " + value;
    }
}
