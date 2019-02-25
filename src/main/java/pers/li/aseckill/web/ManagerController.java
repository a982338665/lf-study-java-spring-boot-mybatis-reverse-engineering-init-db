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
@RequestMapping("/manager")
public class ManagerController
{
    private static Logger log= LoggerFactory.getLogger(ManagerController.class);

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    @Autowired
    SUserService sUserService;
    @Autowired
    ManagerService managerService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
/*
    @RequestMapping("/index")
    public String index(Model model,SUser user){
        List<SGoodsVo> sGoodsVos = sGoodService.listSGoodsVo();
        model.addAttribute("user",user);
        model.addAttribute("goodsList",sGoodsVos);
        return "index";
    }*/
    @RequestMapping("/save/index")
    public String saveIndex(Model model,SUser user){
        if(user==null){
            return "login";
        }
        model.addAttribute("code","-1");
        model.addAttribute("msg","");
        return "index";
    }
    @RequestMapping("/save/info")
    public String saveInfo(@ModelAttribute ServicePublishDto dto,Model model,SUser user){
        if(user==null){
            return "login";
        }
        try{
            if(dto!=null&&dto.getEnvType()!=null&&!"".equals(dto.getEnvType())){
                dto.setHospital(dto.getHospital().split("_")[0]);
                dto.setHospitalDept(dto.getHospitalDept().split("_")[0]);
                dto.setDoctor(dto.getDoctor().split("_")[0]);
                dto.setStartTime(dto.getStartTime().trim().substring(0,4)+"-"+dto.getStartTime().trim().substring(4,6)+"-"+dto.getStartTime().trim().substring(6,8));
                dto.setEndTime(dto.getEndTime().trim().substring(0,4)+"-"+dto.getEndTime().trim().substring(4,6)+"-"+dto.getEndTime().trim().substring(6,8));
                int x=sendHTTP(dto);
                if(x==1){
                    model.addAttribute("code","1");
                    model.addAttribute("msg","添加成功！");
                }else{
                    model.addAttribute("code","0");
                    model.addAttribute("msg","插入失败！");
                }
                model.addAttribute("code","-1");
                model.addAttribute("msg","");
            }
        }catch(Exception e){
                model.addAttribute("code","0");
                model.addAttribute("msg","插入异常:"+e.getMessage());
        }
        return "redirect:/manager/save/index";
    }

    private int sendHTTP(ServicePublishDto dto) {
        String url="";
        if(dto.getEnvType().equals("0")){
            url="http://192.168.1.245:8501/v1/remote-clinics-doctor/save";
        }else {
            url="http://api.jiukangguoji.cn/mem/v1/remote-clinics-doctor/save";
        }
        HttpPost httpPost2 = getHttpPost(url, JSON.toJSONString(dto));
        String result2 = getResponResult(httpPost2);
        int i = Integer.parseInt(result2);
        return i;
    }
    /**
     * 获取httppost请求
     * @param uri
     * @param
     * @return
     */
    private static HttpPost getHttpPost(String uri, String json) {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(json,"utf-8"));
        return httpPost;
    }

    /**
     * 获取响应结果---string类型
     * @param
     * @return  [{"key":"Authorization","value":"Basic cm9vdDpKS2dqMjAxOA==","description":""}]
     */
    private static <T> String getResponResult(T http) {
        CloseableHttpResponse response = null;
        try {
            if(http instanceof HttpPut){
                response = httpclient.execute((HttpPut)http);
            }else if(http instanceof HttpPost){
                response = httpclient.execute((HttpPost)http);
            }else if(http instanceof HttpGet){
                response = httpclient.execute((HttpGet)http);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    @RequestMapping("/select/doctor")
    @ResponseBody
    public List<String> select( String doctor,String envType,SUser user){
        if(Integer.parseInt(envType)==0){
            return managerService.getBYNameTest(doctor);
        }else{
            return managerService.getBYName(doctor);
        }
    }
    @RequestMapping("/select/hospital")
    @ResponseBody
    public List<String> selecthospital( String hospital,String envType,SUser user){
        if(Integer.parseInt(envType)==0){
            return managerService.getHospitalBYNameTest(hospital);
        }else{
            return managerService.getHospitalBYName(hospital);
        }
    }
    @RequestMapping("/select/dept")
    @ResponseBody
    public List<String> selectdept(String hospital,String envType,SUser user){
        if(Integer.parseInt(envType)==0){
            return managerService.getDeptBYNameTest(hospital.split("_")[0]);
        }else{
            return managerService.getDeptBYName(hospital.split("_")[0]);
        }
    }
}