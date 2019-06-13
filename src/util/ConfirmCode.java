package util;

import java.util.Random;

// 이메일 인증번호 생성

public class ConfirmCode {
	
	public int randomCode() {
		
		Random random = new Random();
		int code = 0;
		
		while(code<1000) {
			
			code = random.nextInt(9999);
		
		}
		
		return code;
		
	}
	
}
