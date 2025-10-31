package banking_bonus;

public class SpecialAccount extends NormalAccount{
	int count = 0;
	public SpecialAccount(String number, String name,int money,int interest) {
		super(number, name, money, interest);
	}
	@Override
	public void addMoney(int money) {
		// TODO Auto-generated method stub
		super.addMoney(money);
		count++;
		System.out.printf("입금%d번째 입니다.\n",count);
		if (count%2==0) {
			this.money+=500;
		}
	}
}
