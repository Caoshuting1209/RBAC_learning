package com.shuting.rbac.exception;

import com.shuting.rbac.common.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {
    private CodeEnum codeEnum;
}
