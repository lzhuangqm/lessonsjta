package com.cn.control;

/**
 * USER控制器
 */
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserControl {

    @Autowired
    private UserService userService;

    public UserControl() {
    }

    @RequestMapping(value="/save",method = RequestMethod.GET)
    public String save() {
        userService.insert();
        return "保存成功！";
    }
}
