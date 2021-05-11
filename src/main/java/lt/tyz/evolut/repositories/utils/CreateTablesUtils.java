package lt.tyz.evolut.repositories.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateTablesUtils {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String createAccountsTable = "CREATE TABLE IF NOT EXISTS accounts (" +
            "id VARCHAR(10) NOT NULL, " +
            "number VARCHAR(6) NOT NULL, " +
            "crnc VARCHAR(3) NOT NULL, " +
            "balance DECIMAL(13, 2) NOT NULL, " +
            "personCode VARCHAR(5) NOT NULL, " +
            "UNIQUE INDEX id_unique (id ASC) VISIBLE, " +
            "UNIQUE INDEX account_unique (number, crnc) VISIBLE, " +
            "PRIMARY KEY (id));";

    private static final String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
            "personCode VARCHAR(4) NOT NULL, " +
            "name VARCHAR(35) NOT NULL, " +
            "surname VARCHAR(35) NOT NULL, " +
            "UNIQUE INDEX person_unique (name, surname) VISIBLE, " +
            "UNIQUE INDEX personCode_unique (personCode) VISIBLE, " +
            "PRIMARY KEY (personCode));";

    private static final String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions (" +
            "id VARCHAR(10) NOT NULL, " +
            "datetime TIMESTAMP(6) NOT NULL, " +
            "fromAcctId VARCHAR(10) NOT NULL, " +
            "toAcctId VARCHAR(10) NOT NULL, " +
            "amount DECIMAL(13, 2) NOT NULL, " +
            "UNIQUE INDEX id_unique (id ASC) VISIBLE, " +
            "PRIMARY KEY (id));";

    private static final String addForeignKeyAccountsTable = "ALTER TABLE accounts " +
            "ADD FOREIGN KEY (personCode) REFERENCES customers (personCode);";

    private static final String addForeignKeyTransactionsTable = "ALTER TABLE transactions " +
            "ADD FOREIGN KEY (fromAcctId) REFERENCES accounts (id), " +
            "ADD FOREIGN KEY (toAcctId) REFERENCES accounts (id);";

    public void createTables() {
        jdbcTemplate.update(createAccountsTable);
        jdbcTemplate.update(createCustomersTable);
        jdbcTemplate.update(createTransactionsTable);
        jdbcTemplate.update(addForeignKeyAccountsTable);
        jdbcTemplate.update(addForeignKeyTransactionsTable);
        System.out.println("Tables created or existed before.");
    }
}
