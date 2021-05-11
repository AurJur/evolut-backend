package lt.tyz.evolut.presentation.customer.list;

import lt.tyz.evolut.models.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class GetCustomerListRepresentationOut {

    private final JSONObject data;
    private final JSONArray messages;

    public GetCustomerListRepresentationOut(List<Customer> customerList) {
        JSONArray jsonArray = new JSONArray();
        for (Customer customer : customerList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstName", customer.getFirstName());
            jsonObject.put("lastName", customer.getLastName());
            jsonObject.put("personCode", customer.getPersonCode());
            jsonArray.add(jsonObject);
        }
        JSONObject data = new JSONObject();
        data.put("customers", jsonArray);
        this.data = data;

        JSONArray messages = new JSONArray();
        messages.add("Got user list.");
        this.messages = messages;
    }

    public JSONObject getData() {
        return data;
    }

    public JSONArray getMessages() {
        return messages;
    }
}
