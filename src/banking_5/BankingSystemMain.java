package banking_5;

import java.util.Scanner;

import banking_5.AccountManager;
import banking_5.MenuSelectException;

//main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
public class BankingSystemMain {

	public static void showMenu() {// 메뉴출력
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.종료");
	}
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		AccountManager accManager=new AccountManager();
		for(;;) {
			try {
				showMenu();
				int choice=Integer.parseInt(scanner.nextLine());
				switch(choice) {
					case 1:
						accManager.makeAccount();
						break;
					case 2:
						accManager.depositMoney();
						break;
					case 3:
						accManager.withdrawMoney();
						break;
					case 4:
						accManager.showAll();
						break;
					case 5:
						accManager.delacc();
						break;
					case 6:
						accManager.writeacc();
						return ;
					default:
						MenuSelectException menu = new MenuSelectException();
						throw menu;
				}
			}catch(MenuSelectException e) {
				System.out.println("메뉴는 1~6사이의 정수를 입력하세요");
			}
		}
	}

}
