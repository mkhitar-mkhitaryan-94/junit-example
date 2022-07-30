import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(BankAccountParameterResolver.class)
public class BankAccountDI {


    @DisplayName("Deposit 400 successfully!")
    @ParameterizedTest
    @CsvSource({"100,Mary,Johnson", "300,Samvel,Sargsyan", "600,Anna,Poxosyan"})
    public void testDepositAndName(double amount, String name, String surname, BankAccount bankAccount) {
        bankAccount.deposit(amount);
        bankAccount.setHolderName(name);
        bankAccount.setSurname(surname);
        assertEquals(amount, bankAccount.getBalance());
        assertEquals(name, bankAccount.getHolderName());
        assertEquals(surname, bankAccount.getSurname());

    }
}
