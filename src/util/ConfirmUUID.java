package util;

import java.util.UUID;

public class ConfirmUUID {

	public String confirmCode() {
		
		UUID uuid = UUID.randomUUID();
		
		String confirmcode = uuid.toString().split("-")[1]; // randon 4자리 uuid
		
//		System.out.println(confirmcode); // test code
		
		return confirmcode;
		
	}
	
}
