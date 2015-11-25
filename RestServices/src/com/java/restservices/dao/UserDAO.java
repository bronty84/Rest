/**
 * 
 */
package com.java.restservices.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.java.restservices.connection.MyBatisDAOUtil;
import com.java.restservices.mapper.UserMapper;
import com.java.restservices.model.User;

/**
 * @author an.delia
 *
 */
public class UserDAO {

	public void insertUser(User user) throws SQLException {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertUser(user);
			sqlSession.commit();
		} catch (SQLException e){
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	public User getUserById(Integer userId) {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getUserById(userId);
		} finally {
			sqlSession.close();
		}
	}

	public List<User> getAllUsers() {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		List<User> userList = null;
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userList = userMapper.getAllUsers();
			return userList;

		} finally {
			sqlSession.close();
		}
	}

	public void updateUser(User user) {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.updateUser(user);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	public void deleteUser(Integer userId) {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.deleteUser(userId);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}
	
	public User checkLogin(User user) {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User result = userMapper.checkLogin(user);
			return result!=null ? result : null;
		} catch(Exception e){
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
}
