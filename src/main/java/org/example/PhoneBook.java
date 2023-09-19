package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static java.util.Arrays.asList;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String name, String[] phoneNumbers) {
        if (phoneNumbers.length <= 0) {
            throw new IllegalArgumentException("Phone number array is not greater than 0");
        }
        int invalidNumbers = 0;
        for (String phoneNumber : phoneNumbers) {
            if (phoneNumberIsValid(phoneNumber)) {
                phoneBook.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
            } else {
                invalidNumbers++;
            }

        }
        if (invalidNumbers == phoneNumbers.length) {
            System.out.println("All numbers are invalid");
        }
        if (invalidNumbers > 0) {
            System.out.println("Number of invalid numbers from array: " + invalidNumbers);
        }

    }

    private boolean phoneNumberIsValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String regex1 = "^(\\d{3})[-.\\s]?\\d{4}$";
        String regex2 = "^(\\d{10})$";
        String regex3 = "^(\\d{6}[-.\\s]?\\d{4})$";
        if (phoneNumber.matches(regex1)
        || phoneNumber.matches(regex2)
        || phoneNumber.matches(regex3)) {
            return true;
        }
    return false;
    }
}
