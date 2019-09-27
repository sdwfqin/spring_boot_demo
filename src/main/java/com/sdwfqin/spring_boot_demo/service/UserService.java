package com.sdwfqin.spring_boot_demo.service;

import com.github.pagehelper.PageHelper;
import com.sdwfqin.spring_boot_demo.bean.DeviceTokenBean;
import com.sdwfqin.spring_boot_demo.bean.User;
import com.sdwfqin.spring_boot_demo.enums.DeviceEnum;
import com.sdwfqin.spring_boot_demo.enums.ResultEnum;
import com.sdwfqin.spring_boot_demo.mapper.UserMapper;
import com.sdwfqin.spring_boot_demo.utils.Constant;
import com.sdwfqin.spring_boot_demo.utils.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * User控制器
 * <p>
 *
 * @author 张钦
 * @date 2019/8/31
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    public List<User> getAllUser() {

        return userMapper.getAll();
    }

    public List<User> getAllUserPage(int current, int size, int state) {
        PageHelper.startPage(current, size);
        List<User> allPage = userMapper.getAllPage(state);
        return allPage;
    }

    public String getPassword(String username) {
        User user = userMapper.getUser(username);

        redisService.addObject(user.getId(), user, 30 * 24 * 60 * 60 * 1000L);

        return user.getPassword();
    }

    public String getToken(String username, String password, HttpServletRequest request) {
        User user = userMapper.getUser(username);

        if (user == null){
            throw new ServiceException(ResultEnum.LOGIN_ERROR);
        }

        if (!user.getPassword().equals(password)){
            throw new ServiceException(ResultEnum.LOGIN_ERROR);
        }

        String header = request.getHeader(Constant.DEVICE_TYPE);

        String token = UUID.randomUUID().toString();

        DeviceTokenBean tokenBean = redisService.getObject(user.getId(), DeviceTokenBean.class);

        if (tokenBean == null) {
            tokenBean = new DeviceTokenBean();
        }

        if (DeviceEnum.Android.getType().equals(header)) {
            tokenBean.setAndroid(token);
        } else if (DeviceEnum.Ios.getType().equals(header)) {
            tokenBean.setIos(token);
        } else if (DeviceEnum.Web.getType().equals(header)) {
            tokenBean.setWeb(token);
        } else {
            throw new ServiceException(ResultEnum.VALID_ERROR);
        }

        redisService.addObject(user.getId(), tokenBean, 30 * 24 * 60 * 60 * 1000L);
        redisService.addString(token, user.getId(), 30 * 24 * 60 * 60 * 1000L);

        return token;
    }
}
