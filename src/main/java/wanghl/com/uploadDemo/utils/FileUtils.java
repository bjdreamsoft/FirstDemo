package wanghl.com.uploadDemo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

public class FileUtils {
	
	public final static String UPLOAD_TEMP = "/temp";
	public final static String UPLOAD_DIR = "E:/upload";
	public static Map<String, String> map;
	
	static {
		//将配置文件中的内容加载到静态map中
		map = new HashMap<String,String>();
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/wanghl/com/uploadDemo/utils/fileType.properties");
		
		Properties prop = new Properties();
		
		try {
			prop.load(is);
			
			for(Entry<Object, Object> entry : prop.entrySet()) {
				map.put(entry.getKey().toString(), entry.getValue().toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteFileByPath(String path) {
		File file = new File(path);
		deleteFileByFile(file);
	}
	
	private static void deleteFileByFile(File file) {
		
		if(file.exists()) {
			file.delete();
		}
		
	}
	
	/**
	 * 获取文件的实际存储目录
	 * @return
	 */
	public static String getDirPath() {
		Calendar cal = Calendar.getInstance();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(UPLOAD_DIR).append(File.separator).append(cal.get(Calendar.YEAR))
			.append(File.separator).append(cal.get(Calendar.MONTH)+1).append(File.separator)
			.append(Calendar.DAY_OF_MONTH);
		
		return sb.toString();
	}
	
	public static void copyFile(String source,String target) throws Exception {
		
		FileInputStream is = null;
		FileOutputStream os = null;
		
		FileChannel schannel = null;
		FileChannel tchannel = null;
		
		try {
			//实例化目标文件
			File sfile = new File(source);
			
			if(!sfile.exists()) {
				//如果文件不存在，则抛出文件未找到异常
				throw new FileNotFoundException();
			}
			
			is = new FileInputStream(sfile);
			
			//实例化目标目录
			File tdir = new File(target);
			
			if(!tdir.exists()) {
				//如果目标目录不存在，则创建目录
				tdir.mkdirs();
			}
			
			File tfile = new File(target+File.separator+sfile.getName());
			
			os = new FileOutputStream(tfile);
			
			
			schannel = is.getChannel();
			tchannel = os.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
			
			while(schannel.read(buffer)!=-1) {
				buffer.flip();
				tchannel.write(buffer);
				buffer.clear();
			}
			
		}finally {
			if(is!=null) {
				is.close();
			}
			if(os!=null) {
				os.close();
			}
			if(schannel!=null) {
				schannel.close();
			}
			if(tchannel!=null) {
				tchannel.close();
			}
		}
	}

	/**
	 * 获取UUID
	 * @return UUID
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s;
	}
	
	public static String getRealType(File file) {
		
		InputStream is = null;
		byte[] bytes = null;
		
		try {
			is = new FileInputStream(file);
			
			bytes = new byte[4];
			
			is.read(bytes, 0, bytes.length);
			
		}catch (Exception e) {

		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String ext = bytesToHexString(bytes);
		
		return map.get(ext);
		
	}

	 public static String bytesToHexString(byte[] src) {
		 StringBuilder stringBuilder = new StringBuilder();
		 
		 if (src == null || src.length <= 0) {
			 return null;
		 }
		 for (int i = 0; i < src.length; i++) {
			 int v = src[i] & 0xFF;
			 String hv = Integer.toHexString(v);
			 if (hv.length() < 2) {
				 stringBuilder.append(0);
			 }
			 stringBuilder.append(hv);
		 }
		 return stringBuilder.toString();
	}
	
	
}
