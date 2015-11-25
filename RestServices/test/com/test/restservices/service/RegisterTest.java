/**
 * 
 */
package com.test.restservices.service;

import org.junit.Test;

import com.java.restservices.service.Register;

/**
 * @author an.delia
 *
 */
public class RegisterTest {

	/**
	 * @param args
	 */
	@Test
	public static void main(String[] args) {
		Register register = new Register();
		String result = register.doLogin(new Long(3), "3ed32sx2", "massimo.massimo@email.com", "massimo", "password", new Long(6543212));
		System.out.println("result: "+result);
	}

}
