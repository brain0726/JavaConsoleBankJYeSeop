package banking.jdbc;

import java.sql.SQLException;
import java.util.Scanner;

import banking.jdbc.MyConnection;

//컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
public class AccountManager extends MyConnection {
	Scanner scanner = new Scanner(System.in);
	//멤버변수
	public AccountManager(String user, String pass) {
		//부모클래스의 생성자를 호출하여 DB연결을 처리한다.
		super(user,pass);
	}
	
	//멤버변수
	String query; //쿼리문 작성
	int result; //쿼리 실행 후 결과값
	
	String number,name;
	int money;
		
	boolean first=true;
	//insert 쿼리문 실행을 위한 메서드
	@Override
	public void dbExecute() {
		try {
			/*
			Statement 인터페이스
			: 정적 쿼리문을 실행할때 사용. 정적 쿼리문이란 인파라미터가 없는 완성된 SQL을 말한다.
			수정은 개발자가 직접 소스코드에서 진행해야한다.
			 */
			stmt = con.createStatement();
			//쿼리문 작성
			//select seq_serial_num.currval from dual;
			if (first) {
				stmt.executeUpdate("DELETE FROM banking");
				stmt.executeUpdate("ALTER SEQUENCE SEQ_ACCOUNT_ID_NUM RESTART START WITH 1");
				System.out.println("1");
				first=false;
			}
			query = "INSERT INTO banking VALUES  "
					+ " ('0', '000-000','테스터1',0,0)";
			result = stmt.executeUpdate(query);
			System.out.println("[stmt]"+result +"행 입력됨");
			///////////////////////////////////////////////
			//인파라미터가 있는 동적쿼리문 작성
			query = "INSERT INTO banking VALUES "
					+ " (seq_account_id_num.NEXTVAL, ?, ?, ?, ?)";
			/*
			PreparedStatement 인터페이스
			: 인파라미터가 있는 동적쿼리문을 실행할때 사용. 인파라미터는 ?로 표시하고,
			인스턴스 생성 이후 setXX() 메서드를 통해 설정한다.
			인덱스는 1부터 시작한다.	 */
			//쿼리문을 인수로 인스턴스를 먼저 생성한다.
			psmt = con.prepareStatement(query);
			//쿼리문의 인파라미터를 입력값을 통해 설정한다.
			psmt.setString(1, inputValue("계좌번호 : "));
			psmt.setString(2, inputValue("고객이름 : "));
			psmt.setString(3, inputValue("잔고 : "));
			psmt.setString(4, inputValue("이율 : "));
			//쿼리문을 실행하고 결과를 반환받는다.
			result = psmt.executeUpdate();
			System.out.println("[psmt]"+result +"행 입력됨");
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 중 예외발생");
			e.printStackTrace();
		}
	}
	//생성자
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		new AccountManager("education", "1234").dbExecute();
		
		System.out.println("계좌계설이 완료되었습니다.");
	}
	public void depositMoney() {
		String number;
		int money;
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:"); number=scanner.nextLine();
//		for(int i=0;i<count;i++) {
//			if(number.equals(account[i].number)) {
//				System.out.print("입금액:");money=scanner.nextInt();
//				account[i].addMoney(money);
//				System.out.println("입금이 완료되었습니다.");
//			}
//		}
	}
	public void withdrawMoney() {
		String number;
		int money;
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:"); number=scanner.nextLine();
//		for(int i=0;i<count;i++) {
//			if(number.equals(account[i].number)) {
//				System.out.print("출금액:");money=scanner.nextInt();
//				account[i].subMoney(money);
//				System.out.println("출금이 완료되었습니다.");
//			}
//		}
	}
	public void showAll() {
		System.out.println("***계좌정보출력***");
//		for(int i=0;i<count;i++) {
//			account[i].showAccInfo();
//		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
}
