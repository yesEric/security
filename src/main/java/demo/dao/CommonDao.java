package demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CommonDao extends JdbcDaoSupport {
	public List<String> getRoleByResourceId(Integer resourceId) {
		return getJdbcTemplate().queryForList("select distinct r.rolename " +
				"from role r inner join role_resource rr on r.id = rr.role_id where rr.resource_id=?",
				new Integer[]{resourceId}, String.class);
	}
	
	public List<Map<String, Object>> getAllResource() {
		return getJdbcTemplate().queryForList("select * from resource;");
	}
	
	public List<String> getResourceByUserName(String userName) {
		if("administrator".equals(userName)) {
			return getJdbcTemplate().queryForList("select distinct re.url from resource re;", String.class);
		} 
		
		return getJdbcTemplate().queryForList("select distinct re.url " +
				"from user u, role ro, resource re, user_role ur, role_resource rr " +
				"where u.id = ur.user_id and ro.id = ur.role_id " +
				"and ro.id = rr.role_id and re.id = rr.resource_id " +
				"and u.enable = true and u.username = ?;", new String[]{userName}, String.class);
	}
}
