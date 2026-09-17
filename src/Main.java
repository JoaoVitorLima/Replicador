import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingFormatArgumentException;

public class Main extends JFrame {

    private JDesktopPane desktop;
    private static Connection conn;

    public Main() {
        setTitle("Sistema de Replicação de Dados");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        desktop = new JDesktopPane();
        setContentPane(desktop);

        JMenu menuSistema = new JMenu("Sistema");

        JMenuItem itemExecutar = new JMenu("Executar Replicação");
        itemExecutar.addActionListener(e -> {});
        menuSistema.add(itemExecutar);

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            System.exit(0);
        });
        menuSistema.add(itemSair);

        JMenu menuCadastro = new JMenu("Cadastro");

        JMenuItem itemProcesso = new JMenuItem("Processos");
        itemProcesso.addActionListener(e -> {});
        menuCadastro.add(itemProcesso);

        JMenuItem itemProcessoTabela = new JMenuItem("Processo x Tabelas");
        itemProcessoTabela.addActionListener(e -> {});
        menuCadastro.add(itemProcessoTabela);

        JMenuItem itemDirecao = new JMenuItem("Direções");
        itemDirecao.addActionListener(e -> {});
        menuCadastro.add(itemDirecao);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuSistema);
        menuBar.add(menuCadastro);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/controle", "postgres", "admin123");
            SwingUtilities.invokeLater(() -> new Main().setVisible(true));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.");
            System.exit(0);
        }

    }
}