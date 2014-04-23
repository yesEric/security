package demo.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import demo.tool.DigestService;


public class DataSourcePopulator {
	private JdbcTemplate template;
	private TransactionTemplate transactionManager;
	private static final DigestService digestService = new DigestService();
	
	public DataSourcePopulator() {
		
	}
	
	public void init() throws Exception {
		Assert.notNull(template, "dataSource required");
		Assert.notNull(transactionManager, "platformTransactionManager required");

		digestService.setAlgorithm("sha-256");
		
		template.execute("drop table if exists user");
		template.execute("drop table if exists role");
		template.execute("drop table if exists resource");
		template.execute("drop table if exists user_role");
		template.execute("drop table if exists role_resource");
		
		template.execute("create table user(id integer not null primary key,username varchar(50) not null, password varchar(100) not null, enable boolean not null);");
		template.execute("create table role(id integer not null primary key,rolename varchar(50) not null);");
		template.execute("create table resource(id integer not null primary key,url varchar(500) not null, memo varchar(50));");
		template.execute("create table user_role(user_id integer not null, role_id integer not null, CONSTRAINT PK_UseRole PRIMARY KEY (user_id,role_id));");
		template.execute("create table role_resource(role_id integer not null, resource_id integer not null, constraint pk_roleresource primary key (role_id,resource_id));");
		
		//Usually use this user(administrator) for initialize system.
		template.execute("insert into user values(1,'administrator','" + digestService.digestMessage("111".getBytes()) + "', true);");
		template.execute("insert into user values(2,'scott','" + digestService.digestMessage("222".getBytes()) + "',true);");
		template.execute("insert into user values(3,'peter','" + digestService.digestMessage("333".getBytes()) + "',true);");
		template.execute("insert into user values(4,'rod','" + digestService.digestMessage("444".getBytes()) + "',false);");
		
		template.execute("insert into role values(0,'ROLE_SUPERMAN');");//Only for administrator.
		template.execute("insert into role values(1,'ROLE_ADMIN');");
		template.execute("insert into role values(2,'ROLE_USER');");
		
		template.execute("insert into resource values(1, '/admin.htm', '管理员操作页面');");
		template.execute("insert into resource values(2, '/user.htm','用户操作页面');");
		
		template.execute("insert into user_role values(1,0);");//Only for administrator.Only roled user can login.
		template.execute("insert into user_role values(2,1);");
		template.execute("insert into user_role values(3,2);");
		template.execute("insert into user_role values(4,2);");

		template.execute("insert into role_resource values(1,1);");
		template.execute("insert into role_resource values(1,2);");
		template.execute("insert into role_resource values(2,2);");
		
		//List<String> ls = template.queryForList("select distinct re.url from resource re;", String.class);
		
		//System.out.println(ls.size());//result should be 2.
	}

	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             

	public void setTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.transactionManager = new TransactionTemplate(platformTransactionManager);
	}

}