package com.scu.nfc;

import java.io.IOException;

import com.scu.utils.Consts;
import com.scu.utils.StringUtil;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;

public class NfcUtil {
	
	public static String read(Intent m_intent){
		String str = null;
		if(m_intent!=null){
			byte[] passw =  Consts.CARD_PASSWORD;
				
		    int add = Consts.TICKET_ID_BLOCK;
	
		    Tag m_tagFromIntent = m_intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);   
		    if(m_tagFromIntent!=null){
	           MifareClassic mfc = MifareClassic.get(m_tagFromIntent); 
               /*String  metaInfo="";
               metaInfo += "卡片类型：" + mfc.getType() + "\n共" + mfc.getSectorCount() + "个扇区\n共"   
                + mfc.getBlockCount() + "个块\n存储空间: " + mfc.getSize() + "B\n"; 
               System.out.println("metaInfo:"+metaInfo);*/
		       if(mfc!=null){      
					try {
						mfc.connect(); 									
						int bSec = mfc.blockToSector(add);
						if(bSec<mfc.getSectorCount())
						{
							/*byte[] aPwd = new byte[16];
							aPwd[0] = Consts.A_PASSWORD;*/
							if(mfc.authenticateSectorWithKeyA(bSec, passw))
							{
								System.out.println("密码正确");
								byte[] data = mfc.readBlock(add);
								if(data!=null)
								{
									/*str = bytesToHexString(data);
									System.out.println("str:"+str);*/
									str = new StringUtil().getCardTicketId(data);
									//System.out.println("str1:"+str1);
								}
								else{
									System.out.println("data为空");
								}
							}else{
								System.out.println("读取失败，A密码错误");
							}
						}
						else{
							System.out.println("check your password");
						}
						mfc.close();
					} catch (IOException e) {
						e.printStackTrace();
					} 
		        }
		    }             
		}
		else
		{
			System.out.println("Please send the card to identify areas!");
		}
		
		return str;
	}
	
	//字符序列转换为16进制字符串   
    private static String bytesToHexString(byte[] src) {   
        StringBuilder stringBuilder = new StringBuilder("0x");   
        if (src == null || src.length <= 0) {   
            return null;   
        }   
        char[] buffer = new char[2];   
        for (int i = 0; i < src.length; i++) {   
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);   
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);   
            System.out.println(buffer);   
            stringBuilder.append(buffer);   
        }   
        return stringBuilder.toString();   
    }  
	public static byte[] stringToBytes(String hexString) {    
		   if (hexString == null || hexString.equals("")) {    
		       return null;    
		   }    
		   hexString = hexString.toUpperCase();    
		   int length = hexString.length() / 2;    
		   char[] hexChars = hexString.toCharArray();    
		   byte[] d = new byte[length];    
		   for (int i = 0; i < length; i++) {    
		       int pos = i * 2;    
		       d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));    
		   }    
		   return d;    
		}  

	private static byte charToByte(char c) {    
	   return (byte) "0123456789ABCDEF".indexOf(c);    
	}
	
}
