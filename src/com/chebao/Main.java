package com.chebao;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * HttpPost 使用方法
 */
public class Main {

    public static void main(String[] args) {

        new ReadByPost().start();

    }
}

class ReadByPost extends Thread {

    //doctype=<doctype>  指定为json 或 xml
    //http://fanyi.youdao.com/openapi.do?keyfrom=cheboa-test&key=1383843413&type=data&doctype=<doctype>&version=1.1&q=要翻译的文本

    @Override
    public void run() {

        try {


            //定义URL对象
            URL url = new URL("http://fanyi.youdao.com/openapi.do");
            //打开连接,
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //为连接设定参数
            connection.addRequestProperty("encoding", "UTF-8");
            //设为true,当前connection可以从网络获取数据
            connection.setDoInput(true);
            //设为true,当前connection可以向网络传输数据
            connection.setDoOutput(true);
            //connection访问方法
            connection.setRequestMethod("POST");


            //输出流(向服务器提交数据)
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);


            //向服务器提交数据(要求返回的数据格式是:doctype=xml / doctye=json)
            bw.write("keyfrom=cheboa-test&key=1383843413&type=data&doctype=xml&version=1.1&q=welcome");
            //强制输出
            bw.flush();


            //输入流(下载)
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //临时存数据
            String line;
            //字符串生成器
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                //追加填充builder
                builder.append(line);
            }

            //关闭流
            bw.close();
            osw.close();
            os.close();
            br.close();
            isr.close();
            is.close();

            //输出数据
            System.out.println(builder.toString());



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
