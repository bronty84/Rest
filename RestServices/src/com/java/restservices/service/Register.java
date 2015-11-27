/**
 * 
 */
package com.java.restservices.service;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.exceptions.PersistenceException;

import com.java.restservices.dao.UserDAO;
import com.java.restservices.model.User;
import com.java.restservices.utility.Utility;

/**
 * @author an.delia
 *
 */

//Path: http://localhost/RestServices/register
@Path("/register")
public class Register {

	@POST 
	@Path("doregister")  
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String doRegister(@FormParam("idLanguage") Long idLanguage, 
							@FormParam("gmcToken") String gmcToken, 
							@FormParam("email") String email,
							@FormParam("username") String username,
							@FormParam("password") String password,
							@FormParam("telephone") Long telephone){
		String response = "";
		try{
			System.out.println("Inside doLogin "+username+"  "+password);
			User user = new User();
			user.setIdLanguage(new Long(idLanguage));
			user.setGcmToken(gmcToken);
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(Utility.encryptPassword(password));
			user.setTelephone(new Long(telephone));
			user.setDateRegistration(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			int retCode = registerUser(user);
			if(retCode == 0){
				response = Utility.constructJSON("register", true,retCode);
			}else if(retCode == 1){
				response = Utility.constructJSON("register", false, retCode, "Username already registered");
			}else if(retCode == 2){
				response = Utility.constructJSON("register", false, retCode, "Email already registered");
			}else if(retCode == 3){
				response = Utility.constructJSON("register", false, retCode, "Telephone already registered");
			}else if(retCode == 4){
				response = Utility.constructJSON("register", false, retCode, "Special Characters are not allowed in Username and Password");
			}else if(retCode == 5){
				response = Utility.constructJSON("register", false, retCode, "Error occured");
			}
			return response;
		} catch (Exception e){
			System.out.println("Exception - dologin: "+e);
			response = Utility.constructJSON("register",false, 6, "Error occured", e.toString());
			return response;
		}
	}
		
	private int registerUser(User user) throws Exception{
		System.out.println("Inside checkCredentials");
		int result = 5;
		if(Utility.isNotNull(user.getUsername()) && Utility.isNotNull(user.getPassword())){
			try {
				UserDAO userDAO = new UserDAO();
				userDAO.insertUser(user);
				result = 0;
			} catch(PersistenceException pe){
				System.out.println("RegisterUser catch PersistenceException: "+pe);
				if(pe.getMessage().contains("username_UNIQUE"))
					result = 1;
				else if((pe.getMessage().contains("email_UNIQUE")))
					result = 2;
				else if((pe.getMessage().contains("telephone_UNIQUE")))
					result = 3;
				else 
					throw pe;
			} catch (Exception e) {
				System.out.println("Inside checkCredentials catch Exception ");
				throw e;
			}
		}else{
			System.out.println("Inside checkCredentials else");
			result = 5;
		}
		return result;	
	}
}
