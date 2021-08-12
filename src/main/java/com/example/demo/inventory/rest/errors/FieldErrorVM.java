package com.example.demo.inventory.rest.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class FieldErrorVM implements Serializable {
    private static final long serialVersionUID = 5885275907744758191L;

    private final String objectName;
    private final String field;
    private final String message;
}
