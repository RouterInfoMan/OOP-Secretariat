package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Course<StudentType extends Student> implements Comparable<Course<?>>{
    @Override
    public int compareTo(Course o) {
        return this.name.compareTo(o.getName());
    }

    private static class StudentNameComparator implements Comparator<Student> {

        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private final String name;
    private final int max_size;
    private final ArrayList<StudentType> students = new ArrayList<>();
    private final Class<StudentType> studentTypeClass;

    public Course(String name, int max_size, Class<StudentType> studentTypeClass) {
        this.name = name;
        this.max_size = max_size;
        this.studentTypeClass = studentTypeClass;
    }
    public float getLastAverage() {
        if (students.isEmpty())
            return -1;
        return students.get(students.size() - 1).getAverage();
    }
    public boolean hasSlots() {
        return students.size() < max_size;
    }
    public void addStudent(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Studentul este null!");

        if (studentTypeClass.isInstance(student)) {
            students.add(studentTypeClass.cast(student));
            Collections.sort(students);
        }
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        String header = name + " (" + max_size + ")\n";
        str.append(header);

        ArrayList<Student> copy = new ArrayList<>(students);
        copy.sort(new StudentNameComparator());

        for (Student stud : copy) {
            str.append(stud.toStringAverage());
        }
        return str.toString();
    }

    public String getName() {
        return this.name;
    }

    public boolean isStudentAssignable(Student student) {
        return studentTypeClass.isInstance(student);
    }

}
