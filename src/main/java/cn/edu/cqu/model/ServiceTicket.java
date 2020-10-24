package cn.edu.cqu.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ServiceTicket {
	public static String getServiceTicket() throws IOException {
		File txt = new File("E:/javaee/sso/src/main/java/cn/edu/cqu/model/st.txt");
		BufferedReader br = new BufferedReader(new FileReader(txt));
		String s = null;
		s = br.readLine();// 只读一行
		br.close();
		return s;
	}
	
	// 生成Service Ticket的同时也要存入st.txt
	public static String generateST() throws IOException {
		StringBuffer res = new StringBuffer();
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		for (int i = 0; i < 32; i++) {
			int number=random.nextInt(62);
			res.append(str.charAt(number));
		}
		File txt = new File("E:/javaee/sso/src/main/java/cn/edu/cqu/model/st.txt");
		if (!txt.exists()) txt.createNewFile();
		FileWriter fileWriter = new FileWriter("E:/javaee/sso/src/main/java/cn/edu/cqu/model/st.txt",false);
		fileWriter.write(res.toString());
		fileWriter.close();
		return res.toString();
	}
}
