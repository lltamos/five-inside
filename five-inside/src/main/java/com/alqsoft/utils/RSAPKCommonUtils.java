package com.alqsoft.utils;

import java.io.UnsupportedEncodingException;

import org.alqframework.security.RSAUtils;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author sunhuijie
 *
 * @date 2017年3月30日
 *
 */
public class RSAPKCommonUtils {

	private static Logger logger = LoggerFactory.getLogger(RSAPKCommonUtils.class);

	// private static String publicKey =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiahhmGlsiwtIgRNX0HgHDeqsmCPtGqZ3fiig5TLv9Qqj/On4yCSk/xx+I2esiNT1z0WhWqTnN9UbbyfmmFpqhXcpC8PyLXrbPcK/F7jldGreXtiTfBcBFyIQ33rCL7AE4mrcYnz7yYc036db2fzenWDlAR7srJUQ8xFZ6ZzVRZwIDAQAB";
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGL7fkAo/YP6Ezl4PJ+odkV1nor+iwAhIWSF7V/rHhzK7yxtcyJ0uVnHCcPn9Cn7Evah0PfSc90QrZLOf2HgpxqXiaJy+0tiqfte+8JdjxJDgMEWkndDhAPbRTYuVk1rv2uS1PeclvFNiCELcFA1zCPa/rtXo91N28msGA5nmOYQIDAQAB";

	public static enum CharSet {
		UTF8 {
			public String getName() {
				return "UTF-8";
			}
		};
		public abstract String getName();
	}

	/**
	 * 根据公钥加密
	 * 
	 * @param content 需加密内容
	 * @param charSet 字符集编码格式
	 * @return
	 */
	public static String encryptByPublicKey(String content, CharSet charSet) {
		byte[] b = null;
		try {
			b = encrypt(content.getBytes(charSet.getName()), true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("不支持的编码格式，错误信息" + e.getMessage());
		}
		return bytesToString(b);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @return
	 */
	private static byte[] encrypt(byte[] data, boolean isPublicKey) {
		try {
			return RSAUtils.encryptByPublicKey(data, publicKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("加密失败，错误信息" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字节数组转换为字符串
	 * 
	 * @param decrytString
	 * @return
	 */
	private static String bytesToString(byte[] encrytpByte) {
		char[] charHex = Hex.encodeHex(encrytpByte);
		return new String(charHex);
	}

	public static void main(String[] args) throws Exception {

		/**
		 * RSAUtilsLieTouToPuHui.createRSAKey();
		 */

		/* 测试信息 */
		/*
		 * String kk= RSACommonUtils.encryptByPrivateKey("18822119676",
		 * RSACommonUtils.CharSet.UTF8) ; System.out.println(kk );
		 * System.out.println(RSACommonUtils.decryptByPublicKey(kk,
		 * RSACommonUtils.CharSet.UTF8));
		 */

		/*
		 * byte []data=new byte[]{101}; final char[] toDigits = {'0', '1', '2',
		 * '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};;
		 * final int l = data.length; final char[] out = new char[l << 1]; //
		 * two characters form the hex value. for (int i = 0, j = 0; i < l; i++)
		 * { System.out.println(0xF0 & data[i]); System.out.println((0xF0 &
		 * data[i]) >>> 4); out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
		 * 
		 * System.out.println(0x0F & data[i]); out[j++] = toDigits[0x0F &
		 * data[i]]; }
		 * 
		 * System.out.println("----");
		 */

	}

}
