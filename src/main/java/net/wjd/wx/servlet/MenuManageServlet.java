package net.wjd.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.wjd.wx.model.Token;
import net.wjd.wx.model.menu.Button;
import net.wjd.wx.model.menu.ClickButton;
import net.wjd.wx.model.menu.ComplexButton;
import net.wjd.wx.model.menu.Menu;
import net.wjd.wx.model.menu.ViewButton;
import net.wjd.wx.util.CommonUtil;
import net.wjd.wx.util.MenuUtil;
import net.wjd.wx.util.PropertiesUtil;

/**
 * Servlet implementation class MenuManageServlet
 */
@WebServlet("/MenuManageServlet")
public class MenuManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Menu menu = createMenu();
		String appid = PropertiesUtil.getProperty("appid");
		String appsecret = PropertiesUtil.getProperty("appsecret");
		Token token = CommonUtil.getToken(appid, appsecret);
		String accessToken = token.getAccessToken();
		MenuUtil.createMenu(menu, accessToken);
	}
	
	private Menu createMenu(){
		ClickButton btn1 = new ClickButton();
		btn1.setName("今日歌曲");
		btn1.setType("click");
		btn1.setKey("V1001_TODY_MUSIC");
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("登录");
		btn2.setType("view");
		btn2.setUrl("http://wangjidong.ngrok.io/wxPlatform/login.jsp");
		
		ClickButton btn31 = new ClickButton();
		btn31.setName("hello world");
		btn31.setType("click");
		btn31.setKey("V1001_HELLO_WORLD");
		
		ClickButton btn32 = new ClickButton();
		btn32.setName("赞一下我们");
		btn32.setType("click");
		btn32.setKey("V1001_GOOD");
		
		ComplexButton btn3 = new ComplexButton();
		btn3.setName("菜单");
		btn3.setSub_button(new Button[]{btn31,btn32});
		
		Menu menu = new Menu();
		menu.setButton(new Button[]{btn1,btn2,btn3});
		
		String jsonMenu = JSONObject.fromObject(menu).toString();
		
		System.out.println(jsonMenu);
		
		return menu;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
