package lt.tyz.evolut.presentation.customer.add;

import lt.tyz.evolut.business.CustomerService;
import lt.tyz.evolut.models.Customer;
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
public class PostCustomerAddController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customer/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody //we don't need to annotate the @RestController-annotated controllers with the @ResponseBody annotation since Spring does it by default.
    public RepresentationOut addCustomer(@RequestBody PostCustomerAddRepresentationIn input) {
        Customer addedCustomer = customerService.addCustomer(input);
        PostCustomerAddRepresentationOut out = new PostCustomerAddRepresentationOut(addedCustomer);
        return new RepresentationOut(out.getData(), true, HttpStatus.OK, out.getMessages());
    }

    //    @ExceptionHandler
//    public ResponseEntity<RepresentationOut> handle(Exception e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
//        return new ResponseEntity<>(e.getCause(), HttpStatus.OK);
//        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.OK);
//        JSONArray messages = new JSONArray();
//        messages.add("getCause: " + e.getCause());
//        messages.add("getMessage: " + e.getMessage());
//        messages.add("getLocalized: " + e.getLocalizedMessage());
//        messages.add(e.ge.getStackTrace());
//        messages.add(e.initCause(e.getCause()));
//        messages.add("toString: " + e.toString());
//
//        return new ResponseEntity<>(new RepresentationOut(null, false, null, messages), HttpStatus.CONFLICT);
//        return new ResponseEntity<>(new RepresentationOut(null, true, HttpStatus.OK, messages), HttpStatus.CONFLICT);
//    }
}
