package com.catt.server.controller;

import com.catt.server.dto.ParamDTO;
import com.catt.server.utils.ResultVOUtil;
import com.catt.server.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class ServerController {

    @PostMapping("/login")
    public ResultVO login(@RequestParam String username, @RequestParam String password) {
        if ("root".equals(username) && "root".equals(password)) {
            return ResultVOUtil.success("登录成功");
        } else {
            return ResultVOUtil.error("登录失败");
        }
    }

    @GetMapping("/hello")
    public ResultVO login(@RequestParam(name = "name", defaultValue = "Smith", required = false) String name) {
        String text = String.format("Hello,%s,my dear friend.", name);
        return ResultVOUtil.success(text);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestBody @Valid ParamDTO paramDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        } else {
            return ResultVOUtil.success(paramDTO);
        }
    }
}
