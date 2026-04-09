import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Etapa 1: Criar a biblioteca
        System.out.println(">>> ETAPA 1: Criando a biblioteca...");
        Biblioteca biblioteca = new Biblioteca();

        // Etapa 2: Cadastrar usuários
        System.out.println(">>> ETAPA 2: Cadastrando usuários...");
        biblioteca.cadastrarUsuario(new Aluno(1, "Ana", "ana@email.com"));
        biblioteca.cadastrarUsuario(new Professor(2, "Carlos", "carlos@email.com"));

        // Etapa 3: Cadastrar materiais
        System.out.println(">>> ETAPA 3: Cadastrando materiais...");
        biblioteca.cadastrarMaterial(new Livro(101, "Java Básico", 2021, 2, "Deitel"));
        biblioteca.cadastrarMaterial(new Livro(102, "Estruturas de Dados", 2019, 1, "Szwarcfiter"));
        biblioteca.cadastrarMaterial(new Revista(201, "Revista Tech", 2023, 1, 45));
        biblioteca.cadastrarMaterial(new Ebook(301, "POO em Java", 2024, 999, "PDF", 5.2));

        // Etapa 4: Listar cadastros iniciais
        System.out.println("\n>>> ETAPA 4: Listando cadastros iniciais...");
        biblioteca.listarUsuarios();
        biblioteca.listarMateriais();

        // Etapa 5: Realizar empréstimos válidos
        System.out.println("\n>>> ETAPA 5: Realizando empréstimos válidos...");
        LocalDate dataHoje = LocalDate.now();
        biblioteca.realizarEmprestimo(1, 101, dataHoje); // Ana pega Java Básico (Id 1)
        biblioteca.realizarEmprestimo(1, 102, dataHoje); // Ana pega Estruturas de Dados (Id 2)
        biblioteca.realizarEmprestimo(1, 201, dataHoje); // Ana pega Revista Tech (Id 3)
        biblioteca.realizarEmprestimo(2, 301, dataHoje); // Carlos pega Ebook (Id 4)

        // Etapa 6: Realizar pelo menos uma tentativa inválida (Regra de Negócio)
        System.out.println("\n>>> ETAPA 6: Tentativas inválidas (Tratamento de Erros)...");
        try {
            System.out.println("Tentando emprestar um 4º item para Ana (limite é 3)...");
            biblioteca.realizarEmprestimo(1, 301, dataHoje);
        } catch (Exception e) {
            System.out.println("ERRO ESPERADO: " + e.getMessage());
        }

        try {
            System.out.println("Tentando emprestar Revista Tech (sem estoque)...");
            biblioteca.realizarEmprestimo(2, 201, dataHoje);
        } catch (Exception e) {
            System.out.println("ERRO ESPERADO: " + e.getMessage());
        }

        // Etapa 7: Listar empréstimos em andamento
        System.out.println("\n>>> ETAPA 7: Listando empréstimos em andamento...");
        biblioteca.listarEmprestimosEmAndamento();

        // Etapa 8: Registrar devoluções (Com e Sem atraso)
        System.out.println("\n>>> ETAPA 8: Registrando devoluções...");
        
        // Ana (Aluno - prazo 7 dias). Devolve o Empréstimo 1 no prazo (sem atraso)
        System.out.println("Ana devolvendo Java Básico no prazo (7 dias depois)...");
        biblioteca.registrarDevolucao(1, dataHoje.plusDays(7)); 
        
        // Ana devolve o Empréstimo 2 com atraso (10 dias depois, atraso de 3 dias)
        System.out.println("Ana devolvendo Estruturas de Dados com atraso (10 dias depois)...");
        biblioteca.registrarDevolucao(2, dataHoje.plusDays(10));

        // Etapa 9: Mostrar multas
        System.out.println("\n>>> ETAPA 9: Mostrando multas das devoluções...");
        Emprestimo emp1 = biblioteca.buscarEmprestimoPorId(1);
        Emprestimo emp2 = biblioteca.buscarEmprestimoPorId(2);
        
        System.out.printf("Multa do Empréstimo #1 (Sem atraso): R$ %.2f\n", emp1.calcularMulta());
        System.out.printf("Multa do Empréstimo #2 (Com atraso): R$ %.2f\n", emp2.calcularMulta());

        // Etapa 10: Exibir situação final
        System.out.println("\n>>> ETAPA 10: Situação Final do Sistema...");
        biblioteca.listarMateriais();
        biblioteca.listarEmprestimosFinalizados();
        biblioteca.listarEmprestimosEmAndamento();
    }
}