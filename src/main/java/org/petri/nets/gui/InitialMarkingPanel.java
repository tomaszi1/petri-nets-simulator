package org.petri.nets.gui;

import org.petri.nets.model.DomainModel;
import org.petri.nets.model.PetriNet;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;

public class InitialMarkingPanel extends JPanel {
    public static final String PANEL_TITLE = "Znakowanie początkowe";
    public static final int PANEL_HEIGHT = 80;
    private final JTable table;
    private final MarkingTableModel tableModel;
    private final DomainModel domainModel;

    public InitialMarkingPanel(DomainModel domainModel) {
        this.domainModel = domainModel;

        setPreferredSize(new Dimension(100 /*ignored*/, PANEL_HEIGHT));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setLayout(new BorderLayout());
        tableModel = new MarkingTableModel();
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("Dialog", Font.PLAIN, 15));
        table.setRowHeight(0, 21);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn tableColumn = columns.nextElement();
            DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
            defaultTableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            tableColumn.setCellRenderer(defaultTableCellRenderer);
            tableColumn.setMaxWidth(35);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private class MarkingTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return domainModel.getPetriNet().getInitialMarking().size(); //FIXME
        }

        @Override
        public Object getValueAt(int row, int col) {
            return domainModel.getPetriNet().getInitialMarking().get(col);
        }

        @Override
        public String getColumnName(int col) {
            return "P"+(col+1);
        }

        @Override
        public Class<?> getColumnClass(int col) {
            return Integer.class;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return row == 0;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            try {
                PetriNet petriNet = domainModel.getPetriNet();

                if (value instanceof String) {
                    petriNet.setInitialMarking(col, Integer.parseInt((String) value));
                }
                else if (!(value instanceof Integer))
                    return;
                else {
                    Integer intval = (Integer) value;
                    if (intval < 0)
                        return;
                    petriNet.setInitialMarking(col, (Integer) value);
                }
                fireTableCellUpdated(row, col);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
    }
}
