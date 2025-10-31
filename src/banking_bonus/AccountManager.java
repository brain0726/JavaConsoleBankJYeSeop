package banking_bonus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;


//컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
public class AccountManager {
	Scanner scanner = new Scanner(System.in);
	//멤버변수
	private HashSet<Account> account;
	private boolean file_check =true;
	
	//생성자
	public AccountManager() {
		account = new HashSet<Account>();
	}
	
	
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.println("3.특판계좌");
		
		int choice=scanner.nextInt();
		scanner.nextLine();
		Account newacc;
		String number,name;
		int money,interest;
		Boolean bol;
		
		System.out.print("계좌번호 : "); number = scanner.nextLine();
		System.out.print("고객이름 : "); name = scanner.nextLine();
		System.out.print("잔고 : "); money = Integer.parseInt(scanner.nextLine());
		System.out.print("기본이자%(정수형태로입력): "); interest = Integer.parseInt(scanner.nextLine());
		if(choice==1) {
			newacc=new NormalAccount(number, name, money, interest);
			bol= account.add(newacc);
		}
		else if(choice==2){
			String credit;
			System.out.print("신용등급(A,B,C등급):" );credit =scanner.nextLine();
			newacc=new HighCreditAccount(number, name, money, interest, credit);
			bol= account.add(newacc);
		}
		else {
			newacc=new SpecialAccount(number, name, money, interest);
			bol= account.add(newacc);
		}
		if (bol==false) {
			System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)"); String check = scanner.nextLine();
			if(check.equals("y")) {
				account.remove(newacc);
				account.add(newacc);
				System.out.println("계좌계설이 완료되었습니다.");
			}
		}
		else
			System.out.println("계좌계설이 완료되었습니다.");
	}
	public void depositMoney() {
		String number;
		int money;
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:"); number=scanner.nextLine();
		for(Account A: account) {
			if(number.equals(A.number)) {
				System.out.print("입금액:");money=Integer.parseInt(scanner.nextLine());
				if (money>0) {
					if(money%500==0) {
						A.addMoney(money);
						System.out.println("입금이 완료되었습니다.");
					}
					else 
						System.out.println("500원 단위로 입금가능함");
				}
				else 
					System.out.println("음수는 입금불가");
			}
		}
	}
	public void withdrawMoney() {
		String number;
		int money;
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:"); number=scanner.nextLine();
		for(Account A: account) {
			if(number.equals(A.number)) {
				System.out.print("출금액:");money=Integer.parseInt(scanner.nextLine());
				if (money>0) {
					if(money<A.money) {
						A.subMoney(money);
						System.out.println("출금이 완료되었습니다.");
					}
					else {
						System.out.print("잔고부족. 금액전체를 출금할까요?(y or n)");
						String check = scanner.nextLine();
						if(check.equals("y")) {
							A.subMoney(A.money);
							System.out.println("출금이 완료되었습니다.");
						}
						else if (check.equals("n"))
							System.out.println("출금을 못했습니다.");
					}
				}
				else 
					System.out.println("음수는 출금불가");
			}
		}
	}
	public void showAll() {
		if(file_check) {
			try {
				ObjectInputStream in =
					new ObjectInputStream(
						new FileInputStream("src/banking_5/AccountInfo.obj")
					);	
				while(true) {
					try {
						account.add((Account)in.readObject());
					}
					catch(FileNotFoundException e) {
						System.out.println("[예외]파일없음");
					}
					catch(Exception e) {
						System.out.println("파일이 없음");
						break;
					}
				}
				
			}catch(IOException e) {
				System.out.println("파일이 없음");
			}
		}
		System.out.println("***계좌정보출력***");
		for(Account A: account) {
			System.out.println("-------------");
			A.showAccInfo();
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
	public void delacc() {
		System.out.println("***계좌정보삭제***");
		System.out.println("삭제할 계좌번호를 입력하세요.");
		System.out.print("계좌번호:");String number=scanner.nextLine();
		boolean isFind = false; 
		
		for(Account a : account)
			if(a.number.equals(number)) {
				account.remove(a);
				System.out.println("계좌가 삭제되었습니다.");
				isFind=true;
				break;
			}
		if(isFind==false)
			System.out.println("일치하는 계좌가 없습니다.");
	}
	public void writeacc() {
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(
						new FileOutputStream("src/banking_5/AccountInfo.obj")
					);
			for (Account a : account) 
				out.writeObject(a);
			out.close();
		}
		catch(IOException e) {
			System.out.println("문자스트림 작업중 오류발생");
			e.printStackTrace();
		}
		System.out.println("프로그램이 종료되었습니다.");
	}
}
