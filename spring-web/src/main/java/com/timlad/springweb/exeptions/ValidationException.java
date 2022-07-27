package com.timlad.springweb.exeptions;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ValidationException extends RuntimeException{
    private List<String> errorsFieldsMessages;

    public ValidationException(List<String> errorsFieldsMessages) {
        super(errorsFieldsMessages.stream().collect(Collectors.joining(", ")));
        this.errorsFieldsMessages = errorsFieldsMessages;
    }
}
