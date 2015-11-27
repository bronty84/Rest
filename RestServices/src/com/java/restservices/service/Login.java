/**
 * 
 */
package com.java.restservices.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.java.restservices.dao.UserDAO;
import com.java.restservices.model.User;
import com.java.restservices.utility.Utility;

/**
 * @author an.delia
 *
 */

@Path("/login")
public class Login {

	@POST
	@Path("dologin")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
	public String doLogin(@FormParam("username") String uname, @FormParam("password") String pwd){
		String response = "";
		if(checkCredentials(uname, pwd)){
			response = Utility.constructJSON("login", true, 1);
		}else{
			response = Utility.constructJSON("login", false, 2, "Incorrect Username or Password");
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
		if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
			try {
				User user = new User();
				user.setUsername(uname);
				user.setPassword(Utility.encryptPassword(pwd));
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
