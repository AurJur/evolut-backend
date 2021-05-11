package lt.tyz.evolut.presentation.transaction.make;

import lt.tyz.evolut.models.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PostTransactionMakeRepresentationOut {

    private final JSONObject data;
    private final JSONArray messages;

    public PostTransactionMakeRepresentationOut(Transaction completedTransaction) {

        JSONObject transactionOutData = new JSONObject();
        transactionOutData.put("amount", completedTransaction.getAmount());
        transactionOutData.put("dateTime", completedTransaction.getDateTime());
        this.data = transactionOutData;

        JSONArray messages = new JSONArray();
        messages.add(completedTransaction.getMessage());
        this.messages = messages;
    }

    public JSONObject getData() {
        return data;
    }

    public JSONArray getMessages() {
        return messages;
    }
}
