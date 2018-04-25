package util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author demo
 */
public class HttpUtilTest {
    /**
     *带参数的get请求
     * @throws IOException
     */
    @Test
    public void testGet() throws IOException {
        //创建client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClients.createMinimal();
        //Name
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name","dai"));
        list.add(new BasicNameValuePair("password","123456"));
        String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(list, "UTF-8"));
        String url = "http://www.baidu.com" + "?" + paramsStr;
        // 创建httpget请求
        HttpGet httpGet = new HttpGet(url);
        System.out.println("executing request " + httpGet.getURI());

            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
           int status =  closeableHttpResponse.getStatusLine().getStatusCode();
           //获取主体
            System.out.println(status);
            //获取返回的主体
            HttpEntity entity =closeableHttpResponse.getEntity();
            String string = EntityUtils.toString(entity,"utf-8");
            System.out.println(string);
            //关闭response和client
            closeableHttpResponse.close();
            httpClient.close();
    }

    @Test
    public void testPost() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("user","dai"));
        nameValuePairs.add(new BasicNameValuePair("password","123"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs,"UTF-8");
      HttpPost httpPost1 = new HttpPost("http://www.baidu.com");
        httpPost1.setEntity(urlEncodedFormEntity);
      CloseableHttpResponse closeableHttpResponse = (CloseableHttpResponse) httpClient.execute(httpPost1);
        System.out.println(closeableHttpResponse);
     ProtocolVersion protocolVersion = closeableHttpResponse.getStatusLine().getProtocolVersion();
        System.out.println(protocolVersion);



    }
}