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
        DigitalWallet w = new DigitalWallet("Alice", inicial);
        w.verify();
        assumeTrue(w.isVerified());
        assumeFalse(w.isLocked());

        w.refund(valor);
        assertEquals(saldoEsperado, w.getBalance(), 1e-9);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.0, -0.01, -3.0 })
    void deveLancarExcecaoParaRefundInvalido(double valor) {
        DigitalWallet w = new DigitalWallet("Alice", 100.0);
        w.verify();
        assumeTrue(w.isVerified());
        assertThrows(IllegalArgumentException.class, () -> w.refund(valor));
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        DigitalWallet w = new DigitalWallet("Alice", 100.0);
        assertThrows(IllegalStateException.class, () -> w.refund(10.0));

        w.verify();
        w.lock();
        assertThrows(IllegalStateException.class, () -> w.refund(10.0));
    }
}
