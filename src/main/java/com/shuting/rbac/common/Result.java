package com.shuting.rbac.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{
    private String code;
    private String msg;
    private T data;
    private Long total;
    private Boolean isSuccess = Boolean.TRUE;

    public static <T> Result<T> success() {return new Result<>();}
    public static <T> Result<T> success(T data) {
        Result<T> result = success();
        result.setData(data);
        return result;
    }
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setMsg(msg);
        result.setCode(null);
        return result;
    }
    public static <T> Result<T> error(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public Boolean isSuccess() {
        return CodeEnum.SUCCESS.getCode().equals(code);
    }
}
