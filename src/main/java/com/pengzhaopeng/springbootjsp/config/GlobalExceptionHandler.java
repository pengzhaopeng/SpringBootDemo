package com.pengzhaopeng.springbootjsp.config;

import com.alibaba.fastjson.JSONObject;
import com.pengzhaopeng.springbootjsp.common.ErrorMessage;
import com.pengzhaopeng.springbootjsp.common.LogicException;
import com.pengzhaopeng.springbootjsp.common.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        RestResult result = new RestResult(false, "-1", e.getMessage(), ErrorMessage.SYSTEM_EXCEPTION);
        //如果是业务逻辑异常，返回具体的错误码与提示信息
        if (e instanceof LogicException) {
            LogicException logicException = (LogicException) e;
            result.setCode(logicException.getCode());
            result.setErrorMessage(logicException.getErrorMsg());
        } else {
            //对系统级异常进行日志记录
            logger.error("系统异常:" + e.getMessage(), e);
        }
        return JSONObject.toJSON(result);
    }
}