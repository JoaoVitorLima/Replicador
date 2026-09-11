package database.view;

import database.model.TB_REPLICACAO_DIRECAO;

import javax.swing.*;
import java.awt.*;

public class TelaReplicacaoDirecaoView extends JFrame {

    private JTextField txfId;
    private JComboBox<TB_REPLICACAO_DIRECAO> cbProcesso;
    private JTextField txfOrigem;
    private JTextField txfDestino;
    private JTextField txfUsuarioOrigem;
    private JTextField txfUsuarioDestino;
    private JTextField txfSenhaOrigem;
    private JTextField txfSenhaDestino;
    private JCheckBox chkHabilitado;

    private JButton btnSalvar;
    private JButton btnAdicionar;
    private JButton btnBuscar;
    private JButton btnExcluir;

    public TelaReplicacaoDirecaoView() {
        setTitle("Cadastro de Tabelas");
        setSize(780, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // ===== BOTÕES =====
        btnBuscar = new JButton("BUSCAR");
        btnAdicionar = new JButton("ADICIONAR");
        btnSalvar = new JButton("SALVAR");
        btnExcluir = new JButton("EXCLUIR");

        btnBuscar.setBounds(10, 10, 130, 30);
        btnAdicionar.setBounds(150, 10, 130, 30);
        btnSalvar.setBounds(290, 10, 130, 30);
        btnExcluir.setBounds(430, 10, 130, 30);

        getContentPane().add(btnBuscar);
        getContentPane().add(btnAdicionar);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnExcluir);

        // ===== ID =====
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10, 60, 140, 25);
        getContentPane().add(lblId);

        txfId = new JTextField();
        txfId.setBounds(160, 60, 220, 25);
        getContentPane().add(txfId);

        // ===== PROCESSO =====
        JLabel lblProcesso = new JLabel("PROCESSO:");
        lblProcesso.setBounds(10, 95, 140, 25);
        getContentPane().add(lblProcesso);

        cbProcesso = new JComboBox<>();
        cbProcesso.setBounds(160, 95, 590, 25);
        getContentPane().add(cbProcesso);

        // ===== SEÇÃO ORIGEM =====
        JLabel lblOrigem = new JLabel("ORIGEM");
        lblOrigem.setBounds(10, 140, 200, 25);
        lblOrigem.setFont(lblOrigem.getFont().deriveFont(Font.BOLD, 14f));
        getContentPane().add(lblOrigem);

        // DIREÇÃO ORIGEM
        JLabel lblDirecaoOrigem = new JLabel("DIREÇÃO ORIGEM:");
        lblDirecaoOrigem.setBounds(10, 175, 140, 25);
        getContentPane().add(lblDirecaoOrigem);

        txfOrigem = new JTextField();
        txfOrigem.setBounds(160, 175, 590, 25);
        getContentPane().add(txfOrigem);

        // USUÁRIO ORIGEM
        JLabel lblUsuarioOrigem = new JLabel("USUÁRIO ORIGEM:");
        lblUsuarioOrigem.setBounds(10, 210, 140, 25);
        getContentPane().add(lblUsuarioOrigem);

        txfUsuarioOrigem = new JTextField();
        txfUsuarioOrigem.setBounds(160, 210, 590, 25);
        getContentPane().add(txfUsuarioOrigem);

        // SENHA ORIGEM (corrigido: antes estava em y=225 sobrepondo o usuário)
        JLabel lblSenhaOrigem = new JLabel("SENHA ORIGEM:");
        lblSenhaOrigem.setBounds(10, 245, 140, 25);
        getContentPane().add(lblSenhaOrigem);

        txfSenhaOrigem = new JTextField();
        txfSenhaOrigem.setBounds(160, 245, 590, 25);
        getContentPane().add(txfSenhaOrigem);

        // ===== SEÇÃO DESTINO =====
        JLabel lblDestino = new JLabel("DESTINO");
        lblDestino.setBounds(10, 290, 200, 25);
        lblDestino.setFont(lblDestino.getFont().deriveFont(Font.BOLD, 14f));
        getContentPane().add(lblDestino);

        // DIREÇÃO DESTINO
        JLabel lblDirecaoDestino = new JLabel("DIREÇÃO DESTINO:");
        lblDirecaoDestino.setBounds(10, 325, 140, 25);
        getContentPane().add(lblDirecaoDestino);

        txfDestino = new JTextField();
        txfDestino.setBounds(160, 325, 590, 25);
        getContentPane().add(txfDestino);

        // USUÁRIO DESTINO
        JLabel lblUsuarioDestino = new JLabel("USUÁRIO DESTINO:");
        lblUsuarioDestino.setBounds(10, 360, 140, 25);
        getContentPane().add(lblUsuarioDestino);

        txfUsuarioDestino = new JTextField();
        txfUsuarioDestino.setBounds(160, 360, 280, 25);
        getContentPane().add(txfUsuarioDestino);

        // SENHA DESTINO
        JLabel lblSenhaDestino = new JLabel("SENHA DESTINO:");
        lblSenhaDestino.setBounds(460, 360, 120, 25);
        getContentPane().add(lblSenhaDestino);

        txfSenhaDestino = new JTextField();
        txfSenhaDestino.setBounds(580, 360, 170, 25);
        getContentPane().add(txfSenhaDestino);

        // ===== HABILITADO =====
        chkHabilitado = new JCheckBox("HABILITADO");
        chkHabilitado.setBounds(10, 405, 140, 25);
        getContentPane().add(chkHabilitado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaReplicacaoDirecaoView().setVisible(true));
    }
}