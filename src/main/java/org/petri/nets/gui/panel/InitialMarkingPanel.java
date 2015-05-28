package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;

public class InitialMarkingPanel extends JPanel {
    public static final String PANEL_TITLE = "Znakowanie początkowe";
    public static final int PANEL_HEIGHT = 80;
    private JTable table;
    private MarkingTableModel tableModel;
    private final DomainModel domainModel;

    public InitialMarkingPanel(DomainModel domainModel) {
        this.domainModel = domainModel;
        //this.tableModel = new MarkingTableModel(domainModel);
        setPreferredSize(new Dimension(100 /*ignored*/, PANEL_HEIGHT));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setLayout(new BorderLayout());
        initMarkingTable();
    }

    private void initMarkingTable() {
        setTableModel(new MarkingTableModel(domainModel));
        table = new JTable(getTableModel());
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

    public MarkingTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(MarkingTableModel tableModel) {
        this.tableModel = tableModel;
    }
}
