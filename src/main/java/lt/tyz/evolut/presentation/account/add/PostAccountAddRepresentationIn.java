package lt.tyz.evolut.presentation.account.add;

public class PostAccountAddRepresentationIn {

    private final String personCode;
    private final String currency;
    private final double balance;

    PostAccountAddRepresentationIn(String personCode, String currency, double balance) {
        this.personCode = personCode;
        this.currency = currency;
        this.balance = balance;
    }

    public String getPersonCode() {
        return personCode;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }
}
