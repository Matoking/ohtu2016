package ohtu;

import java.util.List;

public class Submission {
    private String student_number;
    private int week;
    private int hours;
    private List<Integer> doneTasks;

    public List<Integer> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(List<Integer> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    
    @Override
    public String toString() {
        return String.format(" viikko %d: tehtyjä tehtäviä yhteensä: %d, aikaa kului %d tuntia, tehdyt tehtävät: %s",
                             this.getWeek(), this.getDoneTasks().size(), this.getHours(), this.getDoneTasks());
    }
    
}