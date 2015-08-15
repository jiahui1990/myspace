package javacommon.javautil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javacommon.base.action.BaseAction;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPFile;

/**
 *Title:文件服务类
 *Description: 文件服务类
 *@author 
 *@version Revision: 1.0 
 *@time：2014-7-17 下午11:03:04   
 */
public class FileService extends BaseAction {
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 3899157061401191866L;
	private static  ResourceBundle	  bundle = ResourceBundle.getBundle("jdbc");		
	
	private static FTPClient ftp;

	/**
	 * 
	 * @param path
	 *            上传到ftp服务器哪个路径下
	 * @param addr
	 *            地址
	 * @param port
	 *            端口号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */

	private static boolean connect(String path, String addr, String port,
			String username, String password) throws Exception {
		try {
			long startTime=System.currentTimeMillis();   //获取开始时间
			boolean result = false;
			ftp = new FTPClient();
			int reply;
			int p  = Integer.parseInt(port);
			ftp.connect(addr, p);
			ftp.login(username, password);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				closeFtp();
				return result;
			}
			ftp.changeWorkingDirectory(path);
			result = true;
			long endTime=System.currentTimeMillis(); //获取结束时间
			System.out.println("链接FTP耗时：" + (endTime - startTime));
			return result;
		} catch (Exception e) {
			System.out.println("ftp连接失败：" + e.getMessage());
			return false;
		}
	}
	/**
    * 功能：将本地路径下的文件上传到服务器
    * 返回值：true：上传成功，false：上传不成功
    * 关联函数：
    * 作者：QinChaun
	 * @param file 上传的文件
	 * @param savePath 上传到服务器的物理路径
	 * @param fileName 文件名
	 * @return
	 * @throws IOException 
	 */
	public static boolean uploadFile(File file, String savePath, String fileName) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			new File(savePath).mkdirs();
			new File(savePath,fileName).delete();
			in = new FileInputStream(file);
			out = new FileOutputStream(new File(savePath,fileName));
			int len = 0;
			byte[] b = new byte[1024*1024];
			while((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*@SuppressWarnings("finally")
	public static boolean uploadFile(File file, String savePath, String fileName) {
		FileInputStream fis = null;
		OutputStream fos = null;
		try {
			if (connect("", bundle.getString("FtpAddress"),
					bundle.getString("FtpPort"), bundle.getString("FtpUser"),
					bundle.getString("FptPassword"))) {
				if(!ftp.changeWorkingDirectory(savePath)){
					String[] path = savePath.split("/");
					String pa = "/";
					for (String p : path) {
						if (p != null && !p.equals("")) {
							pa += p + "/";
							Boolean b2 = ftp.makeDirectory(pa);
							System.out.println("创建单个文件夹" + pa + "：" + b2);
						}
					}
				}
				Boolean b2 = ftp.makeDirectory(savePath);
				System.out.println("创建文件夹" + savePath + "：" + b2);
				Boolean b1 = ftp.changeWorkingDirectory(savePath);
				System.out.println("切换工作空间：" + b1);
				fis = new FileInputStream(file);
				TelnetOutputStream os = null;
				// 新建一个文件
				Boolean bs = ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				System.out.println("设置文件类型：" + bs);
				Boolean br = ftp.storeFile(new String(fileName.getBytes("GBK"),
						"iso-8859-1"), fis);
				System.out.println("设置文件流到远程文件：" + bs);
				System.out.println(fileName);
				fis.close();
				if (br) {
					fis.close();
					closeFtp();
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				fis.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}*/
   
   /**
    * 功能：将服务器上的文件下载到本地路径
    * 输入参数：localUrl：文件要下载到的路径（本地路径）
    * serviceUrl：要下载的文件路径（服务器路径）
    * 返回值：0：下载成功，1：下载失败
    * 关联函数：
    * 作者：
    * @param localUrl
    * @param serviceUrl
    * @return Integer
    * @roseuid 538F187903AA
    */
	public Integer downloadFile(String serviceUrl,String filename1) {
		Integer result = 0;
		OutputStream out = null;
		InputStream in = null;
		try {
			in = new FileInputStream(new File(serviceUrl));
			getRequest().setCharacterEncoding("utf-8");
			getResponse().setCharacterEncoding("utf-8");
			getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + new String(filename1.getBytes("GBK"),"ISO8859_1") + "\"");
		    out = getResponse().getOutputStream();
		    byte[] b = new byte[1024*1024];
		    int len = 0;
		    while((len = in.read(b)) != -1) {
		    	out.write(b, 0, len);
		    }
		    out.flush();
		    out.close();
		    in.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = 1;
		}
		return result;
	}
	/*@SuppressWarnings({ "resource", "null" })
	public Integer downloadFile(String serviceUrl,String filename1) {
		 Integer result = 0;  
		 OutputStream out = null;
		try {
			if (connect("",bundle.getString("FtpAddress"),bundle.getString("FtpPort"), bundle.getString("FtpUser"), bundle.getString("FptPassword"))) {
				Boolean b1=	ftp.changeWorkingDirectory(serviceUrl.substring(0,serviceUrl.lastIndexOf("/")+1));
				ftp.setControlEncoding("UTF-8");
			    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			    getRequest().setCharacterEncoding("utf-8");
				getResponse().setCharacterEncoding("utf-8");
				getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + new String(filename1.getBytes("GBK"),"ISO8859_1") + "\"");
			    out = getResponse().getOutputStream();
			    boolean ok = ftp.retrieveFile(new String(filename1.getBytes("GBK"), "iso-8859-1"), out);
			    if(ok){
			    	out.close();
			    	closeFtp();
			    }
			    return 0;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			result = -1;
		}
		return result;
		
	    
	}*/
   
   /**
    * 获取文件大小
    * @param file
    * @return
    */
   public static Long getFileSize(File file) {
		String filepath = file.getPath();
		filepath = filepath.replaceAll("\\\\", "/");
		filepath = filepath.substring(filepath.indexOf("/usersfiles"));
		long size = -1L;
		try {
			if (connect("", bundle.getString("FtpAddress"),
					bundle.getString("FtpPort"), bundle.getString("FtpUser"),
					bundle.getString("FptPassword"))) {
				ftp.setControlEncoding("GBK");
				if (ftp.listFiles().length > 0)
					size = getDirOrFileSize(filepath);
				closeFtp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
   }
   /**
	 * 将HTML编辑器类容写入服务器上的jsp文件
	 * QinChuan
    * @param jspString  HTML编辑器里面的字符串
    * @param savePath   jsp文件保存到服务器的物理地址
    * @param filename   jsp文件名
    * @return
    */
	public static boolean depositContent(String jspString, String savePath, String filename) {
		InputStream is = null;
		OutputStream out = null;
		try {
			is = new ByteArrayInputStream(jspString.getBytes("UTF-8"));
			File  f = new File(savePath);
			f.mkdirs();
			f = new File(savePath,filename);
			f.createNewFile();
			out = new FileOutputStream(f);
			int len = 0;
			byte[] b = new byte[10247*1024];
			while((len = is.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				is.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*public static boolean depositContent(String jspString, String savePath, String filename) {
		InputStream is = null;
		try {
			if (connect("",bundle.getString("FtpAddress"),bundle.getString("FtpPort"), bundle.getString("FtpUser"), bundle.getString("FptPassword"))){
				if(!ftp.changeWorkingDirectory(savePath)){
					String[] path = savePath.split("/");
					String pa = "/";
					for (String p : path) {
						if (p != null && !p.equals("")) {
							pa += p + "/";
							Boolean b2 = ftp.makeDirectory(pa);
							System.out.println("创建单个文件夹" + pa + "：" + b2);
						}
					}
				}
			Boolean b1=	ftp.changeWorkingDirectory(savePath);
			System.out.println("切换工作空间："+b1);
            is = new ByteArrayInputStream(jspString.getBytes("UTF-8"));
			Boolean bs = ftp.setFileType(FTPClient.ASCII_FILE_TYPE); 
			System.out.println("设置文件类型："+bs);
			Boolean br= ftp.storeFile(new String(filename.getBytes("GBK"), "iso-8859-1") , is); 
			System.out.println("设置文件流到远程文件："+bs);
			System.out.println(filename);
			is.close();
			if(br){
				closeFtp();
			}
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			try {
				is.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}*/
	/**
	 * QinChuan
	 * 从服务器jsp文件里面读取字符串
	 * @param file
	 * @returnx
	 */
	public static String readFile(File file) {
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		BufferedReader reader = null;
		try {
			is = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			int count = 0;
			while ((line = reader.readLine()) != null) {
				count++;
				if (count == 1)
					continue;
				sb.append(line).append("\n");
			}
			reader.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	/*public static String readFile(File file) {
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		BufferedReader reader = null;
		try {
			if (connect("",bundle.getString("FtpAddress"),bundle.getString("FtpPort"), bundle.getString("FtpUser"), bundle.getString("FptPassword"))) {
				Boolean b1=	ftp.changeWorkingDirectory(file.getParentFile().getPath());
				System.out.println("切换工作空间："+b1);
				ftp.setControlEncoding("utf-8");
			    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			    is = ftp.retrieveFileStream(new String(file.getName().getBytes("GBK"), "iso-8859-1"));
			    if(is==null){
			    	return "";
			    }
			    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			    String line = null;
				int count = 0;
				while((line=reader.readLine())!=null){
					count++;
					if(count==1)continue;
					sb.append(line).append("\n");
				}
				reader.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}*/
	
	/**
	 * 
	 * 函 数 名: deleteDirExcept
	 * 功 能：TODO (删除指点文件夹所有文件 除了expfile文件)
	 * @param file
	 * @param expfile
	 * @return boolean
	 * @Date 2014-6-11上午11:45:03
	 * @author Wangdeyong
	 */
	public static boolean deleteDirExcept(File file, File expfile){
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f:files){
				if(f.isDirectory()){
					deleteDir(f);
				}else{
					if(!f.getName().equals(expfile.getName()))
						deleteFile(f);
					
				}
			}
		}
		return true;	
	}
	/**
	 * QinChuan
	 * 删除指定目录下所有东西（文件、文件夹）
	 * @param file
	 * @return
	 */
	public static boolean deleteDir(File file) {
		String filepath = file.getPath();
		filepath=filepath.replaceAll("\\\\", "/");
		filepath=filepath.substring(filepath.indexOf("/usersfiles"));
		Integer number = 0;
		try {
			if (connect("", bundle.getString("FtpAddress"),
					bundle.getString("FtpPort"), bundle.getString("FtpUser"),
					bundle.getString("FptPassword"))) {
				ftp.setControlEncoding("GBK");
				number = deleteFTPDir(filepath);
				System.out.println("共删除:"+number+"个文件");
				closeFtp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;	
	}
	/**
	 * QinCHuan
	 * 删除文件
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file) {
		String filepath = file.getPath();
		filepath=filepath.replaceAll("\\\\", "/");
		filepath=filepath.substring(filepath.indexOf("/usersfiles"));
		boolean isDeleteSuccess=false;
		try {
			if (connect("", bundle.getString("FtpAddress"),
					bundle.getString("FtpPort"), bundle.getString("FtpUser"),
					bundle.getString("FptPassword"))) {
				ftp.setControlEncoding("GBK");
				isDeleteSuccess = deleteFTPFile(filepath);
				System.out.println("删除文件:"+filepath);
				closeFtp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleteSuccess;		
	}
	
	/**
	 * QinCHuan
	 * 得到目录下的文件
	 * @param file 下面全是文件
	 * @return
	 */
	public static int getFilesCount(File file) {
		return file.listFiles().length;
	}
	/**
	 * QinCHuan
	 * 得到目录下最后一次被修改的文件
	 * @param file
	 * @return
	 */
	public static File getLastModified(File file){
		File[] files = file.listFiles();
		File max = files[0];
		for(int i=1;i<files.length;i++){
			if(files[i].lastModified() > max.lastModified())
				max = files[i];
		}
		return max;
	}
	/*public static void main(String[] ags){
		FileUploadUtil f = new FileUploadUtil();
		f.deleteDir(new File("E:\\Photos\\2012-07-04 金牛阳光家园（图片为压缩版本，如需原图直接找我）"));
	}*/
	
	
	/**
	 * 复制文件
	 * Wangdeyong
	 * @param sourceFile 原文件地址
	 * @param targetFile 目标文件地址
	 * @throws IOException
	 */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    /**
	 * 复制文件交
	 * Wangdeyong
	 * @param sourceFile 原文件地址
	 * @param targetFile 目标文件地址
	 * @throws IOException
	 */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
   /**
    * QinChuan
    * 判断文件类型是否满足允许类型
    * @param file 上传的文件
    * @param allowedTypes 允许上传的文件类型
    * @return
    */
   public static boolean checkFormat(File file, String allowedTypes){
		String filetype = getMimeType(file);
		if(allowedTypes.indexOf(filetype) != -1){
			return true;
		}
		return false;
   }
   /**
    * QinChuan
    * 获取文件的MIME类型
    * @param file 上传的文件
    * @return
    */
   public static String getMimeType(File file){
		Magic parser = new Magic();
		MagicMatch match = null;
		try {
			match = parser.getMagicMatch(file,false,true);
		} catch (MagicParseException | MagicMatchNotFoundException | MagicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return match.getMimeType().toLowerCase();
	}
	
	
   
   /**
     * 
	 * 动态获取配置的物理路径
	 * chencong
	 * @param path
	 * @return
	 */
	public String getPhysicalPath(String path) {
		//String servletPath = this.savePath;
		/*String realPath = getSession().getServletContext().getRealPath("/");
		 //return new File(realPath) + "/" + path;
		return realPath.replace("\\", "/") + path;*/
		//System.out.println(realPath.replace("\\", "/") + path);
		return  path;
	}
	/**
	 * 根据传入的物理路径获取虚拟路径
	 * chencong
	 * @param path
	 * @return
	 */
	public String getVirtualPath(String path) {
		String realPath = getSession().getServletContext().getRealPath("/");
		return path.replace(realPath.replace("\\", "/"), "");
	}
	/**
	 * 获取文件扩展名
	 * chencong
	 * @return string
	 */
	protected String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 获取一个随机的文件名
	 * chencong
	 * @return
	 */
	private String getRandomName() {
		Random random = new Random();
		return MD5Utils.getMD5String("" + random.nextInt(10000) + System.currentTimeMillis());
	}
	/**
	 * 函 数 名:getFileNetWorkPath <br>
	 * 功 能：获取文件网络路径 IP+path <br>
	 * 输 入 参 数： <br>
	 * 返 回 值： <br>
	 * @param @param path
	 * @param @return
	 * @return String
	 * @exception 异常处理类
	 * @time：2014-7-11 下午1:04:40  
	 * @author LiuLun
	 */
	public static String getFileNetWorkPath(String path){
		path = bundle.getString("PicturePath")+path;
		return path;
	}
	/**
	 * 函 数 名:moveDirectioryOrFile <br>
	 * 功 能：移动ftp服务其上的文件或者目录至指定的地方<br>
	 * 输 入 参 数： <br>
	 * 返 回 值： <br>
	 * @param @param path
	 * @param @return
	 * @return String
	 * @exception 异常处理类
	 * @time：2014-7-19 下午1:04:40  
	 * @author LiuLun
	 */
	public static boolean moveDirectioryOrFile(String sourceFileDir, String targetDir,String targetFileName) {
		sourceFileDir = sourceFileDir.replaceAll("\\\\", "/");
		sourceFileDir = sourceFileDir.substring(sourceFileDir.indexOf("/usersfiles"));
		boolean isMoveSuccess=false;
		try {
			if (connect("", bundle.getString("FtpAddress"),
					bundle.getString("FtpPort"), bundle.getString("FtpUser"),
					bundle.getString("FptPassword"))) {
				String[] path = targetDir.split("/");
				String pa = "/";
				for (String p : path) {
					if (p != null && !p.equals("")) {
						pa += p + "/";
						Boolean b2 = ftp.makeDirectory(pa);
						System.out.println("创建单个文件夹" + pa + "：" + b2);
					}
				}
				ftp.changeWorkingDirectory(targetDir);
				isMoveSuccess=ftp.rename(new String(sourceFileDir.getBytes("GBK"),"ISO8859-1"), new String(targetFileName.getBytes("GBK"),"ISO8859-1"));
				System.out.println("将"+sourceFileDir+"移动文件至"+targetDir+":"+isMoveSuccess);
				if(isMoveSuccess){
					closeFtp();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isMoveSuccess;
	}
	/**
	 * 函 数 名:getDirOrFileSize <br>
	 * 功 能：获取指定ftp文件路径上的文件大小<br>
	 * 输 入 参 数： <br>
	 * 返 回 值： <br>
	 * @param @param path
	 * @param @return
	 * @return String
	 * @exception 异常处理类
	 * @time：2014-7-19 下午1:04:40  
	 * @author LiuLun
	 */
	private static Long getDirOrFileSize(String filepath) throws IOException{
		Long size=0L;
		FTPFile[] ftpFiles = ftp.listFiles(new String(filepath.getBytes("GBK"),"ISO8859-1"));
		for (FTPFile ftpFile : ftpFiles) {
			if (ftpFile.isFile()) {
				size += ftpFile.getSize();
			}else if(ftpFile.isDirectory()){
				size+=getDirOrFileSize(filepath+"/"+ftpFile.getName());
			}
		}
		return size;
	}
	/**
	 * 函 数 名:deleteFTPDir <br>
	 * 功 能：删除FTP服务器上指定文件或者文件夹<br>
	 * 输 入 参 数：文件路径 <br>
	 * 返 回 值： <br>
	 * @param path
	 * @param @return
	 * @return String
	 * @exception 异常处理类
	 * @time：2014-7-19 下午1:04:40  
	 * @author LiuLun
	 */
	private static Integer deleteFTPDir(String filepath) throws IOException {
		Integer number = 0;
		FTPFile[] ftpFiles = ftp.listFiles(filepath);
		String path;
		for (FTPFile ftpfile : ftpFiles) {
			if(ftpfile.getName()==null||ftpfile.getName().equals("")||ftpfile.getName().matches("^\\.+$"))continue;
			path = filepath + "/" + ftpfile.getName();
			if (ftpfile.isDirectory()) {
				number += deleteFTPDir(path);
				System.out.println("删除文件夹----"
						+ path
						+ ":"
						+ ftp.removeDirectory(new String(path.getBytes("GBK"),
								"ISO8859-1")));
			} else if(ftpfile.isFile()){
				if (deleteFTPFile(path)) {
					number++;
				} else if (deleteFTPFile(filepath)) {
					number++;
					System.out.println("删除文件---" + path + ":成功");
				} else {
					System.out.println("删除文件---" + path + ":失败");
				}
			}
		}
		ftp.removeDirectory(new String(filepath.getBytes("GBK"), "ISO8859-1"));
		return number;
	}
	/**
	 * 函 数 名:deleteFTPFile <br>
	 * 功 能：删除FTP服务器上指定文件<br>
	 * 输 入 参 数：文件路径 <br>
	 * 返 回 值： <br>
	 * @param path
	 * @param @return
	 * @return String
	 * @exception 异常处理类
	 * @time：2014-7-19 下午1:04:40  
	 * @author LiuLun
	 */
	private static boolean deleteFTPFile(String filepath) throws IOException{
		return ftp.deleteFile(new String(filepath.getBytes("GBK"),"ISO8859-1"));
	}
	/**
	 * 函 数 名:deleteExpireDirOrFile <br>
	 * 功 能：删除FTP服务器上过期文件<br>
	 * 输 入 参 数：文件路径 <br>
	 * 返 回 值： <br>
	 * @param path
	 * @param @return
	 * @return String
	 * @exception 异常处理类
	 * @time：2014-7-19 下午1:04:40  
	 * @author LiuLun
	 */
	public static Integer deleteExpireDirOrFile(String filepath,Date expireDate){
		filepath = filepath.replaceAll("\\\\", "/");
		filepath = filepath.substring(filepath.indexOf("/usersfiles"));
		Integer number = 0;
		try {
			if (connect("", bundle.getString("FtpAddress"),
					bundle.getString("FtpPort"), bundle.getString("FtpUser"),
					bundle.getString("FptPassword"))) {
				ftp.setControlEncoding("GBK");
				number=deleteFTPExpireDirOrFile(filepath,expireDate);
				System.out.println("共删除:"+number+"个文件");
				closeFtp();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return number;
	}
	/**
	 * 函 数 名:deleteFTPExpireDirOrFile <br>
	 * 功 能：删除FTP服务器过期文件<br>
	 * 输 入 参 数：文件路径 <br>
	 * 返 回 值： <br>
	 * @param filepath expireDate
	 * @param @return
	 * @return String
	 * @exception 异常处理类
	 * @time：2014-7-19 下午1:04:40  
	 * @author LiuLun
	 */
	private static Integer deleteFTPExpireDirOrFile(String filepath,Date expireDate) throws IOException{
		Integer number = 0;
		FTPFile[] ftpFiles = ftp.listFiles(filepath);
		String path;
		for (FTPFile ftpfile : ftpFiles) {
//			String[] modify=ftpfile.get
			if(ftpfile.getName()==null||ftpfile.getName().equals("")||ftpfile.getName().matches("^\\.+$"))continue;
			path = filepath + "/" + ftpfile.getName();
			if (ftpfile.isDirectory()) {
				number += deleteFTPDir(path);
				System.out.println("删除文件夹----"
						+ path
						+ ":"
						+ ftp.removeDirectory(new String(path.getBytes("GBK"),
								"ISO8859-1")));
			} else if(ftpfile.isFile()&&ftpfile.getTimestamp().getTime().before(expireDate)){
				if (deleteFTPFile(path)) {
					number++;
				} else if (deleteFTPFile(filepath)) {
					number++;
					System.out.println("删除文件---" + path + ":成功");
				} else {
					System.out.println("删除文件---" + path + ":失败");
				}
			}
		}
		ftp.removeDirectory(new String(filepath.getBytes("GBK"), "ISO8859-1"));
		return number;
	}
	
	/**
	 *关闭FTP的方法
	 *void
	 *@time：2014-7-22 下午3:55:12   
	 *@author yf
	 */
	private static void closeFtp(){
		Boolean b = null;
		try {
			b = ftp.logout();
    	System.out.println("退出登录："+b);
			ftp.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
