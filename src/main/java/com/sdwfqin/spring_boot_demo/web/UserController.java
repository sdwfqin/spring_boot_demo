package com.sdwfqin.spring_boot_demo.web;

import com.sdwfqin.spring_boot_demo.enums.ResultEnum;
import com.sdwfqin.spring_boot_demo.bean.User;
import com.sdwfqin.spring_boot_demo.service.UserService;
import com.sdwfqin.spring_boot_demo.utils.JWTUtil;
import com.sdwfqin.spring_boot_demo.utils.result.Result;
import com.sdwfqin.spring_boot_demo.utils.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService mUserService;

    /**
     * 获取全部用户
     */
    @GetMapping("/getAllUser")
    public Result getAllUser() {

        List<User> allUser = mUserService.getAllUser();

        return ResultUtils.resultData(ResultEnum.SUCCESS, allUser);
    }

    /**
     * 分页获取全部用户
     */
    @GetMapping("/getAllUserPage")
    public Result getAllUserPage(@RequestParam("current") String current,
                                 @RequestParam(value = "size", defaultValue = "10") String size,
                                 @RequestParam(value = "state", defaultValue = "0") String state) {

        List<User> userList =
                mUserService.getAllUserPage(Integer.parseInt(current), Integer.parseInt(size), Integer.parseInt(state));

        return ResultUtils.resultData(ResultEnum.SUCCESS, userList);
    }

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        String realPassword = mUserService.getPassword(username);
        if (realPassword != null && realPassword.equals(password)) {
            String token = JWTUtil.createToken(username);
            return ResultUtils.resultData(ResultEnum.SUCCESS, token);
        } else {
            return ResultUtils.errorData(ResultEnum.ERROR, "");
        }
    }

    @PostMapping("/login/v2")
    public Result loginV2(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request) {
        String token = mUserService.getToken(username, password, request);
        return ResultUtils.resultData(ResultEnum.SUCCESS, token);
    }
}
