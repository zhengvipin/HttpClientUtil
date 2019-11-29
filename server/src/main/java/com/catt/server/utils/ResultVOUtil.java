package com.catt.server.utils;

import com.catt.server.vo.ResultVO;

public class ResultVOUtil {
    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(200);
        resultVO.setMsg("success");
        resultVO.setData(data);
        return resultVO;
    }

    public static <T> ResultVO<T> error(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(400);
        resultVO.setMsg("error");
        resultVO.setData(data);
        return resultVO;
    }
}
