package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Secretariat {
    private final Hashtable<String, Student> students = new Hashtable<>();
    private final Hashtable<String, Course<?>> courses = new Hashtable<>();

    public void addStudent(String type, String name) throws ExceptionDuplicateStudent, ExceptionInvalidDegree {
        if (students.get(name) != null)
            throw new ExceptionDuplicateStudent("Student duplicat: " + name + "\n");

        Student stud = null;
        if (type.equals("licenta")) {
            stud = new Bachelor(name, -1);
        }
        if (type.equals("master")) {
            stud = new Master(name, -1);
        }
        if (stud == null) {
            throw new ExceptionInvalidDegree("Tip de studii invalid: " + "\n");
        }
        students.put(name, stud);
    }
    public void addCourse(String type, String name, int max_size){
        Course<?> curs = null;
        if (type.equals("master"))
            curs = new Course<>(name, max_size, Master.class);
        if (type.equals("licenta"))
            curs = new Course<>(name, max_size, Bachelor.class);
        courses.put(name, curs);
    }
    public void readAverages(String folder) {
        String file_prefix = folder + "note_";
        int file_count = 1;
        File file;
        while (true) {
            try {
                file = new File(file_prefix + file_count + ".txt");
                Scanner reader = new Scanner(file);

                while(reader.hasNextLine()) {
                    String data = reader.nextLine();
                    String[] out = data.split(" -");

                    Student stud = students.get(out[0]);
                    if (stud != null) {
                        stud.setAverage(Float.parseFloat(out[1]));
                    }
                }

                reader.close();
            } catch (FileNotFoundException e) {
                break;
            }
            file_count++;
        }
    }
    public String getAverages() {
        StringBuilder str = new StringBuilder();
        ArrayList<Student> students_copy = new ArrayList<>(students.values());
        Collections.sort(students_copy);
        for (Student stud : students_copy) {
            str.append(stud.toStringAverage());
        }
        return str.toString();
    }
    public void updateAverage(String name, float average) {
        Student stud = students.get(name);
        if (stud == null)
            return;
        stud.setAverage(average);
    }
    public void addPreferences(String[] args) {
        // args[0] - adauga_preferinte
        // args[1] - nume
        // args[2..n] - preferinte

        Student stud = students.get(args[1]);
        if (stud == null)
            return; // Should throw probably

        for (int i = 2; i < args.length; i++) {
            Course<?> curs = courses.get(args[i]);

            if (curs == null)
                continue;
            stud.addPreference(curs);
        }
    }
    public void allocate() {
        ArrayList<Student> students_copy = new ArrayList<>(students.values());
        ArrayList<Course<?>> courses_copy = new ArrayList<>(courses.values());
        Collections.sort(students_copy);
        Collections.sort(courses_copy);
        for (Student stud : students_copy) {
            boolean found = false;
            for (Course<?> curs : stud.getPreferences()) {
                if (curs.hasSlots() && curs.isStudentAssignable(stud)) {
                    curs.addStudent(stud);
                    stud.setAssigned(curs);
                    found = true;
                    break;
                }
                if (curs.getLastAverage() == stud.getAverage() && curs.isStudentAssignable(stud)) {
                    curs.addStudent(stud);
                    stud.setAssigned(curs);
                    found = true;
                    break;
                }
            }
            if (found)
                continue;
            // Find a course
            for (Course<?> curs : courses_copy) {
                if (curs.hasSlots() && curs.isStudentAssignable(stud)) {
                    curs.addStudent(stud);
                    stud.setAssigned(curs);
                    break;
                }
            }
        }
    }
    public String getCourse(String name) {
        Course<?> curs = courses.get(name);
        return curs.toString();
    }
    public String getStudent(String name) {
        Student student = students.get(name);
        return student.toString();
    }
}
