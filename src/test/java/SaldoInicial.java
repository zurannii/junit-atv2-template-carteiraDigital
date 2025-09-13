import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

class SaldoInicial {
        @Test
        void deveConfigurarSaldoInicialCorreto() {
            DigitalWallet w = new DigitalWallet("Alice", 1000.0);
            assertEquals(1000.0, w.getBalance(), 1e-9);
        }

        @Test
        void deveLancarExcecaoParaSaldoInicialNegativo() {
            assertThrows(IllegalArgumentException.class,
                () -> new DigitalWallet("Bob", -1.0));
        }
    }