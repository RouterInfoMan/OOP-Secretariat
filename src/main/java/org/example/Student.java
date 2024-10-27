package org.example;

import java.util.ArrayList;

public abstract class Student implements Comparable<Student>{
    private String name;
    private float average;
    private final ArrayList<Course<?>> preferences = new ArrayList<>();
    private Course<?> assigned;

    Student(String name, float average) {
        this.name = name;
        this.average = average;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public void addPreference(Course<?> course) {
        preferences.add(course);
    }
    public void setAssigned(Course<?> course) {
        this.assigned = course;
    }
    public Course<?> getAssigned() {
        return this.assigned;
    }

    public ArrayList<Course<?>> getPreferences() {
        return new ArrayList<>(preferences);
    }

    public String toStringAverage() {
        return getName() + " - " + getAverage() + "\n";
    }
    public int compareTo(Student stud) {
        if (this.getAverage() < stud.getAverage())
            return 1;
        if (this.getAverage() > stud.getAverage())
            return -1;
        return this.getName().compareTo(stud.getName());
    }

}
