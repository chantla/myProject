package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

/*
 * 	[ Data Access Object ]
 * 								< 		Single Ton(static)	   >
 * 	- Web Application 을 만들때 Dao 객체는 하나만 생성하는 구조로 만들어야한다.
 * 	- DB 에 SELECT, INSERT, UPDATE, DELETE 작업을 할때 사용되는 객체이다.
 */
public class MemberDao {
	private static MemberDao dao;
	private MemberDao() {}
	public static MemberDao getInstance() {
		if(dao==null) {
			dao=new MemberDao();
		}
		return dao;
	}
	//인자로 전달되는 아이디를 사용가능한지 여부를 리턴해주는 메소드
	public boolean canUseId(String inputId) {
		//사용 가능한지 여부를 담을 지역변수 만들고 초기값 true 부여
		boolean canUse=true;
		//필요한 객체를 담을 지역변수 미리 만들기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Connection pool 에서 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			// 실행할 sql 문 준비 
			String sql = "SELECT id FROM member1"
					+ " WHERE id=?";
			//PreparedStatement 객체의 참조값 얻어오기 
			pstmt = conn.prepareStatement(sql);
			// ? 에 필요한 값 바인딩
			pstmt.setString(1, inputId);
			// sql(쿼리) 문을 수행하고 ResultSet 객체를 리턴 받는다.
			rs = pstmt.executeQuery();
			//반복문 돌면서 
			if(rs.next()) {//커서를 한칸씩 내리면서
				//가입 불가한 아이디 이다.
				canUse=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		//지역 변수에 있는 값을 리턴해준다.
		return canUse;
	}
	//구글 로그인 저장
	public boolean goologin(String gooid, String gooname, String gooemail) {
		int check=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int flag = 0;
		try {
			conn=new DbcpBean().getConn();
			String sql="SELECT *"
					+ " FROM member1"
					+ " WHERE gooid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, gooid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				check=1;
			}else {
				if (pstmt != null)pstmt.close();
				if (conn != null)conn.close();
				conn = new DbcpBean().getConn();
				String sql2 = "INSERT INTO"
						+ " member1(num,regdate,name,email,gooid)"
						+ " VALUES(member1_seq.NEXTVAL,SYSDATE,?,?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gooname);
				pstmt.setString(2, gooemail);
				pstmt.setString(3, gooid);
				flag = pstmt.executeUpdate();
				if(flag>0)check=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (check > 0) {
			return true;
		} else {
			return false;
		}
	}
	//카카오 로그인 저장 메소드
	public boolean kalogin(String kaid) {
		int check=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int flag = 0;
		try {
			conn=new DbcpBean().getConn();
			String sql="SELECT *"
					+ " FROM member1"
					+ " WHERE kaid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, kaid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				check=1;
			}else {
				if (pstmt != null)pstmt.close();
				if (conn != null)conn.close();
				conn = new DbcpBean().getConn();
				String sql2 = "INSERT INTO"
						+ " member1(num,regdate,kaid)"
						+ " VALUES(member1_seq.NEXTVAL,SYSDATE,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, kaid);
				flag = pstmt.executeUpdate();
				if(flag>0)check=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (check > 0) {
			return true;
		} else {
			return false;
		}
	}
	//로그인 메소드
		public int login(String email, String pwd) {
			int check=0;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=new DbcpBean().getConn();
				String sql="SELECT email,name,pwd"
						+ " FROM member1"
						+ " WHERE email=? and pwd=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, pwd);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					check=1;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (Exception e) {}
			}
			return check;
		}
	//회원 정보를 추가하는 메소드
	public boolean insert(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		//작업 성공 혹은 실패를 확인할 지역변수 선언하고 초기값 0 부여
		int flag=0;
		try {
			conn=new DbcpBean().getConn();
			String sql="INSERT INTO member1"
					+ " (num,email,name,pwd,regdate)"
					+ " VALUES(member1_seq.NEXTVAL,?,?,?,SYSDATE)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, 	dto.getEmail());
			pstmt.setString(2,  dto.getName());
			pstmt.setString(3,  dto.getPwd());
			flag=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) {
			return true;	
		}else {
			return false;	
		}
	}
	//회원 정보를 삭제하는 메소드
	public boolean delete(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DbcpBean().getConn();
			String sql="DELETE FROM member1"
					+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			flag=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) {
			return true;	
		}else {
			return false;	
		}
	}
	//회원 정보를 수정하는 메소드
	public boolean update(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DbcpBean().getConn();
			String sql="UPDATE member1"
					+ " SET email=?,name=?,pwd=?"
					+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,  dto.getEmail());
			pstmt.setString(2,  dto.getName());
			pstmt.setString(3,  dto.getPwd());
			pstmt.setInt(4, dto.getNum());
			flag=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) {
			return true;	
		}else {
			return false;	
		}
	}
	//회원 한명의 정보를 리턴하는 메소드
	public MemberDto getData(int num) {
		//MemberDto 객체의 참조값을 담을 지역 변수 만들기
		MemberDto dto=null;
		// 필요한 객체를 담을 지역변수 만들기
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//Connection 객체의 참조값 얻어오기
			conn=new DbcpBean().getConn();
			//실행할 sql 문 준비
			String sql="SELECT email,name,pwd,"
					+ " TO_CHAR(regdate, 'YYYY.MM.DD AM HH:MI') regdate"
					+ " FROM member1"
					+ " WHERE num=?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt=conn.prepareStatement(sql);
			//sql(query) 문 수행하고 결과를 ResultSet 으로 리턴 받기
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new MemberDto();
				// cursor 가 위치한 곳의 정보를 MemberDto 객체에 담는다.
				dto.setNum(num);
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPwd(rs.getString("pwd"));
				dto.setRegdate(rs.getString("regdate"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {}
		}
		return dto;
	}
	//회원 목록을 리턴하는 메소드
		public List<MemberDto> getList(){
			List<MemberDto> list=new ArrayList<>();
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=new DbcpBean().getConn();
				String sql="SELECT num,email,name,pwd,"
						+ " TO_CHAR(regdate, 'YYYY.MM.DD HH24:MI') regdate,"
						+ " FROM member1"
						+ " ORDER BY num DESC";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					MemberDto dto=new MemberDto();
					dto.setNum(rs.getInt("num"));
					dto.setEmail(rs.getString("email"));
					dto.setName(rs.getString("name"));
					dto.setPwd(rs.getString("pwd"));
					list.add(dto);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (Exception e) {}
			}
			return list;
		}
}
