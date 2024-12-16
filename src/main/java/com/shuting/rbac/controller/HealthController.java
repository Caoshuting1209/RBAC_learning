package com.shuting.rbac.controller;

import com.shuting.rbac.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public Result<String> health() {
        return Result.success("Up");
    }
}
