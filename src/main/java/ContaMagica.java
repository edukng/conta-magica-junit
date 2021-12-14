import java.math.BigDecimal;

public class ContaMagica {

    private String nome;
    private BigDecimal saldo;
    private Categorias status;

    public ContaMagica() {
    }

    public ContaMagica(String nome, BigDecimal saldo, Categorias status) {
        this.nome = nome;
        this.saldo = saldo;
        this.status = status;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setStatus(Categorias status) {
        this.status = status;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Categorias getStatus() {
        return status;
    }

    public void depositar(BigDecimal valorParam) {

        setSaldo(saldo.add(valorParam));

        int gold = saldo.compareTo(new BigDecimal("50000"));
        int platinum = saldo.compareTo(new BigDecimal("200000"));

        if (gold > 0 || gold == 0) {
            setStatus(Categorias.GOLD);
            setSaldo(saldo.add(valorParam.multiply(new BigDecimal("0.01"))));
        }
        if (platinum > 0 || platinum == 0) {
            setStatus(Categorias.PLATINUM);
            setSaldo(saldo.add(valorParam.multiply(new BigDecimal("0.025"))));
        }
    }

    public void retirar(BigDecimal valor) {

        int verificaSaldo = saldo.compareTo(valor);

        if (verificaSaldo >= 0) {
            setSaldo(saldo.subtract(valor));

            if (getSaldo().compareTo(new BigDecimal("100000")) < 0 && getStatus() == Categorias.PLATINUM) {
                setStatus(Categorias.GOLD);

            } else if (getSaldo().compareTo(new BigDecimal("25000")) < 0 && getStatus() == Categorias.GOLD) {
                setStatus(Categorias.SILVER);
            }
        }
    }
}
