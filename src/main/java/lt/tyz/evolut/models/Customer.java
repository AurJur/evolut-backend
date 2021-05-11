package lt.tyz.evolut.models;

import lt.tyz.evolut.business.CustomerService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class Customer {

    @Autowired
    private CustomerService customerService;

    private final String firstName;
    private final String lastName;
    private final String personCode;
//    private List<Account> accounts = null;

    public Customer(String firstName, String lastName, String personCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personCode = personCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonCode() {
        return personCode;
    }

/*    public List<Account> getAccounts() throws SQLException {
        if (this.accounts == null) {
            *//**
     *  todo: check what is return if customer has no accounts.
     *  In this case maybe accounts had to be set from null to zero.
     *//*
            System.out.println("OK01 personCode:" + this.personCode);
            List<Account> accountsFromDb = customerService.getAccountsByPersonCode(this.personCode);
            this.accounts = accountsFromDb;
        }
        return this.accounts;
    }*/
}
