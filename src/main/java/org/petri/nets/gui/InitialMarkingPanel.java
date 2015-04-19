package org.petri.nets.gui;

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

    public InitialMarkingPanel() {
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
        private Integer[] data = {0, 0, 0, 0, 0};

        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return 5; //FIXME
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return "P"+(column+1);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Integer.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return rowIndex == 0;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            try {
                if (value instanceof String)
                    data[col] = Integer.parseInt((String) value);
                else if (!(value instanceof Integer))
                    return;
                else {
                    Integer intval = (Integer) value;
                    if (intval < 0)
                        return;
                    data[col] = intval;
                }
                fireTableCellUpdated(row, col);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
    }
}
