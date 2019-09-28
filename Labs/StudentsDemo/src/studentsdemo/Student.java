/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsdemo;

/**
 *
 * @author Airi
 */
public class Student {

    private long id;
    private String firstName;
    private String lastName;

    public Student(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String displayStudent() {
        return "ID:" + this.getId() + " " + "First Name:" + this.getFirstName()
                + " " + "Last Name:" + this.getLastName();
    }

}
