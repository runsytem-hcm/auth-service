package jp.gmo.auth.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jp.gmo.auth.exception.FieldError;
import lombok.Data;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseCommon {
    @JsonProperty(value = "data", index = 1)
    private Object result;

    @JsonProperty(value = "message", index = 2)
    private String message;

    @JsonProperty(value = "errors", index = 3)
    private List<FieldError> errors;

    private Long timestamp;
}

