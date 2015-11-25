/**
 * 
 */
package com.java.restservices.mapper;

import java.sql.SQLException;
import java.util.List;

import com.java.restservices.model.User;

/**
 * @author an.delia
 *
 */
public interface UserMapper {

	public User getUserById(Integer idUser);
	public List<User> getAllUsers();
	public void insertUser(User user) throws SQLException;
	public void updateUser(User user);
	public void deleteUser(Integer idUser);
	public User checkLogin(User user);
}
