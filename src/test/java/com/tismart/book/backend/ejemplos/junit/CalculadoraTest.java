package com.tismart.book.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Calculadora calc;
	
	@BeforeAll
	public static void primero() {
		System.out.println("Debe ejecutarse primero");
	}

	@BeforeEach						//Antes de cada prueba unitaria
	public void instanciaObjeto() {
		calc = new Calculadora();
		System.out.println("@BeforeEach; instanciando");
	}
	
	@Test
	@DisplayName("prueba que ocupa AssertEquals")
	public void calculadoraAssertEqualTest() {
		assertEquals(2, calc.sumar(1, 1));
		assertEquals(5, calc.restar(6, 1));
		assertEquals(9, calc.multiplicar(3, 3));
		assertEquals(6, calc.dividir(18, 3));
	}
	
	@Test
	@Disabled("esta pueba no se ejecutará")
	public void calculadoraAssertTrueFalse() {
		assertTrue(calc.sumar(2, 2)==4);
		assertTrue(calc.restar(8, 5)==3);
		assertFalse(calc.multiplicar(3, 3)==8);
		assertFalse(calc.dividir(4, 2)==1);
	}
	
	@AfterEach						//Despues de cada prueba unitaria
	public void despuesTest() {
		System.out.println("@AfterEach; terminando Test");
	}
	
	@AfterAll
	public static void ultimo() {
		System.out.println("Debe ejecutarse al final");
	}
	
}
