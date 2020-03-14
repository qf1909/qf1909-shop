package com.qf.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
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

    @RequestMapping("success")
    public String getSuccess(){
        return "success";
    }

    @RequestMapping("pay")
    public String showPay(){
        return "pay";
    }


    @RequestMapping("doPay")
    public void doPay(@RequestParam  String oid, HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse) throws IOException {
        /**
         *参数说明：
         * 1. 支付网关
         * 2.应用id
         * 3.商户应用私钥
         * 4.返回的数据类型： json
         * 5.字符集： utf-8
         * 6. 支付宝公钥  注意，是支付宝公钥，不是商户公钥
         * 7. 密钥生成方式： RSA2
         */
         AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016101700710374",
                "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",
                "JSON",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyrJDK3t0KIzooQKVSzCXqPpQMRQqMb2BVuJPCAX7WnLoDhsbJo8SnfVM+eWOmgyHEo3GVRnCNo6kH5dLdfPJaEK3Xcni1vERZ5DgGYUpjr1KaNz8rpvhQ4CHd0BdPHUvtbAuHxF+HehD9sm8/druS1T5DIaOg1HG+2h+COyrXgBNyFhBPk8ePAwNws+jGeoDkNAjcmY6DeMcqUSTfEX5ErACsw2fiE0qvK1CxbK0DLZFxuWU5Q5HSfPR7wS2vm/j6XhHeFoKwCpQEQOIWqBqaNE7plq91zfpck2tEIxLsIlYO9PyVvuif5N5fPudqAcIfjIRUyeGMVudk9CvVMdw/wIDAQAB+hVdDOTxNlW1uTXUneP1/JJKpCHLN4VT3zRYNDCehu2ow4yg6vv+6m9aiK0E0vl1BvWykLuwC8MfveqYazGXRGos165jgPFcyN9LIG4ztL6vrHH8etIaElu7AdeaQnBox3b+puWlmGOmiLq83MtLxcHZHCuw4yqZrZTNnOsZyqgWgp+xPXVfhUD7HsuQcXiv47FboSt6yaVQpmSXXYHo68Idr3mxYPM6X4pZyQwuLkoYBB4sJPmwE2525sJSWOP9nwQG3ypAT6+kLk5kYK1IurtpVP5g2m2wIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:9085/success");
        alipayRequest.setNotifyUrl("http://ggkuq4.natappfree.cc/notifyUrl");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+oid+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":2000000.88," +
                "    \"subject\":\"Iphone16 16G\"," +
                "    \"body\":\"Iphone16 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

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
