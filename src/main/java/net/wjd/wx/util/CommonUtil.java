package net.wjd.wx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;
import net.wjd.wx.model.Token;

public class CommonUtil {
	
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?"
			+ "grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		
		JSONObject jsonObject = null;
		
		try{
			TrustManager[] tm = {new MyX509TrustManager()};
			
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			
			conn.setDoInput(true);
			
			conn.setUseCaches(false);
			
			conn.setRequestMethod(requestMethod);
			
			if(null != outputStr){
				OutputStream outputStream = conn.getOutputStream();
				
				outputStream.write(outputStr.getBytes("UTF-8"));
				
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			
			StringBuffer buffer = new StringBuffer();
			
			while((str=bufferedReader.readLine())!=null){
				buffer.append(str);
			}
			
			bufferedReader.close();
			
			inputStreamReader.close();
			
			inputStream.close();
			
			inputStream = null;
			
			conn.disconnect();
			
			jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(ConnectException ce){
			ce.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return jsonObject;
	}

	public static Token getToken(String appid,String appsecret){
		Token token = null;
		
		String requestUrl = token_url.replace("APPID", appid)
				.replace("APPSECRET", appsecret);
		
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				token = null;
				System.out.println("ªÒ»°token ß∞‹£¨errcode:"+jsonObject.getString("errcode")
						+"£¨errmsg:"+jsonObject.getString("errmsg"));
				e.printStackTrace();
			}
		}
		return token;
	}
	
}
