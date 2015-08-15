package javacommon.javautil;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * @Description: TODO(图片处理)
 * @author qinchuan
 * @version V0.1
 * @date 2014-6-9 下午4:01:04
 *
 */
public class ImageService {
	/**
	 * 剪切图片
	 * @param suffix 后缀
	 * @param sourcePath 原图片位置
	 * @param targetPath 剪切后图片存放位置
	 * @param point1 
	 * @param point2
	 * @throws Exception
	 */
	public static void cutImage(String suffix, String sourcePath, String targetPath,int x1, int y1, int x2, int y2) throws Exception {
		Image img;
		ImageFilter cropFilter;
		File sourceImgFile = new File(sourcePath);
		BufferedImage bi = ImageIO.read(sourceImgFile);
		int srcWidth = bi.getWidth();
		int srcHeight = bi.getHeight();
		//float ratio=((float)srcHeight)/300;
		/*x1=(int) (x1*ratio);
		x2=(int) (x2*ratio);
		y1=(int) (y1*ratio);
		y2=(int) (y2*ratio);*/
		int destWidth = x2 - x1;
		int destHeight = y2 - y1;
		//System.out.println("ratio"+ratio+"width:"+srcHeight+" height:"+srcHeight+" x1:"+x1+" x2:"+x2+" y1:"+y1+" y2:"+y2);
		if (srcWidth >= destWidth && srcHeight >= destHeight) {
			Image image = bi.getScaledInstance(srcWidth, srcHeight,
					Image.SCALE_DEFAULT);
			cropFilter = new CropImageFilter(x1, y1, destWidth, destHeight);
			img = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(image.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(destWidth, destHeight,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();
			ImageIO.write(tag, suffix, new File(targetPath));		
		}
	}
	
	/*public static void main(String[] args){
		Point p1 = new Point(200, 200);
		Point p2 = new Point(400, 400);
		try {
			cutImage("jpg", "D:/Photos/领小树/http_imgload[4].jpg", "F:/a.jpg", p1,p2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("图片剪切失败！");
		}
	}*/
	/**
	 * 
	 * 函 数 名: rotate90
	 * 功 能：TODO (顺时针旋转90度)
	 * @param bi
	 * @return BufferedImage
	 * @Date 2014-6-11下午6:09:28
	 * @author Wangdeyong
	 */
	  public static  BufferedImage rotate90(BufferedImage bi)
	   {
	       int width = bi.getWidth();
	       int height = bi.getHeight();
	        
	       BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
	       for(int i=0; i<width; i++)
	           for(int j=0; j<height; j++)
	               biFlip.setRGB(height-1-j, i, bi.getRGB(i, j));
	       
	       return biFlip;
	   }
	   
	  /**
	   * 
	   * 函 数 名: rotateImg
	   * 功 能：TODO (根据图片原路径srcPath旋转后生成新的图片destPath，action是旋转的方向参数 right 和left)
	   * @param srcPath 
	   * @param destPath
	   * @param action 
	   * @Date 2014-6-12上午9:48:09
	   * @author Wangdeyong
	   */
	  public static void rotateImg(String srcPath, String destPath, String action){
		   try {
			File img = new File(srcPath);
			File dest = new File(destPath);
			BufferedImage image = ImageIO.read(img);
			//顺时针转90度
			if(action.equals("right")){
				image = ImageService.rotate90(image);
			}else {
				//逆时针90度
				image = ImageService.rotate_90(image);
			}
			
			ImageIO.write(image, "jpg", img);
			img.renameTo(dest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	  
	  /**
	   * 
	   * 函 数 名: getImgHW
	   * 功 能：TODO (根据图片文件srcPath路径  获取图片的300*X缩放大小)
	   * @param srcPath
	   * @return String  height + "-" + width
	   * @Date 2014-6-12上午9:53:16
	   * @author Wangdeyong
	   */
	  public static int[]  getImg300HW(String srcPath){
		  int height = 0;
		  int width = 0;
		  int hw[] = new int[2];
		  try {
			BufferedImage image = ImageIO.read(new File(srcPath));
			height = image.getHeight();
			width = image.getWidth();
			 if(height > width){
				 width = 300 * width / height;
				 height = 300;
			 }else if(height < width){
				 height = 300 * height / width;
				 width = 300;
			 }else{
				 width = 300;
				 height = 300;
			 }
			 hw[0] = height;
			 hw[1] = width;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return hw;
	  }
	  /**
	   * 
	   * 函 数 名: rotate_90
	   * 功 能：TODO (逆时针旋转90度)
	   * @param bi
	   * @return BufferedImage
	   * @Date 2014-6-11下午6:09:55
	   * @author Wangdeyong
	   */
	   public static  BufferedImage rotate_90(BufferedImage bi)
	   {
	       int width = bi.getWidth();
	       int height = bi.getHeight();
	        
	       BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
	       for(int i=0; i<width; i++)
	           for(int j=0; j<height; j++)
	               biFlip.setRGB(j, width-1-i, bi.getRGB(i, j));
	       
	       return biFlip;
	   }
	
	/**
	 * 
	 * 函 数 名: cutImageAll
	 * 功 能：TODO (根据前台传来的点坐标数据 放大并裁减图片  里面包含了cutImage这个方法)
	 * @param suffix
	 * @param sourcePath
	 * @param targetPath
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @throws Exception void
	 * @Date 2014-6-10下午8:44:21
	 * @author Wangdeyong
	 */
	public static  void cutImageAll(String suffix, String sourcePath, String targetPath,int x1, int y1, int x2, int y2) throws Exception {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(sourcePath));
		
			double height = img.getHeight() *1.0;
			double width = img.getWidth() * 1.0;
			
		   //先要对图片进行缩放
		/*    try {
				ImageService.resize(path, path, 300);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		  //1 截图 
		  double x11 = x1 * 1.0;
		  double y11 = y1 * 1.0;
		  double x22 = x2 * 1.0;
		  double y22 = y2 * 1.0;
		 //放大点位
		   if(width > height){
			   double s = 300.0 * height / width;
			   x11 = x11 * (width / 300);
			   x22 = x22 * (width / 300);
			   y11 = y11 * (height / s);
			   y22 = y22 * (height / s);
		   }else if(width < height){
			   double s = 300.0 * width / height;
			   x11 = x11 * (width / s);
			   x22 = x22 * (width / s);
			   y11 = y11 * (height / 300);
			   y22 = y22 * (height / 300);
		   }
		   //if(point1.getPointx() != null && point1.getPointy() != null && point2.getPointx() != null && point2.getPointy() != null){
		    int a1 = (int)x11;
		   int a2 = (int)x22;
		   int b1 = (int)y11;
		   int b2 = (int)y22;
			ImageService.cutImage("jpg", sourcePath, targetPath, a1, b1,a2,b2);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * 实现图像的等比缩放 * @param source * @param targetW * @param targetH * @return
	 **/
	/*public static void resize(String srcPath, String  destPath,  
            int newWidth) throws IOException {  
		float quality = 0.9999f;
		File originalFile = new File(srcPath);
		File resizedFile = new File(destPath);
		try {
		
        if (quality > 1) {  
            throw new IllegalArgumentException(  
                    "Quality has to be between 0 and 1");  
        }  
  
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());  
        Image i = ii.getImage();  
        Image resizedImage = null;  
  
        int iWidth = i.getWidth(null);  
        int iHeight = i.getHeight(null);  
  
        if (iWidth > iHeight) {  
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)  
                    / iWidth, Image.SCALE_SMOOTH);  
        } else {  
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,  
                    newWidth, Image.SCALE_SMOOTH);  
        }  
  
        // This code ensures that all the pixels in the image are loaded.  
        Image temp = new ImageIcon(resizedImage).getImage();  
  
        // Create the buffered image.  
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
  
        // Copy image to buffered image.  
        Graphics g = bufferedImage.createGraphics();  
  
        // Clear background and paint the image.  
        g.setColor(Color.white);  
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
        g.drawImage(temp, 0, 0, null);  
        g.dispose();  
  
        // Soften.  
        float softenFactor = 0.05f;  
        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
        Kernel kernel = new Kernel(3, 3, softenArray);  
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
        bufferedImage = cOp.filter(bufferedImage, null);  
  
        // Write the jpeg to a file.  
        FileOutputStream out = new FileOutputStream(resizedFile);  
  
        // Encodes image as a JPEG data stream  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
  
        JPEGEncodeParam param = encoder  
                .getDefaultJPEGEncodeParam(bufferedImage);  
  
        param.setQuality(quality, true);  
  
        encoder.setJPEGEncodeParam(param);  
        encoder.encode(bufferedImage);  
    	
		} catch (Exception e) {
			// TODO: handle exception
		}
    } // Example usage  
*/  
	
	/**
	 * 实现图像的等比缩放 * @param source * @param targetW * @param targetH * @return
	 **/
	/*public static void resize(String sourcePath, int targetW,int targetH, String destPath) {
		
		BufferedImage source = null;
		BufferedImage target = null;
		File destFile = new File(destPath);
		try {
			File sourceFile = new File(sourcePath);
			source = ImageIO.read(sourceFile);
		
			// targetW，targetH分别表示目标长和宽
			int type = source.getType();
			
			double sx = (double) targetW / source.getWidth();
			double sy = (double) targetH / source.getHeight();
			// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
			// 则将下面的if else语句注释即可
//			if (sx < sy) {
//				sx = sy;
//				targetW = (int) (sx * source.getWidth());
//			} else {
//				sy = sx;
//				targetH = (int) (sy * source.getHeight());
//			}
			if (type == BufferedImage.TYPE_CUSTOM) { // handmade
				ColorModel cm = source.getColorModel();
				WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
						targetH);
				boolean alphaPremultiplied = cm.isAlphaPremultiplied();
				target = new BufferedImage(cm, raster, alphaPremultiplied, null);
			} else
				target = new BufferedImage(targetW, targetH, type);
			Graphics2D g = target.createGraphics(); // smoother than exlax:
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
			g.dispose();
			ImageIO.write( target, "jpeg" , destFile );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}  */

}
