package pers.li.aseckill.web;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.entity.SMenu;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.service.ManagerService;
import pers.li.aseckill.service.SUserService;
import pers.li.aseckill.vo.ServicePublishDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/menu")
public class MenuController
{
    private static Logger log= LoggerFactory.getLogger(MenuController.class);

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    @Autowired
    SUserService sUserService;
    @Autowired
    ManagerService managerService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @RequestMapping("/index")
    public String saveIndex(Model model,SUser user){
        if(user==null){
            return "login";
        }
        List<SMenu> list=sUserService.getMenu(user.getId());
        model.addAttribute("menuList",list);
        model.addAttribute("user",user);
        return "menu";
    }
}