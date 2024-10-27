package org.example;

public class Bachelor extends Student{
    public Bachelor(String name, float average) {
        super(name, average);
    }
    public String toString() {
        return "Student " + "Licenta: " +
                this.getName() +
                " - " +
                this.getAverage() +
                " - " +
                this.getAssigned().getName() +
                "\n";
    }
}
