import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo implements Exibivel, CalculavelMulta {
    private int id;
    private Usuario usuario;
    private Material material;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;
    private boolean finalizado;

    public Emprestimo(int id, Usuario usuario, Material material, LocalDate dataEmprestimo) {
        this.id = id;
        this.usuario = usuario;
        this.material = material;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(usuario.getPrazoDevolucaoDias());
        this.finalizado = false;
    }

    public int getId() { return id; }
    public boolean isFinalizado() { return finalizado; }
    public Usuario getUsuario() { return usuario; }

    public void registrarDevolucao(LocalDate dataDevolucao) {
        if (this.finalizado) {
            throw new IllegalStateException("Este empréstimo já foi finalizado.");
        }
        this.dataRealDevolucao = dataDevolucao;
        this.finalizado = true;
        this.material.aumentarQuantidade();
        this.usuario.removerEmprestimo();
    }

    public long calcularDiasAtraso() {
        if (!finalizado) return 0;
        long dias = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataRealDevolucao);
        return dias > 0 ? dias : 0;
    }

    @Override
    public double calcularMulta() {
        long diasAtraso = calcularDiasAtraso();
        return diasAtraso * usuario.getValorMultaDiaria();
    }

    @Override
    public String exibirResumo() {
        String status = finalizado ? "FINALIZADO em " + dataRealDevolucao : "EM ANDAMENTO (Previsto: " + dataPrevistaDevolucao + ")";
        return String.format("Empréstimo #%d | Usuário: %s | Material: %s | Status: %s", 
            id, usuario.getNome(), material.getTitulo(), status);
    }
}