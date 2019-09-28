/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsdemo;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Airi
 */
public class StudentsDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student[] students = new Student[3];
        Student s1 = new Student(1587, "Eli", "Vereti");
        Student s2 = new Student(4532, "Zealia", "Wehst");
        Student s3 = new Student(923, "Xander", "Dianos");

        students[0] = s1;
        students[1] = s2;
        students[2] = s3;

        System.out.println("Pre-sort on ID");

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].displayStudent());
        }

        Arrays.sort(students, (a, b) -> (int) (a.getId() - b.getId()));
        //Arrays.sort(students, (a,b) -> a - b);
        /*
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return (int) (s1.getId() - s2.getId());
            }
        });*/

        System.out.println("");
        System.out.println("Post-sort on ID");
        for (Student temp_students : students) {
            System.out.println(temp_students.displayStudent());
        }

        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return ("" + s1.getFirstName()).compareTo("" + s2.getFirstName());
            }

        });

        System.out.println("");
        System.out.println("Post-sort on First Name");
        for (Student temp_students : students) {
            System.out.println(temp_students.displayStudent());
        }

        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int ok = ("" + s1.getLastName()).compareTo("" + s2.getLastName());
                return ok != 0 ? ok
                        : ("" + s1.getFirstName()).compareTo("" + s2.getFirstName());
            }

        });

        System.out.println("");
        System.out.println("Post-sort on Last Name then on First Name");
        for (Student temp_students : students) {
            System.out.println(temp_students.displayStudent());
        }
    }

}
