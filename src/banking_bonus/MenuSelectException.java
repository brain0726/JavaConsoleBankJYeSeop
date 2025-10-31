package banking_bonus;

//개발자가 직접 정의한 예외처리 클래스
public class MenuSelectException extends Exception{
	public MenuSelectException() {
		System.out.println("메뉴 입력 예외발생됨.");;
	}
}
