public class Livro extends Material {
    private String autor;

    public Livro(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String autor) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.autor = autor;
    }

    @Override
    public String getInformacaoEspecifica() {
        return "Autor: " + autor;
    }
}