package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String name, String phoneNumber) {
        if(phoneNumberIsValid(phoneNumber)) {
            phoneBook.computeIfAbsent(name, k -> new ArrayList<String>()).add(formatPhoneNumber(phoneNumber));
        }
    }

    public void addAll(String name, String... phoneNumbers) {
        if (phoneNumbers.length == 0) {
            throw new IllegalArgumentException("Phone number array is not greater than 0");
        }
        int invalidNumbers = 0;
        for (String phoneNumber : phoneNumbers) {
            String existingName = "";
            if(phoneNumberExists(phoneNumber)) {
                for (String nameTemp : phoneBook.keySet()) {
                    for (String phoneNumberTemp : phoneBook.get(nameTemp)) {
                        if (phoneNumber.equals(phoneNumberTemp)) {
                            existingName = nameTemp;
                            break;
                        }
                    }
                }
                System.out.println(phoneNumber + " is already assigned to " + existingName);
                continue;
            }
            if (phoneNumberIsValid(phoneNumber)) {
                phoneBook.computeIfAbsent(name, k -> new ArrayList<String>()).add(formatPhoneNumber(phoneNumber));
            } else {
                invalidNumbers++;
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
        }else {
            System.out.println("Name not found in phonebook");
        }

    }

    public boolean hasEntry(String name) {
        return this.phoneBook.containsKey(name);
    }

    public String getAllContactNames() {
        StringBuilder output = new StringBuilder("PHONEBOOK:\n");
        for (String name : phoneBook.keySet()) {
            output.append(name + " -> Phone numbers: ");
            for(String phoneNumber : phoneBook.get(name)) {
                output.append(phoneNumber + ", ");
            }
            output.append("\n");
        }
        return output.toString().substring(0, output.length() - 3);
    }

    private String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = phoneNumber.replaceAll("[-.\\s]?", "");
        formattedNumber = String.format("(%s) %s-%s",
                formattedNumber.substring(0, 3),
                formattedNumber.substring(3, 6),
                formattedNumber.substring(6, 10)
        );
        return formattedNumber;
    }

    private boolean phoneNumberIsValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String regex1 = "^(\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{4})$";
        String regex2 = "^(\\d{10})$";
        String regex3 = "^(\\d{6}[-.\\s]?\\d{4})$";
        String regex4 = "^(\\d{3}[-.\\s]?\\d{7})$";
        return phoneNumber.matches(regex1)
                || phoneNumber.matches(regex2)
                || phoneNumber.matches(regex3)
                || phoneNumber.matches(regex4);
    }

    private boolean phoneNumberExists(String phoneNumber) {
        return phoneBook.containsValue(phoneNumber);
    }
}
