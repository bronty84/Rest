/**
 * 
 */
package com.java.restservices.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.java.restservices.dao.UserDAO;
import com.java.restservices.model.User;
import com.java.restservices.utility.Utitlity;

/**
 * @author an.delia
 *
 */

@Path("/login")
public class Login {

	// HTTP Get Method
	@GET 
	// Path: http://localhost/<appln-folder-name>/login/dologin
	@Path("/dologin")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON) 
	// Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
	public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
		String response = "";
		if(checkCredentials(uname, pwd)){
			response = Utitlity.constructJSON("login",true);
		}else{
			response = Utitlity.constructJSON("login", false, "Incorrect Username or Password");
		}
		return response;		
	}
	
	/**
	 * Method to check whether the entered credential is valid
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	private boolean checkCredentials(String uname, String pwd){
		System.out.println("Inside checkCredentials");
		boolean result = false;
		if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)){
			try {
				User user = new User();
				user.setUsername(uname);
				user.setPassword(pwd);
				User login = new UserDAO().checkLogin(user);
				result = login!=null ? true : false;
				System.out.println("Inside checkCredentials try "+result);
			} catch (Exception e) {
				System.out.println("Inside checkCredentials catch");
				result = false;
			}
		}else{
			System.out.println("Inside checkCredentials else");
			result = false;
		}
		return result;
	}
	
}
