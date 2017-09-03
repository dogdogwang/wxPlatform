package net.wjd.wx.model.menu;

import net.sf.json.JSONObject;

public class Menu {
	
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
	
	public static void main(String[] args) {
		
		ClickButton btn1 = new ClickButton();
		btn1.setName("今日歌曲");
		btn1.setType("click");
		btn1.setKey("V1001_TODY_MUSIC");
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("歌手简介");
		btn2.setType("view");
		btn2.setUrl("http://www.qq.com");
		
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
		
		
	}
	
}
