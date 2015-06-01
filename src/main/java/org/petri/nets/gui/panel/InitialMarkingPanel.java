package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Place;
import org.petri.nets.service.GraphService;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;

public class InitialMarkingPanel extends JPanel {
    public static final String PANEL_TITLE = "Znakowanie początkowe";
    public static final int PANEL_HEIGHT = 80;

    private InitialMarkingTable table;
    private MarkingTableModel tableModel;

    public InitialMarkingPanel(GraphService graphService) {
        this.tableModel = new MarkingTableModel(graphService);
        setPreferredSize(new Dimension(100 /*ignored*/, PANEL_HEIGHT));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setLayout(new BorderLayout());
        initMarkingTable();
    }

    private void initMarkingTable() {
        table = new InitialMarkingTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateMarking() {
        tableModel.fireTableStructureChanged();
    }

    public class InitialMarkingTable extends JTable {

        private final DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        private final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

        public InitialMarkingTable(MarkingTableModel tableModel) {
            super(tableModel);

            setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            getTableHeader().setReorderingAllowed(false);
            setFont(FONT);
            getTableHeader().setFont(FONT);
            setRowHeight(0, 21);
            setRowSelectionAllowed(false);
            setCellSelectionEnabled(true);
            defaultTableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        }

        @Override
        public void tableChanged(TableModelEvent e) {
            super.tableChanged(e);

            Enumeration<TableColumn> columns = getColumnModel().getColumns();
            while (columns.hasMoreElements()) {
                TableColumn tableColumn = columns.nextElement();
                tableColumn.setCellRenderer(defaultTableCellRenderer);
                tableColumn.setMaxWidth(35);
            }
        }
    }
}
