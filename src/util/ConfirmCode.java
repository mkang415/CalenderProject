package util;

import java.util.Random;
import java.util.UUID;

public class ConfirmCode {

	public String confirmCode() {
		
		UUID uuid = UUID.randomUUID();
		
		String confirmcode = uuid.toString().split("-")[1]; // randon 4자리 uuid
		
//		System.out.println(confirmcode); // test code
		
		return confirmcode;
		
	}
	
	public int randomCode() {
		
		Random random = new Random();
		int code = 0;
		
		while(code<1000) {
			
			code = random.nextInt(9999);
		
		}
		
		return code;
		
	}
	
}
