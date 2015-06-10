package org.petri.nets.gui.panel;

import org.petri.nets.gui.panel.matrixPanels.*;
import org.petri.nets.service.GraphService;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by Asia on 2015-06-07.
 */
public class NetMatrixPanel extends JPanel {

    public static final String PANEL_TITLE = "Macierz Grafu";
    public static final String PANEL_PLUS_TITLE = "Macierz Wejść";
    public static final String PANEL_MINUS_TITLE = "Macierz Wyjść";
    public static final int PANEL_HEIGHT = 200;

    private NetMatrixTable plusTable;
    private NetMatrixTable minusTable;
    private NetMatrixTable generalTable;

    private NetMatrixPlusModel tablePlusModel;
    private NetMatrixMinusModel tableMinusModel;
    private NetMatrixGeneralModel tableGeneralModel;

    public NetMatrixPanel(GraphService graphService) {

        this.tablePlusModel = new NetMatrixPlusModel(graphService);
        this.tableMinusModel= new NetMatrixMinusModel(graphService);
        this.tableGeneralModel = new NetMatrixGeneralModel(graphService);
        this.plusTable = new NetMatrixTable(tablePlusModel);
        this.minusTable = new NetMatrixTable(tableMinusModel);
        this.generalTable = new NetMatrixTable(tableGeneralModel);
        initNetMatrixTable();

    }

    private void initNetMatrixTable() {

        /*setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(200, 500));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(this);
        add(scrollPane);*/

        initNetMatrixTable(tablePlusModel, PANEL_PLUS_TITLE);
        initNetMatrixTable(tableMinusModel, PANEL_MINUS_TITLE);
        initNetMatrixTable(tableGeneralModel, PANEL_TITLE);
        updateTable();
    }
    private void initNetMatrixTable(NetMatrixModel model, String title){
        JPanel panel = new JPanel( );//panel.setTable(new NetMatrixTable(tableModel));
        panel.setPreferredSize(new Dimension(200, 150));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), title));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPane = new JScrollPane(new NetMatrixTable(model));
        scrollPane.setPreferredSize(new Dimension(2000, 150));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane );
        add(panel);
    }

    public void updateTable() {
        tablePlusModel.fireTableStructureChanged();
        tableGeneralModel.fireTableStructureChanged();
        tableMinusModel.fireTableStructureChanged();
    }


    public class NetMatrixTable extends JTable {

        private final DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        private final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

        public NetMatrixTable(NetMatrixModel tableModel) {
            super(tableModel);
            setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            getTableHeader().setReorderingAllowed(false);
            setFont(FONT);
            getTableHeader().setFont(FONT);
            setRowHeight(0, 20);
            //setRowSelectionAllowed(false);
            //setCellSelectionEnabled(true);
            defaultTableCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        }

        @Override
        public void tableChanged(TableModelEvent e) {
            super.tableChanged(e);

            Enumeration<TableColumn> columns = getColumnModel().getColumns();
            while (columns.hasMoreElements()) {
                TableColumn tableColumn = columns.nextElement();
                tableColumn.setCellRenderer(defaultTableCellRenderer);
                tableColumn.setMaxWidth(20);
            }
        }
    }
}
