import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteContaMagica {

    private ContaMagica conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaMagica("Silva", BigDecimal.ZERO, Categorias.SILVER);
    }

    @Test
    void deveAtualizarCategoriaParaGold() {
        conta.depositar(new BigDecimal("40000"));
        conta.depositar(new BigDecimal("10000"));
        conta.depositar(new BigDecimal("10"));
        System.out.println(conta.getSaldo());
        assertEquals(Categorias.GOLD, conta.getStatus());
    }

    @Test
    void deveAtualizarCategoriaParaPlatinum() {
        conta.depositar(new BigDecimal("200000"));
        System.out.println(conta.getSaldo());
        assertEquals(Categorias.PLATINUM, conta.getStatus());
    }

    @Test
    void deveRetirarValor() {
        conta.depositar(new BigDecimal("20000"));
        conta.retirar(new BigDecimal("10000"));
        System.out.println(conta.getSaldo());
        assertEquals(new BigDecimal("10000"), conta.getSaldo());
    }

    @Test
    void deveAtualizarDePlatinumParaGold() {
        conta.depositar(new BigDecimal("200000"));
        conta.retirar(new BigDecimal("100000"));
        conta.retirar(new BigDecimal("8000"));
        System.out.println(conta.getSaldo());
        assertEquals(Categorias.GOLD, conta.getStatus());
    }

    @Test
    void deveAtualizarDeGoldParaSilver() {
        conta.depositar(new BigDecimal("50000"));
        conta.retirar(new BigDecimal("40000"));
        assertEquals(Categorias.SILVER, conta.getStatus());
    }

    @Test
    void naoDeveAtualizarDePlatinumParaSilver() {
        conta.depositar(new BigDecimal("200000"));
        conta.retirar(new BigDecimal("198000"));
        assertEquals(Categorias.GOLD, conta.getStatus());
    }

    @Test
    void naoDeveRetirarValorMaiorQueSaldo() {
        conta.depositar(new BigDecimal("50000"));
        conta.retirar(new BigDecimal("60000"));
        assertEquals(new BigDecimal("50500.00"), conta.getSaldo());
    }
}
