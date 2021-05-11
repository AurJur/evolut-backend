package lt.tyz.evolut.presentation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;

public class RepresentationOut {

    private final JSONObject data;
    private final JSONObject status;

    public RepresentationOut(JSONObject data, boolean isSuccess, HttpStatus statusCode, JSONArray messages) {
        this.data = data;
        JSONObject status = new JSONObject();
        status.put("isSuccess", isSuccess);
        status.put("statusCode", statusCode);
        status.put("messages", messages);
        this.status = status;
    }

    public JSONObject getData() {
        return data;
    }

    public JSONObject getStatus() {
        return status;
    }

//        private class ResponseStatus {
//
//        private final JSONObject status;
//
//        private ResponseStatus(boolean isSuccess, HttpStatus statusCode, JSONArray messages) {
//            JSONObject status = new JSONObject();
//            status.put("isSuccess", isSuccess);
//            status.put("statusCode", statusCode);
//            status.put("messages", messages);
//            this.status = status;
//        }
//    }
}
