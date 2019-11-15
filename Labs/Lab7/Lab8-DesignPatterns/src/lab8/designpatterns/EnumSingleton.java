/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8.designpatterns;

import java.util.Arrays;

/**
 *
 * @author Airi
 */
//
enum EnumSingleton {
    WEEKDAY("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
    WEEKEND("Saturday", "Sunday");

    private String[] days;

    EnumSingleton(String... days) {
        System.out.println("Initializing enum with " + Arrays.toString(days));
        this.days = days;
    }

    public String[] getDays() {
        return this.days;
    }

    public String toString() {
        return "EnumSingleton{"
                + "days=" + Arrays.toString(days) + '}';
    }
}
