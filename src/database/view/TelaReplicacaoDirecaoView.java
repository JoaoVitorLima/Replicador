package database.view;

import database.dao.DirecaoDAO;
import database.dao.ReplicacaoProcessoDAO;
import database.model.TB_REPLICACAO_DIRECAO;
import database.model.TB_REPLICACAO_PROCESSO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelaReplicacaoDirecaoView extends JFrame {

    private enum ModoTela {NENHUM, INSERT, UPDATE}
    private TelaReplicacaoDirecaoView.ModoTela modoTela = TelaReplicacaoDirecaoView.ModoTela.NENHUM;

    private final Connection conn;
    private final DirecaoDAO dao;
    private  final ReplicacaoProcessoDAO daoProcesso;

    private JTextField txfId;
    private JComboBox<TB_REPLICACAO_PROCESSO> cbProcesso;
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

    public TelaReplicacaoDirecaoView(Connection conn) throws SQLException {

        this.conn = conn;
        this.dao = new DirecaoDAO(conn);
        this.daoProcesso = new ReplicacaoProcessoDAO(conn);

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

        // CARREGA COMBO PROCESSOS
        cbProcesso.removeAllItems();;
        ArrayList<TB_REPLICACAO_PROCESSO> processos = daoProcesso.selectAll();
        for (TB_REPLICACAO_PROCESSO p : processos) {
            cbProcesso.addItem(p);
        }

        txfId.setEnabled(false);
        cbProcesso.setEnabled(false);
        chkHabilitado.setEnabled(false);
        txfOrigem.setEnabled(false);
        txfUsuarioOrigem.setEnabled(false);
        txfSenhaDestino.setEnabled(false);
        txfUsuarioOrigem.setEnabled(false);
        txfUsuarioDestino.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);

        btnAdicionar.addActionListener(e -> {

            modoTela = ModoTela.INSERT;

            txfId.setText("");

            if (cbProcesso.getItemCount() > 0) {
                cbProcesso.setSelectedIndex(0);
            }
            chkHabilitado.setSelected(true);

            txfOrigem.setText("");
            txfUsuarioOrigem.setText("");
            txfSenhaOrigem.setText("");

            txfDestino.setText("");
            txfUsuarioDestino.setText("");
            txfSenhaDestino.setText("");

            cbProcesso.setEnabled(true);
            chkHabilitado.setEnabled(true);
            txfOrigem.setEnabled(true);
            txfUsuarioOrigem.setEnabled(true);
            txfSenhaOrigem.setEnabled(true);

            txfDestino.setEnabled(true);
            txfUsuarioDestino.setEnabled(true);
            txfSenhaDestino.setEnabled(true);

            btnSalvar.setEnabled(true);
            btnExcluir.setEnabled(true);
        });

        btnSalvar.addActionListener(e -> {
            try {
                if (cbProcesso.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Informe o PROCESSO.");
                    return;
                }

                if (txfOrigem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe a ORIGEM.");
                    return;
                }

                if (txfDestino.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o DESTINO.");
                    return;
                }

                if (txfSenhaOrigem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe a SENHA DE ORIGEM.");
                    return;
                }

                if (txfSenhaDestino.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe a SENHA DE DESTINO.");
                    return;
                }

                if (txfUsuarioOrigem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o USUÁRIO DE ORIGEM.");
                    return;
                }

                if (txfUsuarioDestino.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o USUÁRIO DE DESTINO.");
                    return;
                }

                TB_REPLICACAO_PROCESSO pSel = (TB_REPLICACAO_PROCESSO) cbProcesso.getSelectedItem();

                TB_REPLICACAO_DIRECAO d = new TB_REPLICACAO_DIRECAO();
                d.setProcesso_id(pSel.getId());
                d.setHabilitado(chkHabilitado.isSelected());
                d.setDirecao_origem(txfOrigem.getText().trim());
                d.setUsuario_origem(txfUsuarioOrigem.getText().trim());
                d.setSenha_origem(txfSenhaOrigem.getText().trim());
                d.setDirecao_destino(txfDestino.getText().trim());
                d.setUsuario_destino(txfUsuarioDestino.getText().trim());
                d.setSenha_destino(txfSenhaDestino.getText().trim());

                if (modoTela == ModoTela.INSERT) {
                    dao.insert(d);
                    JOptionPane.showMessageDialog(this, "Direção cadastrada!");
                } else if (modoTela == ModoTela.UPDATE) {
                    if (txfId.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "ID não carregado para update. ");
                        return;
                    }
                    d.setId(Integer.parseInt(txfId.getText()));
                    dao.update(d);
                    JOptionPane.showMessageDialog(this, "Direção atualizada!");
                } else {
                    JOptionPane.showMessageDialog(this, "Clique em ADICIONAR ou BUSCAR antes de salvar.");
                }

                modoTela = ModoTela.NENHUM;
                txfId.setEnabled(false);
                cbProcesso.setEnabled(false);
                chkHabilitado.setEnabled(false);
                txfOrigem.setEnabled(false);
                txfUsuarioOrigem.setEnabled(false);
                txfSenhaDestino.setEnabled(false);
                txfUsuarioOrigem.setEnabled(false);
                txfUsuarioDestino.setEnabled(false);
                btnSalvar.setEnabled(false);
                btnExcluir.setEnabled(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                if (txfId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "ID não carregado para exclusão. ");
                    return;
                }

                int op = JOptionPane.showConfirmDialog(this, "Excluir", "Deseja realmente excluir o registro? ", JOptionPane.YES_NO_OPTION);

                if (op != JOptionPane.YES_OPTION) return;

                long id = Long.parseLong(txfId.getText());
                dao.delete(id);
                JOptionPane.showMessageDialog(this, "Direção Excluída: ");

                modoTela = ModoTela.NENHUM;

                txfId.setText("");

                if (cbProcesso.getItemCount() > 0) {
                    cbProcesso.setSelectedIndex(0);
                }
                chkHabilitado.setSelected(false);

                txfOrigem.setText("");
                txfUsuarioOrigem.setText("");
                txfSenhaOrigem.setText("");

                txfDestino.setText("");
                txfUsuarioDestino.setText("");
                txfSenhaDestino.setText("");

                cbProcesso.setEnabled(false);
                chkHabilitado.setEnabled(false);
                txfOrigem.setEnabled(false);
                txfUsuarioOrigem.setEnabled(false);
                txfSenhaOrigem.setEnabled(false);

                txfDestino.setEnabled(false);
                txfUsuarioDestino.setEnabled(false);
                txfSenhaDestino.setEnabled(false);

                btnSalvar.setEnabled(false);
                btnExcluir.setEnabled(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage());
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                ConsultaDirecaoDialog dlg = new ConsultaDirecaoDialog(this, dao);
                dlg.setVisible(true);

                TB_REPLICACAO_DIRECAO sel = dlg.getSelecionado();

                if (sel == null) return;

                modoTela = ModoTela.UPDATE;

                txfId.setText(String.valueOf(sel.getId()));
                chkHabilitado.setSelected(sel.isHabilitado());

                txfOrigem.setText(sel.getDirecao_origem());
                txfUsuarioOrigem.setText(sel.getUsuario_origem());
                txfSenhaOrigem.setText(sel.getSenha_origem());

                txfDestino.setText(sel.getDirecao_destino());
                txfUsuarioDestino.setText(sel.getUsuario_destino());
                txfSenhaDestino.setText(sel.getSenha_destino());

                long id = sel.getProcesso_id();
                for (int i = 0; i < cbProcesso.getItemCount(); i++) {
                    TB_REPLICACAO_PROCESSO item = cbProcesso.getItemAt(i);
                    if (item.getId() == id) {
                        cbProcesso.setSelectedItem(i);
                        break;
                    }
                }

                cbProcesso.setEnabled(true);
                chkHabilitado.setEnabled(true);
                txfOrigem.setEnabled(true);
                txfUsuarioOrigem.setEnabled(true);
                txfSenhaDestino.setEnabled(true);
                txfUsuarioOrigem.setEnabled(true);
                txfUsuarioDestino.setEnabled(true);
                btnSalvar.setEnabled(true);
                btnExcluir.setEnabled(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao consultar registro: " + ex.getMessage());
            }
        });
    }
}