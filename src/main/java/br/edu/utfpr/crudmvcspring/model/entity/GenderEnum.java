package br.edu.utfpr.crudmvcspring.model.entity;

public enum GenderEnum {
    MASCULINE("Masculino"), FEMININE("Feminino");

    private String name;
    GenderEnum(String gender){
        this.name = gender;
    }

    public String getName() {
        return name;
    }
}
