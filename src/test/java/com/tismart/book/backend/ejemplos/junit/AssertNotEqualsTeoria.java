package com.tismart.book.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEqualsTeoria {

	@Test
	public void miTest() {
		assertNotEquals(2, 3); 		//VERDE
		//assertNotEquals(2, 2); 	//ROJO
	}
}
