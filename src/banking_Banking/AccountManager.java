package banking_Banking;

import java.util.Scanner;

//컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
public class AccountManager {
	Scanner scanner = new Scanner(System.in);
	//멤버변수
	private Account[] account;
	private int count;
	
	//생성자
	public AccountManager(int num) {
		account = new Account[num];
		count = 0;
	}
	public void makeAccount() {
		String number,name;
		int money;
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호 : "); number = scanner.nextLine();
		System.out.print("고객이름 : "); name = scanner.nextLine();
		System.out.print("잔고 : "); money = scanner.nextInt();
		Account new_acc= new Account(number,name,money);
		account[count++]=new_acc;
		System.out.println("계좌계설이 완료되었습니다.");
	}
	public void depositMoney() {
		String number;
		int money;
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:"); number=scanner.nextLine();
		for(int i=0;i<count;i++) {
			if(number.equals(account[i].number)) {
				System.out.print("입금액:");money=scanner.nextInt();
				account[i].addMoney(money);
				System.out.println("입금이 완료되었습니다.");
			}
		}
	}
	public void withdrawMoney() {
		String number;
		int money;
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:"); number=scanner.nextLine();
		for(int i=0;i<count;i++) {
			if(number.equals(account[i].number)) {
				System.out.print("출금액:");money=scanner.nextInt();
				account[i].subMoney(money);
				System.out.println("출금이 완료되었습니다.");
			}
		}
	}
	public void showAll() {
		System.out.println("***계좌정보출력***");
		for(int i=0;i<count;i++) {
			account[i].showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
}
