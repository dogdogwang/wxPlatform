package net.wjd.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private static Properties props = new Properties();  
	
	static{
		InputStream in = null;
        try {
        	//in = new BufferedInputStream(new FileInputStream(""));  
        	in = PropertiesUtil.class.getClassLoader().getResourceAsStream("cfg/config.properties");
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	
	public static String getProperty(String key) {  
		
		String value = props.getProperty(key);  
        
		return value;
    }  
	
	public static void main(String[] args) {
		
		String token = PropertiesUtil.getProperty("token");
		System.out.println(token);
		
		String appid = PropertiesUtil.getProperty("appid");
		System.out.println(appid);
	}

}
