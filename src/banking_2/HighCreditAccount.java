package banking_2;

/*
Account의 자식클래스로 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
 */
public class HighCreditAccount extends Account{
	
	private int interest;
	private String credit; //A,B,C
	
	public HighCreditAccount(String number, String name,int money,int interest,String credit) {
		super(number, name, money);
		this.interest=interest;
		this.credit=credit;
	}
	@Override
	public void addMoney(int mon) {
		switch(credit) {
			case "A":
				money=(int)(money+(money*interest/100)+(money*0.07)+mon);
				break;
			case "B":
				money=(int)(money+(money*interest/100)+(money*0.04)+mon);
				break;
			case "C":
				money=(int)(money+(money*interest/100)+(money*0.02)+mon);
				break;
			default :
				System.out.println("A/B/C가 아닙니다");
		}
	}
	
	@Override
	public void showAccInfo() {
		// TODO Auto-generated method stub
		super.showAccInfo();
		System.out.println("기본이자 : "+interest+"%");
		System.out.println("신용등급 : "+credit);
	}
	
}
