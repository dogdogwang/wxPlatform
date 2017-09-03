package net.wjd.wx.util;

import net.sf.json.JSONObject;
import net.wjd.wx.model.menu.Menu;

public class MenuUtil {
	
	public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?"
			+ "access_token=ACCESS_TOKEN";

	public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?"
			+ "access_token=ACCESS_TOKEN";
	
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?"
			+ "access_token=ACCESS_TOKEN";
	
	public static boolean createMenu(Menu menu,String accessToken){
		boolean result = false;
		
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		
		String jsonMenu = JSONObject.fromObject(menu).toString();
		
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
		
		if(null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0 == errorCode)
				result = true;
			else{
				result = false;
				System.out.println("菜单创建失败，errcode："+errorCode+"，errmsg："+errorMsg);
			}
		}
		return result;
	}
	
	public static String getMenu(String accessToken){
		String result = null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		if(null != jsonObject){
			result = jsonObject.toString();
		}
		return result;
	}
	
	public static boolean deleteMenu(String accessToken){
		
		boolean result = false;
		String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0 == errorCode)
				result = true;
			else{
				result = false;
				System.out.println("菜单创建失败，errcode："+errorCode+"，errmsg："+errorMsg);
			}
		}
		return result;
		
	}
	
}
