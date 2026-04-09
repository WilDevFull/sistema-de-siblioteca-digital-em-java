public class Professor extends Usuario {

    public Professor(int codigo, String nome, String email) {
        super(codigo, nome, email);
    }

    @Override
    public int getLimiteEmprestimos() { return 5; }

    @Override
    public int getPrazoDevolucaoDias() { return 14; }

    @Override
    public double getValorMultaDiaria() { return 1.00; }

    @Override
    public String getTipoUsuario() { return "Professor"; }
}