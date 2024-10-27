package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void parseLines(Secretariat secretariat, String folder, Scanner reader, FileWriter writer) throws IOException{
        while(reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] split_data = data.split(" - ");

            if (split_data[0].equals("adauga_student")) {
                try {
                    secretariat.addStudent(split_data[1], split_data[2]);
                } catch (ExceptionDuplicateStudent | ExceptionInvalidDegree e) {
                    writer.write("***\n");
                    writer.write(e.getMessage());
                }
            }
            if (split_data[0].equals("adauga_curs")) {
                secretariat.addCourse(split_data[1], split_data[2], Integer.parseInt(split_data[3]));
            }
            if (split_data[0].equals("citeste_mediile")) {
                secretariat.readAverages(folder);
            }
            if (split_data[0].equals("posteaza_mediile")) {
                writer.write("***\n");
                writer.write(secretariat.getAverages());
            }
            if (split_data[0].equals("contestatie")) {
                secretariat.updateAverage(split_data[1], Float.parseFloat(split_data[2]));
            }
            if (split_data[0].equals("adauga_preferinte")) {
                secretariat.addPreferences(split_data);
            }
            if (split_data[0].equals("repartizeaza")) {
                secretariat.allocate();
            }
            if (split_data[0].equals("posteaza_curs")) {
                writer.write("***\n");
                writer.write(secretariat.getCourse(split_data[1]));
            }
            if (split_data[0].equals("posteaza_student")) {
                writer.write("***\n");
                writer.write(secretariat.getStudent(split_data[1]));
            }
        }
    }



    public static void main(String[] args) {
	    String path = "src/main/resources/";
        if (args.length == 0) {
            throw new ArithmeticException("0 must be 1");
        }
        String folder = path + args[0] + "/";
        try {
            File fisierIn = new File(folder + args[0] + ".in");
            File fisierOut = new File(folder + args[0] + ".out");

            Scanner reader = new Scanner(fisierIn);
            FileWriter writer = new FileWriter(fisierOut);
            Secretariat secretariat = new Secretariat();

            parseLines(secretariat, folder, reader, writer);

            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
