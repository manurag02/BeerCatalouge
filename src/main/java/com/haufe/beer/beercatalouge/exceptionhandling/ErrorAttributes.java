package com.haufe.beer.beercatalouge.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ErrorAttributes {

    private String code;
    private String message;
    private int status;
    private List<String> params;
}
