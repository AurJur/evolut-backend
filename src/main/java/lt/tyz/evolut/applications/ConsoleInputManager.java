package lt.tyz.evolut.applications;

import lt.tyz.evolut.models.Account;
import lt.tyz.evolut.models.Customer;
import lt.tyz.evolut.models.Transaction;
import lt.tyz.evolut.repositories.AccountRepository;
import lt.tyz.evolut.repositories.CustomerRepository;
import lt.tyz.evolut.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleInputManager {

    private Scanner scanner;
    private boolean runApp = true;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ConsoleInputManager() {
        this.scanner = new Scanner(System.in);
    }

    public void flow() throws InterruptedException, SQLException, IOException {
        while (runApp) {
            System.out.println("1. Add customer\n" +
                    "2. Add account to customer.\n" +
                    "3. Delete account.\n" +
                    "4. Make transaction.\n" +
                    "5. Finish.\n" +
                    "Your choice:");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    offerToAddCustomer();
                    break;
                case "2":
                    offerToAddAccount();
                    break;
                case "3":
                    offerToDeleteAccount();
                    break;
                case "4":
                    offerToMakeTransaction();
                    break;
                case "5":
                    System.out.println("Bye");
                    runApp = false;
                    scanner.close();
                    break;
                default: {
                    System.out.println("Your input was somewhat nonsensical...");
                    Thread.sleep(1000);
                }
            }
        }
        scanner.close();
    }

    private void offerToAddCustomer() {
        System.out.println("Enter person code (4 symbols):");
        String personCode = scanner.nextLine();
        System.out.println("Enter person name:");
        String name = scanner.nextLine();
        System.out.println("Enter person surname:");
        String surname = scanner.nextLine();
        customerRepository.add(new Customer(name, surname, personCode));
    }

    private void offerToAddAccount() throws InterruptedException, SQLException, IOException {
        System.out.println("Do you know person code you want to add account to? y/n:");
        switch (scanner.nextLine()) {
            case "n":
                for (Customer customer : customerRepository.getAll())
                    System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getPersonCode());
            case "y":
                getDetailsAndAddAccount();
                break;
            default:
                System.out.println("Whaaat?....");
                Thread.sleep(1000);
        }
    }

    private void offerToDeleteAccount() throws InterruptedException, SQLException {
        System.out.println("Do you know account number you want to delete? y/n:");
        switch (scanner.nextLine()) {
            case "n":
                getListOfAccountsAndPrintThemWithCustomer();
            case "y":
                getDetailsAndDeleteAccount();
                break;
            default:
                System.out.println("Whaaat?....");
                Thread.sleep(1000);
        }
    }

    private void offerToMakeTransaction() throws InterruptedException, SQLException {
        System.out.println("Do you know the fromAccount and toAccount numbers? y/n:");
        switch (scanner.nextLine()) {
            case "n":
                getListOfAccountsAndPrintThemWithCustomer();
            case "y":
                getDetailsAndMakeTransaction();
                break;
            default:
                System.out.println("Whaaat?....");
                Thread.sleep(1000);
        }
    }

    private void getDetailsAndDeleteAccount() {
        System.out.println("Enter account number you want to delete:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter currency of the account:");
        String crnc = scanner.nextLine();
        Account accountForDelete = accountRepository.getByNumAndCrnc(accountNumber, crnc);
        accountRepository.deleteById(accountForDelete.getId());
    }

    private void getDetailsAndAddAccount() {
        System.out.println("Enter person code you want to add acount to:");
        String personCode = scanner.nextLine();
        System.out.println("Enter currency of the account:");
        String crnc = scanner.nextLine();
        System.out.println("Enter balance of the account:");
        double balance = Double.parseDouble(scanner.nextLine());
        Customer customerModel = customerRepository.getByPersonCode(personCode);
        Account account = new Account(crnc, balance, customerModel.getPersonCode());
        accountRepository.add(account);
    }

    private void getDetailsAndMakeTransaction() {
        System.out.println("Enter fromAccount number:");
        String fromAccountNum = scanner.nextLine();
        System.out.println("Enter toAccount number:");
        String toAccountNum = scanner.nextLine();
        System.out.println("Enter amount:");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter currency:");
        String currency = scanner.nextLine();
        Account fromAccount = accountRepository.getByNumAndCrnc(fromAccountNum, currency);
        Account toAccount = accountRepository.getByNumAndCrnc(toAccountNum, currency);
        Transaction transaction = new Transaction(fromAccount.getId(), toAccount.getId(), amount);
        transactionRepository.make(transaction);
    }

    private void getListOfAccountsAndPrintThemWithCustomer() {
        List<Account> accounts = accountRepository.getAll();
        List<Customer> customers = customerRepository.getAll();
        for (Account account : accounts) {
            String personCode = account.getPersonCode();
            for (Customer customer : customers) {
                if (customer.getPersonCode().equals(personCode)) {
                    System.out.println(account.getNumber() + " " + account.getCurrency() + " " + account.getBalance() + " " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getPersonCode());
                }
            }
        }
    }
}
