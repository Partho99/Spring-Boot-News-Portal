package com.etcenteprise.newsoftheearth.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MathematicalException.class)
    public ResponseEntity<Object> exception(MathematicalException exception) {

        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    public final ResponseEntity<ErrorInfo> handleException(Exception ex, ErrorInfo errorInfo, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof IOException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            MathematicalException e = (MathematicalException) ex;
            return handleMathematicalException(e, errorInfo, headers, status, request);

        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Unknown exception type: " + ex.getClass().getName());
            }
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }


    protected ResponseEntity<ErrorInfo> handleMathematicalException(MathematicalException ex, ErrorInfo errorInfo, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleMathematicalException(ex, new ErrorInfo(errors), headers, status, request);
    }

    protected ResponseEntity<ErrorInfo> handleExceptionInternal(Exception ex, @Nullable ErrorInfo body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}