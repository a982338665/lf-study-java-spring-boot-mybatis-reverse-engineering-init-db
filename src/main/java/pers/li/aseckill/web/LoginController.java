package pers.li.aseckill.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.result.Result;
import pers.li.aseckill.service.SUserService;
import pers.li.aseckill.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/login")
//@Transactional
public class LoginController
{
    private static Logger log= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SUserService sUserService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }
    @RequestMapping("/login")
    @ResponseBody
    public Result<String> login(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        return Result.success(sUserService.login(response,loginVo));
    }

    @RequestMapping("/login2")
    @ResponseBody
    public void login2() throws Exception {
        try{
            sUserService.login2();
        }catch (Exception e){
            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //手动开启事务回滚
            System.err.println("---------------");
            throw new  Exception();
        }
    }


}