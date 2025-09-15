import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;



class Estorno {
    static Stream<Arguments> valoresEstorno() {
        return Stream.of(
            Arguments.of(100.0, 10.0, 110.0),
            Arguments.of(0.0,   5.0,   5.0),
            Arguments.of(50.0,  0.01, 50.01)
        );
    }

    
    @ParameterizedTest
    @MethodSource("valoresEstorno")
    void refundComCarteiraValida(double inicial, double valor, double saldoEsperado) {
        DigitalWallet wallet = new DigitalWallet("Teste", inicial);
        wallet.verify();
        wallet.unlock();

        assumeTrue(wallet.isVerified());
        assumeFalse(wallet.isLocked());

        wallet.refund(valor);

        assertEquals(saldoEsperado, wallet.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0, -50.0})
    void deveLancarExcecaoParaRefundInvalido(double valor) {
        DigitalWallet wallet = new DigitalWallet("Teste", 100.0);
        wallet.verify();
        wallet.unlock();

        assertThrows(IllegalArgumentException.class, () -> wallet.refund(valor));
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
    
        DigitalWallet wallet1 = new DigitalWallet("Teste", 100.0);
        wallet1.unlock();
        assumeFalse(wallet1.isVerified());
        assertThrows(IllegalStateException.class, () -> wallet1.refund(10.0));

        DigitalWallet wallet2 = new DigitalWallet("Teste", 100.0);
        wallet2.verify();
        wallet2.lock();
        assumeTrue(wallet2.isLocked());
        assertThrows(IllegalStateException.class, () -> wallet2.refund(10.0));
    }
}