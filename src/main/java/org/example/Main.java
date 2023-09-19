package org.example;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addAll("John", new String[]{"3024456594", "301434 5695", "945 6879604, 945 6879604"});
        System.out.println(phoneBook.getAllContactNames());
    }
}