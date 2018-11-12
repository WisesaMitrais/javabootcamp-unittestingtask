package com.mitrais.booklibrarymanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooklibrarymanagementApplicationTests {

	@Test
	public void testSetup(){
		String str = "Done with JUnit";
		assertEquals("Done with JUnit", str);
	}
}
