package lt.tyz.evolut.presentation.account.list;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class GetAccountListRepresentationOut {

//    private final long id;
//    private final JSONArray content;

//    public GetAccountListRepresentationOut(long id, JSONArray content) {
//        this.id = id;
//        this.content = content;
//    }

    private final JSONObject data;
    private final JSONArray messages;

    public GetAccountListRepresentationOut(JSONArray allIncludingCustomerData) {

        JSONObject data = new JSONObject();
        data.put("accounts", allIncludingCustomerData);
        this.data = data;

        JSONArray messages = new JSONArray();
        messages.add("Got account list with customer data.");
        this.messages = messages;
    }

//    public long getId() {
//        return id;
//    }

//    public JSONArray getContent() {
//        return content;
//    }


    public JSONObject getData() {
        return data;
    }

    public JSONArray getMessages() {
        return messages;
    }
}
