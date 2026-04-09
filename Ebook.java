public class Ebook extends Material {
    private String formato;
    private double tamanhoArquivoMb;

    public Ebook(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String formato, double tamanhoArquivoMb) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.formato = formato;
        this.tamanhoArquivoMb = tamanhoArquivoMb;
    }

    @Override
    public String getInformacaoEspecifica() {
        return "Formato: " + formato + " | Tamanho: " + tamanhoArquivoMb + "MB";
    }
}