public class Revista extends Material {
    private int edicao;

    public Revista(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, int edicao) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.edicao = edicao;
    }

    @Override
    public String getInformacaoEspecifica() {
        return "Edição: " + edicao;
    }
}