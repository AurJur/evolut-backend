package lt.tyz.evolut.repositories;

import lt.tyz.evolut.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getAll() {
        String getCustomersStatement = "SELECT * FROM customers;";
        List<Customer> customers = jdbcTemplate.query(getCustomersStatement, (resultSet, rowNum) ->
                parseRow(resultSet));
        System.out.println("Customers retrieved /getAll()/");
        return customers;
    }

    public Customer getByPersonCode(String personCode) {
        String getCustomersByPersonCodeTemplate = "SELECT * FROM customers WHERE personCode = ?;";
        List<Customer> customers = jdbcTemplate.query(getCustomersByPersonCodeTemplate,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, personCode);
                    }
                },
                (resultSet, rowNum) -> parseRow(resultSet));
        System.out.println("Customer retrieved /getByPersonCode(String personCode)/");
        return customers.get(0);
    }

    public Customer add(Customer customer) {
        final String addCustomerTemplate = "INSERT INTO customers (personCode, name, surname) VALUES (?, ?, ?);";
        jdbcTemplate.update(addCustomerTemplate,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, customer.getPersonCode());
                        preparedStatement.setString(2, customer.getFirstName());
                        preparedStatement.setString(3, customer.getLastName());
                    }
                }
        );
        System.out.println("Customer probably added successfully /add(Customer customer)/");
        Customer addedCustomer = getByPersonCode(customer.getPersonCode());
        return addedCustomer;
    }

    private Customer parseRow(ResultSet customersResultSetRow) throws SQLException {
        String personCode = customersResultSetRow.getString(1);
        String firstName = customersResultSetRow.getString(2);
        String lastName = customersResultSetRow.getString(3);
        return new Customer(firstName, lastName, personCode);
    }
}