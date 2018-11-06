package test.member.dto;
/*
 * 		회원 한명의 정보를 담을 Data Transfer Object 클래스 정의하기
 */
public class MemberDto {
	//1. 필드의 접근 지정자를 private 로 정의한다.
	//2. default 생성자를 정의한다.
	//3. 생성자의 인자로 필드값을 모두 전달받을수 있는 생성자 정의
	//4. 각각의 필드에 대한 setter, getter 메소드를 정의한
	private int num;
	private String email;
	private String name;
	private String pwd;
	private String regdate;
	
	public MemberDto() {}

	public MemberDto(int num, String email, String name, String pwd, String regdate) {
		super();
		this.num = num;
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
	
}
























