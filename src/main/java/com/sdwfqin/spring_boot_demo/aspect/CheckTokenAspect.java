package com.sdwfqin.spring_boot_demo.aspect;

import com.sdwfqin.spring_boot_demo.bean.DeviceTokenBean;
import com.sdwfqin.spring_boot_demo.enums.DeviceEnum;
import com.sdwfqin.spring_boot_demo.enums.ResultEnum;
import com.sdwfqin.spring_boot_demo.service.RedisService;
import com.sdwfqin.spring_boot_demo.utils.Constant;
import com.sdwfqin.spring_boot_demo.utils.exception.ServiceException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权
 * <p>
 *
 * @author 张钦
 * @date 2019/9/9
 */
@Aspect
@Order(2)
@Component
public class CheckTokenAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisService redisService;

    /**
     * 拦截这个类的所有方法
     */
    @Pointcut("execution(public * com.sdwfqin.spring_boot_demo.web.*.*(..))")
    public void webLog() {
    }

    /**
     * 请求内容
     */
    @Before("webLog()")
    public void doBefore() throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.error(request.getServletPath());

        String token = request.getHeader(Constant.TOKEN);
        String header = request.getHeader(Constant.DEVICE_TYPE);

        if (StringUtils.isEmpty(header)) {
            throw new ServiceException(ResultEnum.VALID_ERROR);
        }

        if (request.getServletPath().startsWith("/user")) {
            return;
        }

        if (StringUtils.isEmpty(token)) {
            throw new ServiceException(ResultEnum.VALID_ERROR);
        }

        String userId = redisService.getString(token);
        DeviceTokenBean deviceTokenBean = redisService.getObject(userId, DeviceTokenBean.class);

        if (deviceTokenBean == null) {
            throw new ServiceException(ResultEnum.TOKEN_ERROR);
        }

        String isLastToken = "";

        if (DeviceEnum.Android.getType().equals(header)) {
            isLastToken = deviceTokenBean.getAndroid();
        } else if (DeviceEnum.Ios.getType().equals(header)) {
            isLastToken = deviceTokenBean.getIos();
        } else if (DeviceEnum.Web.getType().equals(header)) {
            isLastToken = deviceTokenBean.getWeb();
        }

        if (!token.equals(isLastToken)) {
            throw new ServiceException(ResultEnum.TOKEN_ERROR);
        }
    }
}
