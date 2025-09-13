import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;


class Deposito {
        @ParameterizedTest
        @ValueSource(doubles = { 0.01, 10.0, 999.99 })
        void deveDepositarValoresValidos(double amount) {
            DigitalWallet w = new DigitalWallet("Alice", 100.0);
            double before = w.getBalance();
            w.deposit(amount);
            assertEquals(before + amount, w.getBalance(), 1e-9);
        }

        @ParameterizedTest
        @ValueSource(doubles = { 0.0, -0.01, -100.0 })
        void deveLancarExcecaoParaDepositoInvalido(double amount) {
            DigitalWallet w = new DigitalWallet("Alice", 100.0);
            assertThrows(IllegalArgumentException.class, () -> w.deposit(amount));
        }
    }