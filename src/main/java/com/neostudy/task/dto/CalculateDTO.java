package com.neostudy.task.dto;

public class CalculateDTO {

    private int averageSalary = -1, weekends = -1;
    private String[] dates;

    public CalculateDTO() {}

    public CalculateDTO(int averageSalary, int weekends, String[] dates) {
        this.averageSalary = averageSalary;
        this.weekends = weekends;
        this.dates = dates;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        this.averageSalary = averageSalary;
    }

    public int getWeekends() {
        return weekends;
    }

    public void setWeekends(int weekends) {
        this.weekends = weekends;
    }

    public String[] getDates() {
        return dates;
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return String.format("Average salary: %d\nWeekends: %d", averageSalary, weekends);
    }

}
