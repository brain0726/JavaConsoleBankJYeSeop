package banking.jdbc;


import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
/*
인터페이스를 구현한 클래스로 extends 대신 implements를 사용한다.
또한 용어도 '상속'이 아닌 '구현'으로 표현한다. */
public class MyConnection implements IConnect {

	public Connection con; //DB연결
	public ResultSet rs; //select의 실행결과 반환
	public Statement stmt; //정적 쿼리문 실행
	public PreparedStatement psmt; //동적 쿼리문 실행
	public CallableStatement csmt; // 프로시저 실행
	
	//생성자 : 매개변수로 오라클 계정아이디, 비번을 전달
	public MyConnection(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			con = DriverManager.getConnection(ORACLE_URL,user,pass);
		}
		catch(Exception e) {
			System.out.println("DM 커넥션 예외발생");
			e.printStackTrace();
			}
	}
	
	/*
	IConnect 인터페이스를 구현했으므로, 자식 클래스에서는 반드시 부모의 
	추상메서드를 오버라이딩해서 재정의 해야한다.
	그렇지 않으면 에러가 발생한다.
	 */
	@Override
	public void dbExecute() {
		/*
		CURD(데이터베이스의 기본 4가지 작업)는 자식 클래스에서 처리되어야 하므로
		부모클래스에서는 실행부를 정의할 수 없다.
		따라서 실행부가 없는 메서드가 정의한다.	 */
	}
	
	//JDBC작업을 위해 연결된 모든 자원을 해제
	@Override
	public void dbClose() {
		try {
			//연결되어 사용중이라면 자원을 반납한다.
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(csmt!=null) csmt.close();
			System.err.println("DB 자원 반납");
		}
		catch(Exception e) {
			System.out.println("DB 자원 반납시 예외발생");
			e.printStackTrace();
		}
	}
	
	//사용자로부터 입력을 받기 위해 정의
	@Override
	public String inputValue(String title) {
		Scanner scan = new Scanner(System.in);
		System.out.print(title +"을(를) 입력(exit->종료):");
		//문자열 입력받음
		String inputStr = scan.nextLine();
		/*equalsIgnoreCase() : equal()와 동일하게 인스턴스가 동일한지
		비교하는 메서드로 대소문자로 구분하지 않고 비교한다.
		즉 EXIT와 exit를 같은 문자열로 판단한다. */
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			//자원반납
			dbClose();
			//프로그램 자체를 종료시킨다.
			System.exit(0);
		}
		//종료가 아니라면 입력받은 값을 반환한다.
		return inputStr;
	}
}
