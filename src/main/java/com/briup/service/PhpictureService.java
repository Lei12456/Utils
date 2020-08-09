package com.briup.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.ExecutionError;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年8月9日 下午6:02:44 
* 类说明 
*/
@Service
public class PhpictureService {
	
	public void downloadPicture(String name,String pan) throws Exception {
		String targetPath = pan+"://"+name+System.currentTimeMillis();
        new File(targetPath).mkdir();
        int count = 0;

        InputStream is = null;
        FileOutputStream fos = null;
        try
        {
            URL url = new URL("https://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word="+name);
            is = url.openStream();

            int len;
            byte[] buffer = new byte[1024];
            StringBuilder pageText_ = new StringBuilder();
            while ((len = is.read(buffer)) != -1)
            { pageText_.append(new String(buffer,0,len, StandardCharsets.UTF_8)); }

            String pageText = pageText_.toString();
            Pattern compile = Pattern.compile("https://.*?0\\.jpg");
            Matcher matcher = compile.matcher(pageText);
            ArrayList<String> URLs = new ArrayList<>();

            while (matcher.find())
            {
                String eachURLStr = matcher.group();

                if (URLs.contains(eachURLStr))
                { continue; }

                count ++;
                //System.out.println("正在下载第"+ count +"张图片…………");
                URL eachURL = new URL(eachURLStr);
                is = eachURL.openStream();
                fos = new FileOutputStream(targetPath+ "\\" + System.currentTimeMillis()+".jpg");
                while ((len = is.read(buffer)) != -1)
                { fos.write(buffer,0,len); }

                is.close();
                fos.flush();
                fos.close();
                URLs.add(eachURLStr);
            }
        }
        catch (IOException e)
        {
            System.out.println("对不起，下载错误，请重试");
            e.printStackTrace();
        }
        finally
        {
            System.out.println("下载完成，共下载了"+ count +"图片，请到  "+targetPath+"  目录下查看");
            if (is != null)
            {
                try
                { is.close(); }
                catch (IOException e)
                { e.printStackTrace(); }
            }
            if (fos != null)
            {
                try
                { fos.close(); }
                catch (IOException e)
                { e.printStackTrace(); }
            }
        }
	}
}
