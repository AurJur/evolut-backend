package lt.tyz.evolut.business;

import lt.tyz.evolut.models.Account;
import lt.tyz.evolut.models.Customer;
import lt.tyz.evolut.presentation.customer.add.PostCustomerAddRepresentationIn;
import lt.tyz.evolut.repositories.CustomerRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;

    public Customer addCustomer(PostCustomerAddRepresentationIn postCustomerAddRepresentationIn) {
        String firstName = postCustomerAddRepresentationIn.getFirstName();
        String lastName = postCustomerAddRepresentationIn.getLastName();
        String personCode = postCustomerAddRepresentationIn.getPersonCode();
        Customer customer = new Customer(firstName, lastName, personCode);
        Customer addedCustomer = customerRepository.add(customer);
        return addedCustomer;
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public JSONArray getAllInJsonArray() {
        List<Customer> customers = customerRepository.getAll();
        JSONArray jsonAllCustomers = new JSONArray();
        for (Customer customer : customers) {
            JSONObject jsonCustomer = new JSONObject();
            jsonCustomer.put("personName", customer.getFirstName());
            jsonCustomer.put("personSurname", customer.getLastName());
            jsonCustomer.put("personCode", customer.getPersonCode());
            jsonAllCustomers.add(jsonCustomer);
        }

        return jsonAllCustomers;
    }

    public List<Account> getAccountsByPersonCode(String personCode) {
        return accountService.getAccountsByPersonCode(personCode);
    }

    public Customer getByPersonCode(String personCode) {
        return customerRepository.getByPersonCode(personCode);
    }
}
