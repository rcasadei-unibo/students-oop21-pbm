package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import main.model.account.Account;
import main.model.account.InvestmentAccount;
import main.model.account.InvestmentAccountTypeFactory;
import main.model.account.InvestmentAccountTypeFactoryImpl;
import main.model.account.NotEnoughFundsException;

public class TestInvestmentAccount {

	private final InvestmentAccountTypeFactory accFactory = new InvestmentAccountTypeFactoryImpl();
	private final InvestmentAccount acc1 = accFactory.createForFree();
	private static double feeRate = 0.05;
	private InvestmentAccount acc2 = accFactory.createWithOperationFees(f -> f * feeRate);

	@Test
	public void testAccountType() {
	    //so basically investmentAccount is a subtype of Account
	    //but if i use Account, i will lose the ability of using 
	    //methods from sub interfaces. Interesting but not useful.
	    final Account acc = accFactory.createForFree();
	}
	
	
	@Test
	public void testDeposit() {
		acc1.deposit(200);
		assertEquals(200, acc1.getBalance());
		acc2.deposit(200);
		assertEquals(200 * (1 - feeRate), acc2.getBalance());

		assertEquals(0, acc1.getInvestedBalance());
		assertEquals(0, acc2.getInvestedBalance());
	}

	@Test
	public void testWithDraw() {
		assertEquals(0, acc1.getBalance());
		try {
			acc1.withdraw(200);
			acc1.cashout(200);
			fail("you shouldn't be able to withdraw");
		} catch (NotEnoughFundsException e) {

		}

		acc1.deposit(20);
		acc1.withdraw(19);
		assertEquals(1, acc1.getBalance());
		try {
			acc1.withdraw(2);
			fail();
		} catch (NotEnoughFundsException e) {

		}

	}

	@Test
	public void testInvest() {
		try {
			acc1.invest(200);
			fail();
		} catch (NotEnoughFundsException e) {

		}
		acc1.deposit(200);
		acc1.invest(200);
		assertEquals(200, acc1.getInvestedBalance());
		acc1.cashout(150);
		assertEquals(50, acc1.getInvestedBalance());
		
	}

}
