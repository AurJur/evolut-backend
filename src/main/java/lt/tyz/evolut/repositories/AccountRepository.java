package lt.tyz.evolut.repositories;

import lt.tyz.evolut.models.Account;
import lt.tyz.evolut.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String getListOfAccountsStatement = "SELECT * FROM accounts;";
//    private final String getAccountsByIdTemplate = "SELECT * FROM accounts WHERE id = ?;";
    private final String getAccountsByNumAndCrncTemplate = "SELECT * FROM accounts WHERE number = ? AND crnc = ?;";
    private final String deleteTemplate = "DELETE FROM accounts WHERE id = ?;";
    private final String getAccountsByPersonCodeTemplate = "SELECT * FROM accounts WHERE personCode = ?;";

    public List<Account> getAll() {
        List<Account> accounts = jdbcTemplate.query(getListOfAccountsStatement, (resultSet, rowNum) ->
                parseRow(resultSet));
        System.out.println("Accounts retrieved /getAll()/");
        return accounts;
    }

/*    public Account getByIdDb(int idDb) {
        List<Account> accounts = jdbcTemplate.query(getAccountsByIdTemplate,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, idDb);
                    }
                },
                (resultSet, rowNum) -> parseRow(resultSet));
        System.out.println("Account retrieved /getByIdDb(int idDb)/");
        return accounts.get(0);
    }*/

    public Account getByNumAndCrnc(String number, String crnc) {
        List<Account> accounts = jdbcTemplate.query(getAccountsByNumAndCrncTemplate,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, number);
                        preparedStatement.setString(2, crnc);
                    }
                },
                (resultSet, rowNum) -> parseRow(resultSet));
        System.out.println("Account retrieved /getByNumAndCrnc(String number, String crnc)/");
        return accounts.get(0);
    }

    @Transactional
    public Account add(Account account) {

        // preparation (generating new Id)
        String generatedId;
        do{
            generatedId = generateAccountId();
        }while (getById(generatedId) != null);

        // add account to table
        final String addTemplate = "INSERT INTO accounts (id, number, crnc, balance, personCode) VALUES (?, ?, ?, ?, ?);";
        String finalGeneratedId = generatedId;
        jdbcTemplate.update(addTemplate,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, finalGeneratedId);
                        preparedStatement.setString(2, account.getNumber());
                        preparedStatement.setString(3, account.getCurrency());
                        preparedStatement.setDouble(4, account.getBalance());
                        preparedStatement.setString(5, account.getPersonCode());
                    }
                }
        );
        System.out.println("Account probably added successfully /NEW/.");

        // get the completed transaction
        Account addedAccount = getById(generatedId);
        return addedAccount;
    }

    private Account getById(String id) {
        final String getById = "SELECT * FROM accounts WHERE id = ?;";
        List<Account> accounts = jdbcTemplate.query(getById,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, id);
                    }
                },
                (resultSet, rowNum) -> parseRow(resultSet));
        System.out.println("Account retrieved /getById(String id)/");
        if (accounts.size() == 0) {
            return null;
        }
        return accounts.get(0);
    }

    public void deleteById(String id) {
        jdbcTemplate.update(deleteTemplate,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, id);
                    }
                }
        );
        System.out.println("Account probably was deleted successfully /NEW/.");
//        Thread.sleep(1000);
    }

    public List<Account> getAllByPersonCode(String personCode) {
        List<Account> accounts = jdbcTemplate.query(getAccountsByPersonCodeTemplate,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, personCode);
                    }
                },
                (resultSet, rowNum) -> parseRow(resultSet));
        System.out.println("Accounts retrieved /getAllByPersonCode(String personCode)/");
        return accounts;
    }

    private Account parseRow(ResultSet accountsResultSetRow) throws SQLException {
        String id = accountsResultSetRow.getString((1));
        String number = accountsResultSetRow.getString(2);
        String currency = accountsResultSetRow.getString(3);
        double balance = accountsResultSetRow.getDouble(4);
        String personCode = accountsResultSetRow.getString(5);
        return new Account(id, number, currency, balance, personCode);
    }

    private String generateAccountId() {
        Random random = new Random();
        String digits = "0123456789";
        String char01 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char02 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char03 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char04 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char05 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char06 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char07 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char08 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char09 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        String char10 = String.valueOf(digits.charAt(random.nextInt(digits.length())));
        return (char01 + char02 + char03 + char04 + char05 + char06 + char07 + char08 + char09 + char10);
    }
}