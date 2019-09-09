package com.sdwfqin.spring_boot_demo.web;

import com.sdwfqin.spring_boot_demo.enums.ResultEnum;
import com.sdwfqin.spring_boot_demo.utils.result.Result;
import com.sdwfqin.spring_boot_demo.utils.result.ResultUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
@RestController
@Validated
@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String Hello() {
        return "Hello";
    }

    @GetMapping("/helloV2")
    public Result HelloV2() {
        Result<String> result = new Result<>();
        result.setCode(0);
        result.setMsg("操作成功");
        result.setData("helloV2");
        return result;
    }

    /**
     * restful api
     */
    @GetMapping("/helloV3")
    public Result HelloV3() {
        return ResultUtils.resultData(ResultEnum.SUCCESS, "helloV3");
    }

    /**
     * 测试捕获异常
     */
    @GetMapping("/helloV4")
    public Result HelloV4() {

        String test = "helloV4";
        Integer.parseInt(test);

        return ResultUtils.resultData(ResultEnum.SUCCESS, "helloV4");
    }
}
