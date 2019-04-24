package br.com.devdojo.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Student extends AbstractEntity {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
