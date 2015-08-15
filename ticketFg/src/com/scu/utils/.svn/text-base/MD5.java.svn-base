/**
 * 
 */
package com.scu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author huangcheng
 * @dtae 2014-8-14
 * @time ����08:23:34
 */
public class MD5 {
    
    // ȫ������
    private final static String[] strDigits = { "a", "b", "2", "z", "4", "5",
            "6", "7", "8", "y", "a", "g", "c", "d", "1", "f" };


    // ������ʽΪ���ָ��ַ�
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }


    // ת���ֽ�����Ϊ16�����ִ�
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
    
 // ͨ���ļ����ȡ���ļ���Ӧ���ַ�����
	public static byte[] getBytesFromFile(String fileName) throws IOException {
		File file = new File(fileName);// �����ļ�
		InputStream is = new FileInputStream(file);
		// ��ȡ�ļ���С
		long length = file.length();
		System.out.println("file length..." + length);
		if (length > Integer.MAX_VALUE) {
			// �ļ�̫���޷���ȡ
			throw new IOException("File is to large " + file.getName());
		}
		// ����һ������������ļ����
		byte[] bytes = new byte[(int) length];
		// ��ȡ��ݵ�byte������
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
		&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		// ȷ��������ݾ��ȡ
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		// Close the input stream and return bytes
		is.close();
		return bytes;

	}
	
	//��ȡ�ļ���md5ֵ
    public static String GetMD5Code(String fileName) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() �ú����ֵΪ��Ź�ϣֵ����byte����
            long startTime = System.currentTimeMillis();
            resultString = byteToString(md.digest(getBytesFromFile(fileName)));
            long endTime = System.currentTimeMillis();
            System.out.println("execute time .." + (endTime - startTime));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultString;
    }
    
  //��ȡ�ļ���md5ֵ
    public static String GetMD5CodeByBytes(byte[] bytes) {
    	if(bytes == null || bytes.length == 0) return "ERROR";
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() �ú����ֵΪ��Ź�ϣֵ����byte����
            long startTime = System.currentTimeMillis();
            resultString = byteToString(md.digest(bytes));
            long endTime = System.currentTimeMillis();
            System.out.println("execute time .." + (endTime - startTime));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } 
        return resultString;
    }
    
    public static File getImgFromWeb(String imgUrl, String uploadPath) {
        try {
            URL url = new URL(imgUrl);
            String fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length());
            File outFile = new File(uploadPath + UUID.randomUUID().toString().replace("-", "") + fileType);
            OutputStream os = new FileOutputStream(outFile);
            InputStream is = url.openStream();
            byte[] buff = new byte[1024];
            while (true) {
                int readed = is.read(buff);
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buff, 0, temp, 0, readed);
                os.write(temp);
            }
            is.close();
            os.close();
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static byte[] getImgBytesFromWeb(String imgUrl) {
        try {
        	ArrayList<byte[]> bytesList = new ArrayList<byte[]>();
            URL url = new URL(imgUrl);
            InputStream is = url.openStream();
            byte[] buff = new byte[1024];
            long count = 0;
            while (true) {
                int readed = is.read(buff);
                count = count + readed;//��¼���ܹ��ж೤
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buff, 0, temp, 0, readed);
                bytesList.add(temp);//�������е��ֽ�����
            }
            System.out.println("this img size is..." + count);
            is.close();
            if(count > Integer.MAX_VALUE) {
            	throw new IOException("File is to large ");
            }
            byte[] imgBytes = new byte[(int)count + 1];
            System.out.println("imgBytes size is ..." + imgBytes.length);
            int indexSize = 0;
            for(int i = 0; i < bytesList.size(); i++) {
            	System.arraycopy(bytesList.get(i), 0, imgBytes, indexSize, bytesList.get(i).length);
            	indexSize = indexSize + bytesList.get(i).length;
            }
            return imgBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(MD5.GetMD5Code("g:/111.png"));
    	//MD5.getImgFromWeb("http://cps.360kad.com//ads/adimg1370661130.jpg", "g:/");
    	//System.out.println(MD5.GetMD5CodeByBytes(getImgBytesFromWeb("http://cps.360kad.com//ads/adimg1370661130.jpg")));
    }
}
