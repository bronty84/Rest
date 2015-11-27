package com.java.restservices.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utility {
	/**
	 * Null check Method
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNotNull(String txt) {
		// System.out.println("Inside isNotNull");
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}

	/**
	 * Method to construct JSON
	 * 
	 * @param tag
	 * @param status
	 * @param code
	 * @return
	 */
	public static String constructJSON(String tag, boolean status, int code) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("code", code);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	/**
	 * Method to construct JSON with Error Msg
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @param code
	 * @return
	 */
	public static String constructJSON(String tag, boolean status, int code, String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("code", code);
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString(); 
	}
	
	/**
	 * Method to construct JSON with Error Msg + Exception message
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @param code
	 * @param exception
	 * @return
	 */
	public static String constructJSON(String tag, boolean status, int code, String err_msg, String exception) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("code", code);
			obj.put("error_msg", err_msg);
			obj.put("exception", exception);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString(); 
	}
	
	public static String encryptPassword(String password){
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}
	
	private static String byteToHex(final byte[] hash){
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	
}
