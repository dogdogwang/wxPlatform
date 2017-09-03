package net.wjd.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wjd.wx.service.CoreService;
import net.wjd.wx.util.SignUtil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class CoreController implements InitializingBean {
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(1111111111);
		
	}
	
	@RequestMapping("/coreService")
	public void serviceCenter(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if("GET".equals(request.getMethod().toUpperCase())){
			doGet(request, response);
		}else if("POST".equals(request.getMethod().toUpperCase())){
			doPost(request, response);
		}
		
	}
	
	private void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			System.out.println("��֤�ɹ���������");
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	
	private void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{

		
		//��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//���ղ�����΢�ż���ǩ����ʱ����������
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		System.out.println("signature="+signature);
		System.out.println("timestamp="+timestamp);
		System.out.println("nonce="+nonce);
		
		PrintWriter out = response.getWriter();
		
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			String respXml = CoreService.processRequest(request);
			out.print(respXml);
			System.out.println(respXml);
		}
			
			
		out.close();
		out = null;
		
	}

}
