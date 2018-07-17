package com.wechat.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * token验证类(用于微信公众号后台与程序的绑定)
 * @author Dick
 *
 */
public class CheckUtils {
	
	private static final String token = "wechat";   //验证token字符，需与微信公众号平台填写一致
	
	/**
	 * 
	 * @param signature  签名
	 * @param timestamp  时间戳
	 * @param nonce      加密字符串
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		
		String[] arr = new String[]{token, timestamp, nonce};
		
		// 1.排序	 
		Arrays.sort(arr);
		
		// 2.生成字符串
		StringBuffer content = new StringBuffer();
		
		for(int i = 0; i < arr.length; i++){
			
			content.append(arr[i]);
			
		}
		
		// 3.sha1加密
		String temp = getSha1(content.toString());

		System.out.println("content"+content + "\n"+ "signature"+signature + "\n" +"temp"+ temp);
		return temp.equals(signature); 
	}

	/**
	 * sha1 加密
	 * @param str
	 * @return
	 */
	public static String getSha1(String str){
	    if (null == str || 0 == str.length()){
	        return null;
	    }
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	            'a', 'b', 'c', 'd', 'e', 'f'};
	    try {
	        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	        mdTemp.update(str.getBytes("UTF-8"));
	         
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char[] buf = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(buf);
	    } catch (Exception e) {
	        return null;
	    } 
	}

}
