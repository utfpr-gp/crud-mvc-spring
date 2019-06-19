package br.edu.utfpr.crudmvcspring.model.entity;

public enum DayOfWeekEnum {
    SEGUNDA("Segunda-feira"), TERCA("Terça-feira"),
    QUARTA("Quarta-feira"), QUINTA("Quinta-feira"),
    SEXTA("Sexta-feira");

    private String dayOfWeek;
    DayOfWeekEnum(String day){
      this.dayOfWeek = day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}
