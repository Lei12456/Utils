package com.briup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.util.QRCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年8月9日 下午6:33:03 
* 类说明 
*/
@Controller
@Api(description = "创建二维码接口",tags = "CreateQrCodeController")
public class CreateQrCodeController {
	@RequestMapping(value = "createQr",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("创建二维码")
	public String createQr(String imgPath,String text,String pan) throws Exception{
		// 存放在二维码中的内容
        // 嵌入二维码的图片路径
        // 生成的二维码的路径及名称
        String destPath = pan+":" + System.currentTimeMillis() + ".jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
        return "创建成功";
	}
}	
