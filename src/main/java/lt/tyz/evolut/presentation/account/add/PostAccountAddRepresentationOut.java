package lt.tyz.evolut.presentation.account.add;

import lt.tyz.evolut.models.Account;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PostAccountAddRepresentationOut {

    private final JSONObject data;
    private final JSONArray messages;

    public PostAccountAddRepresentationOut(Account account) {
        JSONObject customerOutData = new JSONObject();
        customerOutData.put("personCode", account.getPersonCode());
        customerOutData.put("balance", account.getBalance());
        customerOutData.put("currency", account.getCurrency());
        customerOutData.put("number", account.getNumber());
        this.data = customerOutData;
        JSONArray messages = new JSONArray();
        messages.add(String.format("Account %s added.", account.getNumber()));
        this.messages = messages;
    }

    public JSONObject getData() {
        return data;
    }

    public JSONArray getMessages() {
        return messages;
    }
}
