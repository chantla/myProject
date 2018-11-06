package test.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/*
 *  Data Base Connection Pool Bean
 *  
 *  - DataBase 에 access 해서 작업하려면 Connection 객체가 필요하다.
 *  - Connection 객체를 connection pool 에 미리 만들어 놓고 필요할때 
 *    사용하고 반납하는 구조로 사용해야한다. 
 *  - DbcpBean 객체에서 리턴해주는 Connection 객체를 사용하고 .close() 
 *    메소드를 호출하면 자동으로 반납된다. 
 *    
 *    1. Servers/context.xml 설정
 *    
 *    <Resource name="jdbc/myoracle" auth="Container"
              type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
              url="jdbc:oracle:thin:@127.0.0.1:1521:orcl"
              username="scott" password="tiger" maxTotal="20" maxIdle="10"
              maxWaitMillis="-1"/>
      
      2. WEB-INF/web.xml 설정
      
      <resource-ref>
		 <description>Oracle Datasource example</description>
		 <res-ref-name>jdbc/myoracle</res-ref-name>
		 <res-type>javax.sql.DataSource</res-type>
		 <res-auth>Container</res-auth>
	  </resource-ref>
	  
	  3. DbcpBean 클래스 아래와 같이 작성
	  
	  4. Dao 에서 
	  
	  Connection conn=new DbcpBean().getConn(); 
	  
	  과 같은 형식으로 Connection 객체의 참조값 얻어내기 
 */
public class DbcpBean {
	//필드
	private Connection conn;
	//생성자
	public DbcpBean() {	
		Context initContext;
		try {
			//Connection 객체의 참조값을 얻어와서 필드에 저장하는 작업
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			//Connection 객체의 참조값 얻어오기
			conn = ds.getConnection();
			System.out.println("Connection 얻어오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Connection 객체를 리턴해주는 메소드
	public Connection getConn() {
		return conn;
	}
}
