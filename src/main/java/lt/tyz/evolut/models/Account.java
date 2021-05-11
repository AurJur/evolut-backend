package lt.tyz.evolut.models;

import java.util.Random;

public class Account {

    private String id;
    private final String number;
    private final String currency;
    private double balance;
    private final String personCode;

    // this is for accounts retrieved from DB.
    public Account(String id, String number, String currency, double balance, String personCode) {
        this.id = id;
        this.number = number;
        this.currency = currency;
        this.balance = balance;
        this.personCode = personCode;
    }

    // this is for new accounts added via frontend
    public Account(String currency, double balance, String personCode) {
        this.number = generateAccountNumber();
        this.currency = currency;
        this.balance = balance;
        this.personCode = personCode;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPersonCode() {
        return personCode;
    }

    private String generateAccountNumber() {
        Random random = new Random();
//        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
//        String char01 = String.valueOf(letters.charAt(random.nextInt(letters.length())));
//        String char02 = String.valueOf(letters.charAt(random.nextInt(letters.length())));
        String char01 = "L";
        String char02 = "T";
        String char03 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char04 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char05 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char06 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        return (char01 + char02 + char03 + char04 + char05 + char06).toUpperCase();
    }
}
