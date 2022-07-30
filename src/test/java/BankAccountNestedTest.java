import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class BankAccountNestedTest {
    @Test
    @DisplayName("WithDraw 500 successfully!")
    public void testWithDraw() {
        BankAccount bankAccount = new BankAccount(500, -1000);
        bankAccount.withdraw(300);
        assertEquals(200, bankAccount.getBalance());


    }

    @Test
    @DisplayName("Deposit 400 successfully!")
    public void testDeposit() {
        BankAccount bankAccount = new BankAccount(400, 0);
        bankAccount.deposit(500);
        assertEquals(900, bankAccount.getBalance());

    }

    @Test
    @DisplayName("Withdraw will become negative!")
    public void testWithdrawNotStuckAtZero() {
        BankAccount bankAccount = new BankAccount(500, -1000);
        bankAccount.withdraw(800);
        assertNotEquals(0, bankAccount.getBalance());


    }

    @Test
    @DisplayName("Test activation account after creation!")
    public void isActive() {
        BankAccount bankAccount = new BankAccount(500, 0);
        assumingThat(bankAccount != null, () -> assertTrue(bankAccount.isActive()));

    }

    @Test
    @DisplayName("Test set holder name!")
    public void testHolderName() {
        BankAccount bankAccount = new BankAccount(500, 0);
        bankAccount.setHolderName("Mxo");
        assertNotNull(bankAccount.getHolderName());

    }

    @Test
    @DisplayName("Test than we can't withdraw below minimum!")
    public void testNoWithdrawBelowMinimum() {
        BankAccount bankAccount = new BankAccount(500, -1000);
        assertThrows(RuntimeException.class, () -> bankAccount.withdraw(2000));


    }

    @Test
    @DisplayName("Test than ")
    public void testNoWithdrawAndDepositWithoutException() {
        BankAccount bankAccount = new BankAccount(500, -1000);
        assertAll(() -> bankAccount.deposit(200), () -> bankAccount.withdraw(450));

    }

    @Test
    @DisplayName("Test speed deposit!")
    public void testDepositTimeout() {
        BankAccount bankAccount = new BankAccount(400, 0);
        assertTimeout(Duration.ofNanos(1), () -> bankAccount.deposit(200));


    }

    @Nested
    class WhenBalanceEqualsZero {
        @Test
        public void testWithdrawMinimumBalanceIs0() {
            BankAccount bankAccount = new BankAccount(0, 0);
            assertThrows(RuntimeException.class, () -> bankAccount.withdraw(500));

        }

        @Test
        public void testWithdrawMinimumBalanceIsNegative1000() {
            BankAccount bankAccount = new BankAccount(0, -1000);
            bankAccount.withdraw(500);
            assertEquals(-500, bankAccount.getBalance());


        }


    }


}
