package br.edu.utfpr.crudmvcspring.model.entity;

public enum DayOfWeek {
    SEGUNDA("Segunda-feira"), TERCA("Terça-feira"),
    QUARTA("Quarta-feira"), QUINTA("Quinta-feira"),
    SEXTA("Sexta-feira");

    private String dayOfWeek;
    DayOfWeek(String day){
      this.dayOfWeek = day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}
