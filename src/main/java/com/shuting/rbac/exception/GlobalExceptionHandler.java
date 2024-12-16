package com.shuting.rbac.exception;

import com.shuting.rbac.common.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.hutool.poi.excel.sax.ElementName.v;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException e) {
        log.error(e.getMessage(), e);
        List<FieldError> fieldErrors = e.getFieldErrors();
        String errorMessage = joinFieldErrors(fieldErrors);
        return Result.error(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errorMessage = joinFieldErrors(fieldErrors);
        return Result.error(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> constraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String errorMessage = constraintViolations.stream()
                .map(v-> "Property: " + v.getPropertyPath() +
                        ", Value: "
                        + v.getInvalidValue() + ", Failed, Reason: " + v.getMessage())
                .collect(Collectors.joining(", "));
        return Result.error(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> illegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public Result<String> globalException(GlobalException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCodeEnum().getCode(), e.getCodeEnum().getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    public String joinFieldErrors(List<FieldError> fieldErrors) {
        if (fieldErrors == null || fieldErrors.isEmpty()) {
            return "No errors";
        }
        return fieldErrors.stream()
                .map(v -> "Property: " + v.getField()+
                        ", Value: "
                        + v.getRejectedValue() + ", Failed, Reason: " + v.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }
}
