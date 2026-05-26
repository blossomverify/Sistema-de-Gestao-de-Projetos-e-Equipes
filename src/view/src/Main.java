

import controller.EquipeController;
import controller.ProjetoController;
import controller.TarefaController;
import controller.UsuarioController;
import repository.EquipeRepository;
import repository.ProjetoRepository;
import repository.TarefaRepository;
import repository.UsuarioRepository;
import view.MenuPrincipal;
import view.TelaEquipe;
import view.TelaLogin; // Importando a nossa nova tela
import view.TelaProjeto;
import view.TelaTarefa;
import view.TelaUsuario;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        ProjetoRepository projetoRepository = new ProjetoRepository();
        EquipeRepository equipeRepository = new EquipeRepository();
        TarefaRepository tarefaRepository = new TarefaRepository();

        UsuarioController usuarioController = new UsuarioController(usuarioRepository);
        ProjetoController projetoController = new ProjetoController(projetoRepository);
        EquipeController equipeController = new EquipeController(equipeRepository);
        TarefaController tarefaController = new TarefaController(tarefaRepository);

    SwingUtilities.invokeLater(() -> {
            TelaUsuario telaUsuario = new TelaUsuario(usuarioController);
            TelaProjeto telaProjeto = new TelaProjeto(projetoController, usuarioController);
            TelaEquipe telaEquipe = new TelaEquipe(equipeController, usuarioController);
            TelaTarefa telaTarefa = new TelaTarefa(tarefaController, usuarioController);

            MenuPrincipal menu = new MenuPrincipal(telaUsuario, telaProjeto, telaEquipe, telaTarefa);
            
            // ATUALIZADO: Agora passamos o 'menu' E a 'telaUsuario' para o login saber abrir o cadastro!
            TelaLogin telaLogin = new TelaLogin(menu, telaUsuario);
            telaLogin.setVisible(true);
        });
    }
}
