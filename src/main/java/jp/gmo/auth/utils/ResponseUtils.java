package jp.gmo.auth.utils;

import java.util.List;

import jp.gmo.auth.exception.FieldError;
import jp.gmo.auth.response.ResponseCommon;

public class ResponseUtils {

    public static ResponseCommon success(Object data, String message) {

    	ResponseCommon res = new ResponseCommon();
        res.setResult(data);
        res.setMessage(message);
        res.setTimestamp(Utils.getDateTimeCurrent());

        return res;
    }

    public static ResponseCommon errors(String message) {
    	ResponseCommon res = new ResponseCommon();
        res.setMessage(message);
        return res;
    }

    public static ResponseCommon errors(List<FieldError> data) {
    	ResponseCommon res = new ResponseCommon();
        res.setErrors(data);
        return res;
    }
}
