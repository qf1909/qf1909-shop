package com.qf.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qf.service.IpayService;
import com.qf.service.impl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class PayController {

    @Autowired
    private PayServiceImpl payService;

    @RequestMapping("success")
    public String getSuccess(){
        return "success";
    }

    @RequestMapping("pay")
    public String showPay(){
        return "pay";
    }


    @RequestMapping("doPay")
    public void doPay(@RequestParam  String uuid, HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse) throws IOException {
       payService.doPay(uuid,httpRequest,httpResponse);

    }

    @RequestMapping("notifyUrl")
    @ResponseBody
    public void notifyUrl(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException {

        Map<String, String[]> map = request.getParameterMap();


        HashMap<String, String> hashMap = new HashMap<>();//将异步通知的参数存到map中
        Set<Map.Entry<String, String[]>> set = map.entrySet();
        for (Map.Entry<String, String[]> entry:set){
            String[] value = entry.getValue();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < value.length-1; i++) {
                stringBuffer.append(value[i]+",");
            }
            stringBuffer.append(value[value.length-1]);
            hashMap.put(entry.getKey(),stringBuffer.toString());

        }

        boolean signVerified = AlipaySignature.rsaCheckV1(hashMap,
               "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyrJDK3t0KIzooQKVSzCXqPpQMRQqMb2BVuJPCAX7WnLoDhsbJo8SnfVM+eWOmgyHEo3GVRnCNo6kH5dLdfPJaEK3Xcni1vERZ5DgGYUpjr1KaNz8rpvhQ4CHd0BdPHUvtbAuHxF+HehD9sm8/druS1T5DIaOg1HG+2h+COyrXgBNyFhBPk8ePAwNws+jGeoDkNAjcmY6DeMcqUSTfEX5ErACsw2fiE0qvK1CxbK0DLZFxuWU5Q5HSfPR7wS2vm/j6XhHeFoKwCpQEQOIWqBqaNE7plq91zfpck2tEIxLsIlYO9PyVvuif5N5fPudqAcIfjIRUyeGMVudk9CvVMdw/wIDAQAB+eWOmgyHEo3GVRnCNo6kH5dLdfPJaEK3Xcni1vERZ5DgGYUpjr1KaNz8rpvhQ4CHd0BdPHUvtbAuHxF+HehD9sm8/druS1T5DIaOg1HG+2h+COyrXgBNyFhBPk8ePAwNws+jGeoDkNAjcmY6DeMcqUSTfEX5ErACsw2fiE0qvK1CxbK0DLZFxuWU5Q5HSfPR7wS2vm/j6XhHeFoKwCpQEQOIWqBqaNE7plq91zfpck2tEIxLsIlYO9PyVvuif5N5fPudqAcIfjIRUyeGMVudk9CvVMdw/wIDAQAB",
               "utf-8" ,
                "RSA2");
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if(hashMap.get("out_trade_no").equals("20200313105750004")&&
                    hashMap.get("total_amount").equals("2000000.88")){
                System.out.println("金额正确，验签成功");//要去数据库中改变订单状态
                //TODO response.getWriter().write("json");

            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }

    }





    }
