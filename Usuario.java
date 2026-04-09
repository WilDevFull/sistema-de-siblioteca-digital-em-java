public abstract class Usuario implements Exibivel {
    protected int codigo;
    protected String nome;
    protected String email;
    protected int emprestimosAtivos;

    public Usuario(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.emprestimosAtivos = 0;
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getEmprestimosAtivos() { return emprestimosAtivos; }
    
    public void adicionarEmprestimo() { this.emprestimosAtivos++; }
    public void removerEmprestimo() { this.emprestimosAtivos--; }

    public abstract int getLimiteEmprestimos();
    public abstract int getPrazoDevolucaoDias();
    public abstract double getValorMultaDiaria();
    public abstract String getTipoUsuario();

    @Override
    public String exibirResumo() {
        return String.format("[%s] Código: %d | Nome: %s | Email: %s | Empréstimos Ativos: %d/%d", 
            getTipoUsuario(), codigo, nome, email, emprestimosAtivos, getLimiteEmprestimos());
    }
}