package jp.gmo.auth.exception;

import lombok.Data;

@Data
public class FieldError {
    private final String itemName;
    private final String message;
}
