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
        "100.0, 50.0, true",   
        "20.0, 10.0, true",    
        "10.0, 20.0, false"    
    })
    void pagamentoComCarteiraVerificadaENaoBloqueada(double inicial, double valor, boolean esperado) {
        DigitalWallet carteira = new DigitalWallet(inicial, true, false);
        boolean resultado = carteira.pay(valor);
        assertEquals(esperado, resultado);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, 0.0})
    void deveLancarExcecaoParaPagamentoInvalido(double valor) {
        DigitalWallet carteira = new DigitalWallet(100.0, true, false);
        assertThrows(IllegalArgumentException.class, () -> {
            carteira.pay(valor);
        });
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        DigitalWallet carteiraNaoVerificada = new DigitalWallet(100.0, false, false);
        assumeTrue(!carteiraNaoVerificada.isVerified());
        assertThrows(IllegalStateException.class, () -> {
            carteiraNaoVerificada.pay(50.0);
        });

        DigitalWallet carteiraBloqueada = new DigitalWallet(100.0, true, true);
        assumeTrue(carteiraBloqueada.isBlocked());
        assertThrows(IllegalStateException.class, () -> {
            carteiraBloqueada.pay(50.0);
        });
    }
}