package com.briup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.service.PhpictureService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年8月9日 下午5:57:50 
* 类说明 
*/
@Controller
@Api(description = "爬百度图片接口",tags = "PhpictureController")
public class PhpictureController {
	@Autowired
	private PhpictureService service;
	
	@RequestMapping(value = "downloadPicture",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("下载图片")
	public String downloadPicture(String name,String pan){
		try {
			System.out.println(name+pan);
			service.downloadPicture(name, pan);
		} catch (Exception e) {
			e.printStackTrace();
			return "下载错误,请重试";
		}
		return "下载成功";
	}
	
	
	
	
}
