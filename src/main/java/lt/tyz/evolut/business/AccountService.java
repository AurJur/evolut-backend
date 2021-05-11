package lt.tyz.evolut.business;

import lt.tyz.evolut.models.Account;
import lt.tyz.evolut.models.Customer;
import lt.tyz.evolut.presentation.account.add.PostAccountAddRepresentationIn;
import lt.tyz.evolut.repositories.AccountRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    public Account addAccount(PostAccountAddRepresentationIn postAccountAddRepresentationIn) {
        String currency = postAccountAddRepresentationIn.getCurrency();
        double balance = postAccountAddRepresentationIn.getBalance();
        String personCode = postAccountAddRepresentationIn.getPersonCode();
        Account account = new Account(currency, balance, personCode);
        Account addedAccount = accountRepository.add(account);
        return addedAccount;
    }

    public JSONArray getAllIncludingCustomerData()   {
        List<Account> accounts = accountRepository.getAll();
        JSONArray jsonAllAccounts = new JSONArray();
        int id = 1;
        for (Account account : accounts) {
            String personCode = account.getPersonCode();
            // fixme: dabar gaunasi, kad į db kreipiasi kiekvieną kartą
            // bandyti su lambda
            Customer customer = customerService.getByPersonCode(personCode);
            JSONObject jsonAccount = new JSONObject();
            jsonAccount.put("id", account.getId());
            jsonAccount.put("number", account.getNumber());
            jsonAccount.put("currency", account.getCurrency());
            jsonAccount.put("balance", account.getBalance());
            jsonAccount.put("firstName", customer.getFirstName());
            jsonAccount.put("lastName", customer.getLastName());
            jsonAccount.put("personCode", customer.getPersonCode());
            jsonAllAccounts.add(jsonAccount);
        }
        return jsonAllAccounts;
    }

    public List<Account> getAccountsByPersonCode(String personCode) {
        return accountRepository.getAllByPersonCode(personCode);
    }

    public Account getByNumAndCrnc(String num, String currency) {
        return accountRepository.getByNumAndCrnc(num, currency);
    }
}
