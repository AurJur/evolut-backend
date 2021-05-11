package lt.tyz.evolut.business;

import lt.tyz.evolut.models.Transaction;
import lt.tyz.evolut.presentation.transaction.make.PostTransactionMakeRepresentationIn;
import lt.tyz.evolut.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction make(PostTransactionMakeRepresentationIn input) {

        String fromAccountId = input.getFrom();
        String toAccountId = input.getTo();
        double amount = input.getAmount();
//        String currency = crncExtractor(input.getFrom());
        String currency = "EUR";

//        String fromAccountId = accountService.getByNumAndCrnc(fromAccountNum, currency).getId();
//        String toAccountId = accountService.getByNumAndCrnc(toAccountNum, currency).getId();

        if (fromAccountId.equals(toAccountId)) {
            System.out.println("Fail");
            return new Transaction("", "", "", "", 0,false, "FROM account and TO account can not be the same.");
        }
        Transaction transaction = new Transaction(fromAccountId, toAccountId, amount);
        Transaction completedTransaction = transactionRepository.make(transaction);
        completedTransaction.setCompleted (true);
        completedTransaction.setMessage(String.format("Transaction completed on %s.", completedTransaction.getDateTime()));
        return completedTransaction;
    }

    private String acctNumExtractor(String str) {
        String[] strs = str.split(" ");
        int wordCount = strs.length;
        return strs[wordCount - 1];
    }

/*    private String crncExtractor(String str) {
        String[] strs = str.split(" ");
        int wordCount = strs.length;
        String balanceCurrency = strs[wordCount - 2];
        return balanceCurrency.substring(balanceCurrency.length() - 3, balanceCurrency.length());
    }*/
}
