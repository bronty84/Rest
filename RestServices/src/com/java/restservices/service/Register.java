/**
 * 
 */
package com.java.restservices.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.exceptions.PersistenceException;

import com.java.restservices.dao.UserDAO;
import com.java.restservices.model.User;
import com.java.restservices.utility.Utitlity;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author an.delia
 *
 */

//Path: http://localhost/RestServices/register
@Path("/register")
public class Register {

	// HTTP Get Method
	@POST 
	// Path: http://localhost/RestServices/register/doregister
	@Path("/doregister")  
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters: http://localhost/RestServices/register/doregister?name=pqrs&username=abc&password=xyz
	//public String doLogin(@QueryParam("name") String name, @QueryParam("username") String uname, @QueryParam("password") String pwd){
	public String doLogin(@QueryParam("idLanguage") Long idLanguage, 
						  @QueryParam("gmcToken") String gmcToken, 
						  @QueryParam("email") String email,
						  @QueryParam("username") String username,
						  @QueryParam("password") String password,
						  @QueryParam("telephone") Long telephone){
		String response = "";
		try{
			System.out.println("Inside doLogin "+username+"  "+password);
			User user = new User();
			user.setIdLanguage(new Long(idLanguage));
			user.setGcmToken(gmcToken);
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			user.setTelephone(new Long(telephone));
			user.setDateRegistration(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			int retCode = registerUser(user);
			if(retCode == 0){
				response = Utitlity.constructJSON("register",true);
			}else if(retCode == 1){
				response = Utitlity.constructJSON("register",false, "You are already registered");
			}else if(retCode == 2){
				response = Utitlity.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
			}else if(retCode == 3){
				response = Utitlity.constructJSON("register",false, "Error occured");
			}
			return response;
		} catch (Exception e){
			System.out.println("Exception - dologin: "+e);
			response = Utitlity.constructJSON("register",false, "Error occured", e.toString());
			return response;
		}
	}
		
//	public String uniqueRegister(){
//		
//	}
	
	private int registerUser(User user){
		System.out.println("Inside checkCredentials");
		int result = 3;
		if(Utitlity.isNotNull(user.getUsername()) && Utitlity.isNotNull(user.getPassword())){
			try {
				UserDAO userDAO = new UserDAO();
				userDAO.insertUser(user);
				System.out.println("RegisterUser if");
				result = 0;
			} catch(SQLException sqle){
				System.out.println("RegisterUser catch sqle");
				//When Primary key violation occurs that means user is already registered
				if(sqle.getErrorCode() == 1062){
					result = 1;
				} 
				//When special characters are used in name,username or password
				else if(sqle.getErrorCode() == 1064){
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			}
			catch (Exception e) {
				System.out.println("Inside checkCredentials catch e ");
				throw e;
			}
		}else{
			System.out.println("Inside checkCredentials else");
			result = 3;
		}
		return result;	
	}
}
