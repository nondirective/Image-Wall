package com.nondirectional.imagewall.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class CheckCode {
	//��֤���ַ�������
	private static int CODE_SIZE = 5;
	private static int IMAGE_WIDTH = 105;
	private static int IMAGE_HEIGHT = 30;
	
	//�����֤��ͼƬ�����
	/**
	 * 	�ڷ���ֵ��map�У�code:��֤���ַ�����image:��֤��ͼƬ����
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> getCheckCodeImage(){
		String code = getCode();
		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = image.createGraphics();
		g.setFont(new Font("sans-serif", Font.PLAIN, 30));
		g.drawString(code, 5, 25);
		g.dispose();
		image.flush();
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("image", image);
		map.put("code", code);
		return map;
	}
	
	
	//�����֤���ַ���
	public static String getCode() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<CODE_SIZE;i++) {
			switch((int)(Math.random()*3)) {
			case 0:
				sb.append((char)(Math.random()*10+48));
				break;
			case 1:
				sb.append((char)(Math.random()*26+65));
				break;
			case 2:
				sb.append((char)(Math.random()*26+97));
				break;
			default:
				i--;
			}
		}
		
		return sb.toString();
	}
	
	
}
