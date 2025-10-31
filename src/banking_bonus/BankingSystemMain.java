package banking_bonus;

import java.util.Scanner;

import banking_bonus.AccountManager;
import banking_bonus.MenuSelectException;

//main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다. 
public class BankingSystemMain implements ICustomDefine{

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
					case MOVE:
						accManager.makeAccount();
						break;
					case ADD:
						accManager.depositMoney();
						break;
					case SUB:
						accManager.withdrawMoney();
						break;
					case SHOW:
						accManager.showAll();
						break;
					case DEL:
						accManager.delacc();
						break;
					case EXIT:
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
