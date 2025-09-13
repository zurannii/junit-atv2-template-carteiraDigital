import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;


public class Pagamento {
    @ParameterizedTest
    @CsvSource({
        "100.0, 30.0, true",
        "50.0, 80.0, false",
        "10.0, 10.0, true"
    })
    void pagamentoComCarteiraVerificadaENaoBloqueada(double inicial, double valor, boolean esperado) {
        DigitalWallet w = new DigitalWallet("Alice", inicial);
        w.verify();
        w.unlock();
        assumeTrue(w.isVerified(), "Pré-condição: carteira verificada");
        assumeFalse(w.isLocked(), "Pré-condição: carteira não bloqueada");

        double before = w.getBalance();
        boolean ok = w.pay(valor);

        assertEquals(esperado, ok);
        double after = w.getBalance();
        if (esperado) {
            assertEquals(before - valor, after, 1e-9);
        } else {
            assertEquals(before, after, 1e-9);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.0, -0.01, -5.0 })
    void deveLancarExcecaoParaPagamentoInvalido(double valor) {
        DigitalWallet w = new DigitalWallet("Alice", 100.0);
        w.verify();
        assumeTrue(w.isVerified());
        assertThrows(IllegalArgumentException.class, () -> w.pay(valor));
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        DigitalWallet w = new DigitalWallet("Alice", 100.0);
        // não verificada
        assertThrows(IllegalStateException.class, () -> w.pay(10.0));

        // verificada porém bloqueada
        w.verify();
        w.lock();
        assertThrows(IllegalStateException.class, () -> w.pay(10.0));
    }
}
