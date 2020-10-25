package cn.edu.cqu.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class User {
	String username;
	String password;
	public User(String u, String p){
		username = u;
		password = p;
	}
	public String getUsername() {
		return username;
	}

	public static boolean checkUser(String u, String p) throws IOException {
		File txt = new File("E:/javaee/sso/src/main/java/cn/edu/cqu/model/user.txt");
		if (!txt.exists()) txt.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader(txt));
		String line = null;
		String read_u = null, read_p = null;
		while ((line = br.readLine()) != null) {
			// 奇数行，为用户名
			read_u = line;
			read_p = br.readLine();
//			System.out.println(read_u + " " + read_p);
			if (read_u.equals(u) && read_p.equals(p)) {
				br.close();
				return true;
			}
		}
		br.close();
		return false;
	}
}
