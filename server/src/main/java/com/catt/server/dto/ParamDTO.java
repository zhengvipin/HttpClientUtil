package com.catt.server.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ParamDTO {

    @Min(value = 0, message = "当前页数必填")
    private Integer page;

    @Min(value = 0, message = "分页大小必填")
    private Integer pageSize;

    @NotEmpty(message = "关键字必填")
    private String search;
}
