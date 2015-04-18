package org.petri.nets.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Enumeration;

public class InitialMarkingPanel extends JPanel {
    public static final String PANEL_TITLE = "Znakowanie początkowe";
    public static final int PANEL_HEIGHT = 70;

    public InitialMarkingPanel() {
        setPreferredSize(new Dimension(100 /*ignored*/, PANEL_HEIGHT));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setLayout(new BorderLayout());
        JTable table = new JTable(new String[][]{{"0", "1", "5"}}, new String[]{"P1", "P2", "P3"});
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("Dialog", Font.PLAIN, 14));
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();

        while(columns.hasMoreElements()){
            TableColumn tableColumn = columns.nextElement();
            DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
            defaultTableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            tableColumn.setCellRenderer(defaultTableCellRenderer);
            tableColumn.setMaxWidth(40);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
