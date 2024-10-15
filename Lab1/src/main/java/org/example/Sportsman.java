package org.example;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Data
class Sportsman implements Serializable {
    private String name;
    private int rating;
    private Sport sport;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Sportsman)) return false;
        Sportsman other = (Sportsman) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Sportsman: " + name + ", Rating: " + rating;
    }
}
