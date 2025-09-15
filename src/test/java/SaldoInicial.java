import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

class SaldoInicial {
    @Test
        void deveConfigurarSaldoInicialCorreto() {
            DigitalWallet carteira = new DigitalWallet(200.0, true, false);
            assertEquals(200.0, carteira.getBalance());
        }

    @Test
        void deveLancarExcecaoParaSaldoInicialNegativo() {
            assertThrows(IllegalArgumentException.class, () -> {
            new DigitalWallet(-100.0, true, false);
        });
        }
    }