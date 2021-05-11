package lt.tyz.evolut.presentation.customer.add;

public class PostCustomerAddRepresentationIn {

    private final String personCode;
    private final String firstName;
    private final String lastName;

    public PostCustomerAddRepresentationIn(String personCode, String firstName, String lastName) {
        this.personCode = personCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPersonCode() {
        return personCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
