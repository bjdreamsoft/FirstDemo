package wanghl.com.uploadDemo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import wanghl.com.uploadDemo.utils.FileUtils;

public class FileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter pw = null;//定义输出流
		
		JSONObject result = new JSONObject();
		
		//获取文件的临时存储目录
		String tempPath = req.getServletContext().getRealPath(FileUtils.UPLOAD_TEMP);
		
		FileItemFactory factory = null;
		
		ServletFileUpload upload = null;
		
		try {
			
			pw = resp.getWriter();
			
			//创建diskfileItem工厂类
			factory = new DiskFileItemFactory();
			
			//创建上传文件解析器
			upload = new ServletFileUpload(factory);
			
			//设置解析器编码
			upload.setHeaderEncoding("UTF-8");
			
			//解析器从请求中进行解析
			List<FileItem> items = upload.parseRequest(req);
			
			//对解析出来的元素进行迭代
			Iterator<FileItem> iterator = items.iterator();
			
			String fileName = "";
			String ext = "";
			File file = null;
			String realName = "";
			
			while(iterator.hasNext()) {
				
				//获取每个请求中的元素
				FileItem item = iterator.next();
				
				if(item.isFormField()) {
					//如果元素是表单普通元素,则不予处理
					continue;
				}
				
				fileName = item.getName();
				
				if(fileName.contains("\\")) {
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
				}
				
				ext = fileName.substring(fileName.lastIndexOf(".")+1);
				
				realName =  FileUtils.getUUID()+"."+ext;
				
				file = new File(tempPath,realName);			
				
				item.write(file);
				
				String type = FileUtils.getRealType(file);
				
				result.put("result", true);
				result.put("filename", realName);
				result.put("type", "文件真实类型："+(type==null?"未识别类型":type));
				
				System.out.println(result.toString());
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			result.put("result", false);
			
		}finally {
			
			pw.write(result.toString());
			
			if(pw!=null) {
				pw.flush();
			}
		}
		
	}
	
}
