package com.sdwfqin.spring_boot_demo.handle;

import com.sdwfqin.spring_boot_demo.enums.ResultEnum;
import com.sdwfqin.spring_boot_demo.utils.exception.ServiceException;
import com.sdwfqin.spring_boot_demo.utils.result.Result;
import com.sdwfqin.spring_boot_demo.utils.result.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@Log4j2
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return ResultUtils.errorData(serviceException.getCode(), serviceException.getMessage(), e.getMessage());
        } else if (e instanceof ConstraintViolationException
                || e instanceof MissingServletRequestParameterException) {
            return ResultUtils.errorData(ResultEnum.VALID_ERROR, e.getMessage());
        }
        log.error("【系统异常】{}", e);
        return ResultUtils.errorData(ResultEnum.ERROR_UNKONE, e.getMessage());
    }
}
