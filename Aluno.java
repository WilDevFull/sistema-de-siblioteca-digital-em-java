public class Aluno extends Usuario {

    public Aluno(int codigo, String nome, String email) {
        super(codigo, nome, email);
    }

    @Override
    public int getLimiteEmprestimos() { return 3; }

    @Override
    public int getPrazoDevolucaoDias() { return 7; }

    @Override
    public double getValorMultaDiaria() { return 2.50; }

    @Override
    public String getTipoUsuario() { return "Aluno"; }
}