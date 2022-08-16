package com.xender.practical.modal;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.xender.practical.model.UserData;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDatatests {
	
	@Test
	public void testUser() {
		UserData user = new UserData(101,"Sridhar","Sridh23@gmail.com","Sridhar@100","Asddf@121","9398493484");
		
		String str = user.toString();
		assertNotNull(str);
	}
	
}
