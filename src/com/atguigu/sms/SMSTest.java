package com.atguigu.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SMSTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// 1. 创建 HttpClient 的实例
		CloseableHttpClient client = HttpClientBuilder.create().build();
		// 2. 创建某种连接方法的实例
		HttpPost httpPost = new HttpPost("http://192.168.10.252:9999/sms_test/sms");
		// 3. 调用第一步中创建好的实例的execute方法来执行第二步中创建好的链接类实例
		List<NameValuePair> list = new ArrayList<>();
		NameValuePair namevaluePair = new BasicNameValuePair("phonenum", "18301302014");
		NameValuePair msg = new BasicNameValuePair("msg", "just a test");
		list.add(namevaluePair);
		list.add(msg);
		httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
		// 配置完参数后，再进行执行
		CloseableHttpResponse response = client.execute(httpPost);
		// 4. 读response获取HttpEntity
		HttpEntity entity = response.getEntity();
		// 5. 对得到后的内容进行处理
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(entity, "UTF-8");
			System.out.println(entity);
			System.out.println(result);

		} else {
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(EntityUtils.toString(entity));
		}
		// 6. 释放连接。无论执行方法是否成功，都必须释放连接
		EntityUtils.consume(entity);
		client.close();

	}

}
