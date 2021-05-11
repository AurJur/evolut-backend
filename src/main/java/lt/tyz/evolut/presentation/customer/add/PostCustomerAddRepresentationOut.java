package lt.tyz.evolut.presentation.customer.add;

import lt.tyz.evolut.models.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PostCustomerAddRepresentationOut {

    private final JSONObject data;
    private final JSONArray messages;

    public PostCustomerAddRepresentationOut(Customer customer) {
        JSONObject customerOutData = new JSONObject();
        customerOutData.put("firstName", customer.getFirstName());
        customerOutData.put("lastName", customer.getLastName());
        customerOutData.put("personCode", customer.getPersonCode());
        this.data = customerOutData;
        JSONArray messages = new JSONArray();
        messages.add("User added.");
        this.messages = messages;
    }

    public JSONObject getData() {
        return data;
    }

    public JSONArray getMessages() {
        return messages;
    }
}
