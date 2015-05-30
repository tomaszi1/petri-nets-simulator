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
        table = new JTable(getTableModel());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("Dialog", Font.PLAIN, 15));
        table.setRowHeight(0, 21);
        //table.wid
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn tableColumn = columns.nextElement();
            DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
            defaultTableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            defaultTableCellRenderer.setMaximumSize(new Dimension(10,10));
            tableColumn.setCellRenderer(defaultTableCellRenderer);
            tableColumn.setMaxWidth(10);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        setTableModel(new MarkingTableModel(domainModel));
    }

    public MarkingTableModel getTableModel() {
        //przy dodawaniu ustaw rozmiar, nie przy inicie, bo on nic nie inicjuje!
        return tableModel;
    }
    public void addNewMarking(int id, int marking){
        tableModel.addNewMarking(id, marking);
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn tableColumn = columns.nextElement();
            DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
            defaultTableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            defaultTableCellRenderer.setMaximumSize(new Dimension(10, 21));
            tableColumn.setCellRenderer(defaultTableCellRenderer);
            tableColumn.setMaxWidth(10);
        }

        tableModel.refreshTableModel();

    }
    public void setTableModel(MarkingTableModel tableModel) {
        this.tableModel = tableModel;
    }
}
