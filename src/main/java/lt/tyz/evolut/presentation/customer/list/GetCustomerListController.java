package lt.tyz.evolut.presentation.customer.list;

import lt.tyz.evolut.business.CustomerService;
import lt.tyz.evolut.models.Customer;
import lt.tyz.evolut.presentation.RepresentationOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "${controllers.CrossOrigin}")
public class GetCustomerListController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/list")
    public RepresentationOut getCustomerList() {
        List<Customer> customerList = customerService.getAll();
        GetCustomerListRepresentationOut out = new GetCustomerListRepresentationOut(customerList);
        return new RepresentationOut(out.getData(), true, HttpStatus.OK, out.getMessages());
    }
}
