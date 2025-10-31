package banking_5;

/*
Account의 자식클래스로 보통예금계좌를 의미한다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다. 
 */
public class NormalAccount extends Account {
	
	int interest;
	public NormalAccount(String number, String name,int money,int interest) {
		super(number, name, money);
		this.interest=interest;
	}
	
	@Override
	public void addMoney(int money) {
		this.money=(int)(this.money+(this.money*interest/100)+money);
	}
	
	@Override
		public void showAccInfo() {
			// TODO Auto-generated method stub
			super.showAccInfo();
			System.out.println("기본이자 : "+interest+"%");
		}
	
}
