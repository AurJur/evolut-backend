package lt.tyz.evolut.presentation.account.add;

import lt.tyz.evolut.business.AccountService;
import lt.tyz.evolut.models.Account;
import lt.tyz.evolut.presentation.RepresentationOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${controllers.CrossOrigin}")
public class PostAccountAddController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public RepresentationOut postAccountAdd(@RequestBody PostAccountAddRepresentationIn input) {
        Account addedAccount = accountService.addAccount(input);
        PostAccountAddRepresentationOut out = new PostAccountAddRepresentationOut(addedAccount);
        return new RepresentationOut(out.getData(), true, HttpStatus.OK, out.getMessages());
    }
}
