package banking_5;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;


//계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가 된다. 
abstract public class Account implements ICustomDefine ,Serializable{
	//고객의 계좌정보는 계좌번호(String형), 이름(String형), 잔액(int형) 3가지만 저장 및 관리한다.
	String number;
	String name;
	int money;
	
	public Account (String number, String name,	int money) {
		this.number=number;
		this.name=name;
		this.money=money;
	}
	
	public void addMoney(int money){    // 입    금
		this.money+=money;
	}

	public void subMoney(int money){ // 출    금
		this.money-=money;
	}
	
	public void showAccInfo(){  // 전체계좌정보출력
        System.out.printf("계좌번호 : %s\n"
        		+ "고객이름 : %s\n"
        		+ "잔고 : %d\n",number,name,money);
	}
	
	@Override
	public int hashCode() {
		//방법1 : 멤버변수의 hash값을 얻어온 후 적당히 연산한다.
		int retCode1 = this.number.hashCode();
		return retCode1;
	}
	@Override
	public boolean equals(Object obj) {
		Account av = (Account) obj;
		if(av.number.equals(this.number))  
			return true;
		else
			return false;
	}
}
