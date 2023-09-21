package org.example;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addAll("John", new String[]{"3024456594", "301434 5695", "945 6879604", "945 6879604"});
        System.out.println(phoneBook.getAllContactNames());
        System.out.println("John: " + phoneBook.lookup("John"));
        System.out.println("3024456594 belongs to " + phoneBook.reverseLookup("3024456594"));
        phoneBook.addAll("Tim", new String[]{"5690346321", "8960583845", "4794503859"});
        System.out.println("John: " + phoneBook.lookup("John"));
        System.out.println("8960583845 belongs to " + phoneBook.reverseLookup("8960583845"));
        System.out.println(phoneBook.getAllContactNames());
        phoneBook.remove("John");
        System.out.println(phoneBook.getAllContactNames());
        System.out.println("John has entry in phonebook: " + phoneBook.hasEntry("John"));
        System.out.println("Tim has entry in phonebook: " + phoneBook.hasEntry("Tim"));
        phoneBook.remove("John");
        phoneBook.add("Tom", "68686");
        //incorrect phone numbers test
        //        phoneBook.addAll("John", new String[]{"302445659", "301434 569", "945 687960", "945 687960"});
    }
}