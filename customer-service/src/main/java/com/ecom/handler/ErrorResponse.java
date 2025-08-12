package com.ecom.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorResponse {
    Map<String, String> errors;

}
