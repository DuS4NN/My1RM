package com.my1rm.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ValidatorResponse {

    private boolean isValid;
    private Object message;
}
