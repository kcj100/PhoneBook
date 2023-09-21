package org.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String name, String phoneNumber) {
        if (phoneNumberIsValid(phoneNumber)) {
            phoneBook.computeIfAbsent(name, k -> new ArrayList<String>()).add(formatPhoneNumber(phoneNumber));
        } else {
            System.out.println("Cannot add " + phoneNumber + " to " + name + ". Phone number " +
                    "is invalid.");
        }
    }

    public void addAll(String name, String... phoneNumbers) {
        if (phoneNumbers.length == 0) {
            throw new IllegalArgumentException("Phone number array is not greater than 0");
        }
        int invalidNumbers = 0;
        for (String phoneNumber : phoneNumbers) {
            if (!phoneNumberIsValid(phoneNumber)) {
                System.out.println("Invalid phone number: " + phoneNumber);
                invalidNumbers++;
                continue;
            }
            String existingName = "";
            if (phoneNumberExists(phoneNumber)) {
                for (String nameTemp : phoneBook.keySet()) {
                    for (String phoneNumberTemp : phoneBook.get(nameTemp)) {
                        if (formatPhoneNumber(phoneNumber).equals(phoneNumberTemp)) {
                            existingName = nameTemp;
                            System.out.println(formatPhoneNumber(phoneNumber) + " is already assigned to " + existingName);
                            break;
                        }
                    }
                }
            }
            if (phoneNumberIsValid(phoneNumber) && !phoneNumberExists(phoneNumber)) {
                phoneBook.computeIfAbsent(name, k -> new ArrayList<String>()).add(formatPhoneNumber(phoneNumber));
            }

        }
        if (invalidNumbers == phoneNumbers.length) {
            throw new IllegalArgumentException("All phone numbers passed to addAll are invalid");
        }
        if (invalidNumbers > 0) {
            System.out.println("Number of invalid phone numbers from array: " + invalidNumbers);
        }

    }

    public void remove(String name) {
        if (hasEntry(name)) {
            this.phoneBook.remove(name);
        } else {
            System.out.println(name + " not found in phonebook");
        }

    }

    public boolean hasEntry(String name) {
        return this.phoneBook.containsKey(name);
    }

    public List<String> lookup(String name) {
        if (hasEntry(name)) {
            return phoneBook.get(name);
        }
        throw new IllegalArgumentException(name + " is not registered in the phonebook.");
    }

    public String reverseLookup(String phoneNumber) {
        String formattedPhoneNumber = formatPhoneNumber(phoneNumber);
        if (!phoneNumberIsValid(phoneNumber)) {
            throw new IllegalArgumentException("Phone number passed to reverse lookup was not valid.");
        }
        for (String name : phoneBook.keySet()) {
            ArrayList<String> phoneNumbers = phoneBook.get(name);
            if (phoneNumbers.contains(formattedPhoneNumber)) {
                return name;
            }
        }
        throw new IllegalArgumentException("Phone number passed to reverse lookup does not exist");
    }

    public String getAllContactNames() {
        if (phoneBook.isEmpty()) {
            throw new IllegalArgumentException("Cannot get all contact names, phonebook is empty.");
        }
        StringBuilder output = new StringBuilder("PHONEBOOK:\n");
        for (String name : phoneBook.keySet()) {
            output.append(name + " -> Phone numbers: ");
            for (String phoneNumber : phoneBook.get(name)) {
                output.append(phoneNumber + ", ");
            }
            output = new StringBuilder(output.substring(0, output.length() - 2));
            output.append("\n");
        }
        return output.toString();
    }

    // format phone number to (xxx) xxx-xxxx
    private String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = phoneNumber.replaceAll("[-.\\s]?", "");
        formattedNumber = String.format("(%s) %s-%s",
                formattedNumber.substring(0, 3),
                formattedNumber.substring(3, 6),
                formattedNumber.substring(6, 10)
        );
        return formattedNumber;
    }

    // valid phone number formats
    private boolean phoneNumberIsValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String regex1 = "^(\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{4})$";
        String regex2 = "^(\\d{10})$";
        String regex3 = "^(\\d{6}[-.\\s]?\\d{4})$";
        String regex4 = "^(\\d{3}[-.\\s]?\\d{7})$";
        String regex5 = "\\(\\d{3}\\)[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
        return phoneNumber.matches(regex1)
                || phoneNumber.matches(regex2)
                || phoneNumber.matches(regex3)
                || phoneNumber.matches(regex4)
                || phoneNumber.matches(regex5);
    }

    private boolean phoneNumberExists(String phoneNumber) {
        for (ArrayList<String> phoneNumbers : phoneBook.values()) {
            if (phoneNumbers.contains(formatPhoneNumber(phoneNumber))) {
                return true;
            }
        }
        ;
        return false;
    }
}
