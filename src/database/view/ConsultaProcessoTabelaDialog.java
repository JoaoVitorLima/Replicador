package database.view;

import database.dao.ProcessoTabelaDAO;
import database.model.TB_REPLICACAO_PROCESSO_TABELA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaProcessoTabelaDialog extends JDialog {

    private JTable table;
    private JButton btnSelecionar;
    private JButton btnCancelar;

    private TB_REPLICACAO_PROCESSO_TABELA selecionado;

    public ConsultaProcessoTabelaDialog (JFrame parent, ProcessoTabelaDAO dao) throws Exception {
        super(parent, "Consulta - Direções", true);
        setSize(1000, 420);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(null);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("PROCESSO_ID");
        model.addColumn("TABELA_ORIGEM");
        model.addColumn("TABELA_DESTINO");
        model.addColumn("ORDEM");
        model.addColumn("HABILITADO");

        ArrayList<TB_REPLICACAO_PROCESSO_TABELA> lista = dao.selectAll();
        for (TB_REPLICACAO_PROCESSO_TABELA p : lista) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getProcesso_id(),
                    p.getTabela_origem(),
                    p.getTabela_destino(),
                    p.getOrdem(),
                    p.isHabilitado()
            });
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 960, 300);
        add(scrollPane);

        btnSelecionar = new JButton("SELECIONAR");
        btnSelecionar.setBounds(10, 320, 140, 30);
        add(btnSelecionar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(160, 320, 140, 30);
        add(btnCancelar);

        btnCancelar.addActionListener(e -> {
            selecionado = null;
            dispose();
        });

        btnSelecionar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um registro.");
                return;
            }

            long id = Long.parseLong(table.getValueAt(row, 0).toString());
            TB_REPLICACAO_PROCESSO_TABELA d = null;
            try {
                d = dao.selectById(id);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            selecionado = d;
            dispose();
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    btnSelecionar.doClick();
                }
            }
        });
    }

    public TB_REPLICACAO_PROCESSO_TABELA getSelecionado() {
        return selecionado;
    }
}
