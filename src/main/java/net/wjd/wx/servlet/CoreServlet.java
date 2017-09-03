package net.wjd.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wjd.wx.service.CoreService;
import net.wjd.wx.util.SignUtil;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
