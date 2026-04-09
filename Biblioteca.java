import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Usuario> usuarios;
    private List<Material> materiais;
    private List<Emprestimo> emprestimos;
    private int proximoIdEmprestimo = 1;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.materiais = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void cadastrarUsuario(Usuario u) { usuarios.add(u); }
    public void cadastrarMaterial(Material m) { materiais.add(m); }

    public Usuario buscarUsuarioPorCodigo(int codigo) {
        for (Usuario u : usuarios) {
            if (u.getCodigo() == codigo) return u;
        }
        return null;
    }

    public Material buscarMaterialPorCodigo(int codigo) {
        for (Material m : materiais) {
            if (m.getCodigo() == codigo) return m;
        }
        return null;
    }

    public Emprestimo buscarEmprestimoPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoMaterial, LocalDate dataEmprestimo) {
        Usuario u = buscarUsuarioPorCodigo(codigoUsuario);
        Material m = buscarMaterialPorCodigo(codigoMaterial);

        if (u == null) throw new IllegalArgumentException("Usuário inexistente.");
        if (m == null) throw new IllegalArgumentException("Material inexistente.");
        if (!m.verificarDisponibilidade()) throw new IllegalStateException("Material sem quantidade disponível.");
        if (u.getEmprestimosAtivos() >= u.getLimiteEmprestimos()) {
            throw new IllegalStateException("Usuário atingiu o limite máximo de empréstimos.");
        }

        m.reduzirQuantidade();
        u.adicionarEmprestimo();
        Emprestimo emp = new Emprestimo(proximoIdEmprestimo++, u, m, dataEmprestimo);
        emprestimos.add(emp);
        System.out.println("Sucesso: Empréstimo #" + emp.getId() + " realizado para " + u.getNome());
    }

    public void registrarDevolucao(int idEmprestimo, LocalDate dataDevolucao) {
        Emprestimo e = buscarEmprestimoPorId(idEmprestimo);
        if (e == null) throw new IllegalArgumentException("Empréstimo não encontrado.");
        e.registrarDevolucao(dataDevolucao);
    }

    public void listarUsuarios() {
        System.out.println("\n--- USUÁRIOS ---");
        for (Usuario u : usuarios) System.out.println(u.exibirResumo());
    }

    public void listarMateriais() {
        System.out.println("\n--- MATERIAIS ---");
        for (Material m : materiais) System.out.println(m.exibirResumo());
    }

    public void listarEmprestimosEmAndamento() {
        System.out.println("\n--- EMPRÉSTIMOS EM ANDAMENTO ---");
        for (Emprestimo e : emprestimos) {
            if (!e.isFinalizado()) System.out.println(e.exibirResumo());
        }
    }

    public void listarEmprestimosFinalizados() {
        System.out.println("\n--- EMPRÉSTIMOS FINALIZADOS ---");
        for (Emprestimo e : emprestimos) {
            if (e.isFinalizado()) System.out.println(e.exibirResumo());
        }
    }
}