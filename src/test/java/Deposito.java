import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;


class Deposito {
        
    @ParameterizedTest
    @ValueSource(doubles = {10.0, 0.01, 999.99})
    void deveDepositarValoresValidos(double amount) {
        DigitalWallet wallet = new DigitalWallet("teste", 0.0);
        wallet.deposit(amount);
        assertEquals(amount, wallet.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -50.0})
    void deveLancarExcecaoParaDepositoInvalido(double amount) {
        DigitalWallet wallet = new DigitalWallet("teste", 100.0);
        assertThrows(IllegalArgumentException.class, () -> wallet.deposit(amount));
    }
}
