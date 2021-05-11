package lt.tyz.evolut.repositories;

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
public class TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Transaction make(Transaction transaction) {

        // preparation (generating new Id)
        String generatedId;
        do {
            generatedId = generateTransactionId();
        } while (getById(generatedId) != null);

//        Transaction completedTransaction = doTransaction(transaction, generatedId);
        /////////
        // subtract amount from fromAccount
        final String subtractAmountFromAccount = "UPDATE accounts SET balance = balance - ? WHERE id = ?;";
        jdbcTemplate.update(subtractAmountFromAccount,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setDouble(1, transaction.getAmount());
                        preparedStatement.setString(2, transaction.getFromAccountId());
                    }
                });

        // add amount to toAccount
        final String addAmountToAccount = "UPDATE accounts SET balance = balance + ? WHERE id = ?;";
        jdbcTemplate.update(addAmountToAccount,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setDouble(1, transaction.getAmount());
                        preparedStatement.setString(2, transaction.getToAccountId());
                    }
                });

        // add transaction to table
        final String addTransactionToTable = "INSERT INTO transactions (id, datetime, fromAcctId, toAcctId, amount) VALUES (?, NOW(), ?, ?, ?);";
        String finalGeneratedId = generatedId;
        jdbcTemplate.update(addTransactionToTable,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, finalGeneratedId);
                        preparedStatement.setString(2, transaction.getFromAccountId());
                        preparedStatement.setString(3, transaction.getToAccountId());
                        preparedStatement.setDouble(4, transaction.getAmount());
                    }
                });

        System.out.println("Great, transaction was made! /NEW/");

        // get the completed transaction
        Transaction completedTransaction = getById(generatedId);




        /////////
        return completedTransaction;
    }

    public Transaction getById(String id) {
        final String getById = "SELECT * FROM transactions WHERE id = ?;";
        List<Transaction> transactions = jdbcTemplate.query(getById,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, id);
                    }
                },
                (resultSet, rowNum) -> parseRow(resultSet));
        System.out.println("Transaction retrieved /getById(String id)/");
        if (transactions.size() == 0) {
            return null;
        }
        return transactions.get(0);
    }


/*    @Transactional
    public Transaction doTransaction(Transaction transaction, String id) {

        // subtract amount from fromAccount
        final String subtractAmountFromAccount = "UPDATE accounts SET balance = balance - ? WHERE id = ?;";
        jdbcTemplate.update(subtractAmountFromAccount,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setDouble(1, transaction.getAmount());
                        preparedStatement.setString(2, transaction.getFromAccountId());
                    }
                });

        // add amount to toAccount
        final String addAmountToAccount = "UPDATE accounts SET balance = balance + ? WHERE id = ?;";
        jdbcTemplate.update(addAmountToAccount,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setDouble(1, transaction.getAmount());
                        preparedStatement.setString(2, transaction.getToAccountId());
                    }
                });

        // add transaction to table
        final String addTransactionToTable = "INSERT INTO transactions (id, datetime, fromAcctId, toAcctId, amount) VALUES (?, NOW(), ?, ?, ?);";
        jdbcTemplate.update(addTransactionToTable,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, id);
                        preparedStatement.setString(2, transaction.getFromAccountId());
                        preparedStatement.setString(3, transaction.getToAccountId());
                        preparedStatement.setDouble(4, transaction.getAmount());
                    }
                });

        System.out.println("Great, transaction was made! /NEW/");

        // get the completed transaction
        Transaction completedTransaction = getById(id);
        return completedTransaction;
    }*/

    private String generateTransactionId() {
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

    private Transaction parseRow(ResultSet transactionsResultSetRow) throws SQLException {
        String id = transactionsResultSetRow.getString(1);
        String dateTime = transactionsResultSetRow.getString(2);
        String fromAcctId = transactionsResultSetRow.getString(3);
        String toAcctId = transactionsResultSetRow.getString(4);
        double amount = transactionsResultSetRow.getDouble(5);
        return new Transaction(id, dateTime, fromAcctId, toAcctId, amount);
    }
}
