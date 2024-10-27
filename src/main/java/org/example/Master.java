package org.example;

public class Master extends Student{
    Master(String name, float average) {
        super(name, average);
    }

    public String toString() {
        return "Student " + "Master: " +
                this.getName() +
                " - " +
                this.getAverage() +
                " - " +
                this.getAssigned().getName() +
                "\n";
    }
}
