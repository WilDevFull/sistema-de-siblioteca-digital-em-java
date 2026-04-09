public abstract class Material implements Exibivel {
    protected int codigo;
    protected String titulo;
    protected int anoPublicacao;
    protected int quantidadeDisponivel;

    public Material(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }

    public boolean verificarDisponibilidade() {
        return this.quantidadeDisponivel > 0;
    }

    public void reduzirQuantidade() {
        if (verificarDisponibilidade()) {
            this.quantidadeDisponivel--;
        }
    }

    public void aumentarQuantidade() {
        this.quantidadeDisponivel++;
    }

    public abstract String getInformacaoEspecifica();

    @Override
    public String exibirResumo() {
        return String.format("[Material: %d] %s (%d) - Estoque: %d | %s", 
            codigo, titulo, anoPublicacao, quantidadeDisponivel, getInformacaoEspecifica());
    }
}