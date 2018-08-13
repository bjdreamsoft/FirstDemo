package wanghl.com.uploadDemo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import wanghl.com.uploadDemo.utils.FileUtils;

public class SaveForm extends HttpServlet {
	
	private static final long serialVersionUID = 7433406658274202075L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		JSONObject obj = new JSONObject();
		
		obj.put("result", false);
		
		String attach = req.getParameter("attach");
		
		//获取文件的临时存储目录
		String tempPath = req.getServletContext().getRealPath(FileUtils.UPLOAD_TEMP);
		
		String source = tempPath + File.separator + attach;
		
		String target = FileUtils.getDirPath();
		
		try {
			FileUtils.copyFile(source, target);
			
			//删除临时文件
			FileUtils.deleteFileByPath(source);
			
			obj.put("result", true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = resp.getWriter();
		
		pw.write(obj.toString());
		
		pw.flush();
		
	}
}
