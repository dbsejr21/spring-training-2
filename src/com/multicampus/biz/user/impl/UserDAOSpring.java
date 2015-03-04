package com.multicampus.biz.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.user.UserVO;

// DAO(Data Access Object)
@Repository
public class UserDAOSpring {
	
	@Autowired
	JdbcTemplate spring;
	
	// SQL 명령어들
	private final String USER_GET = "select * from users where id=? and password=?";
	
	public UserVO getUser(UserVO vo){
		
		Object[] args = {vo.getId(), vo.getPassword()};
		
		return spring.queryForObject(USER_GET, args, new UserRowMapper());
		
	}
	
	class UserRowMapper implements RowMapper<UserVO> {
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO user = new UserVO();
			user = new UserVO();
			user.setId(rs.getString("ID"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setName(rs.getString("NAME"));
			user.setRole(rs.getString("ROLE"));
			
			return user;
		}
	}
	
}
