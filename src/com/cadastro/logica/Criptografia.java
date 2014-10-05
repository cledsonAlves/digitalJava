package com.cadastro.logica;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Cledson
 * Criptografa  usando algoritmo hash (MD5)
 *
 */

public class Criptografia {
	
	   public static String criptografa(String password) {
	        MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				 BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
				 return String.format("%32x", hash);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	       
	        return "";
	    }
	   
}
