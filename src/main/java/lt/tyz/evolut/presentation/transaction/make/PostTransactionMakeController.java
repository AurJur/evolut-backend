package lt.tyz.evolut.presentation.transaction.make;

import lt.tyz.evolut.business.TransactionService;
import lt.tyz.evolut.models.Transaction;
import lt.tyz.evolut.presentation.RepresentationOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${controllers.CrossOrigin}")
public class PostTransactionMakeController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/transaction/make", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public RepresentationOut postTransactionMake(@RequestBody PostTransactionMakeRepresentationIn input) {
        Transaction completedTransaction = transactionService.make(input);
        PostTransactionMakeRepresentationOut out = new PostTransactionMakeRepresentationOut(completedTransaction);
        return new RepresentationOut(out.getData(), completedTransaction.isCompleted(), HttpStatus.OK, out.getMessages());
    }
}
