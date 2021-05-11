package lt.tyz.evolut.presentation.account.list;

import lt.tyz.evolut.business.AccountService;
import lt.tyz.evolut.presentation.RepresentationOut;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${controllers.CrossOrigin}")
public class GetAccountListController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/list")
    public RepresentationOut getAccountList() {
        JSONArray allIncludingCustomerData = accountService.getAllIncludingCustomerData();
        GetAccountListRepresentationOut out = new GetAccountListRepresentationOut(allIncludingCustomerData);
//        return new GetAccountListRepresentationOut(counter.incrementAndGet(), jsonAllAccounts);
        return new RepresentationOut(out.getData(), true, HttpStatus.OK, out.getMessages());
    }
}
