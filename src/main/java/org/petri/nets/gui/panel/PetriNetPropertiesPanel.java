package org.petri.nets.gui.panel;

import org.petri.nets.model.PetriNetProperties;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomasz on 2015-06-14.
 */
public class PetriNetPropertiesPanel extends JPanel {
    private static final String PANEL_TITLE = "Właściwości sieci";
    public static final String NET_SAFE_LABEL_STRING = "Sieć bezpieczna: ";
    public static final String NET_BOUNDED_LABEL_STRING = "Sieć ograniczona: ";
    public static final String NET_REVERSIBLE_LABEL_STRING = "Sieć odwracalna: ";
    public static final String NET_LIVE_LABEL_STRING = "Sieć żywa: ";
    public static final String NET_CONSERVATIVE_LABEL_STRING = "Sieć zachowawcza: ";
    public static final String NET_KBOUNDEDNESS_LABEL_STRING = "Wektor k-ograniczoności przejść:";
    public static final String YES = "TAK";
    public static final String NO = "NIE";

    private final JLabel netSafeLabel;
    private final JLabel netBoundedLabel;
    private final JLabel netReversibleLabel;
    private final JLabel netLiveLabel;
    private final JLabel netConservativeLabel;
    private final JLabel placecKBoundednessLabel;
    private final JLabel placecKBoundednessVector;


    public PetriNetPropertiesPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), PANEL_TITLE));
        netSafeLabel = new JLabel(NET_SAFE_LABEL_STRING + NO);
        netBoundedLabel = new JLabel(NET_BOUNDED_LABEL_STRING + NO);
        netReversibleLabel = new JLabel(NET_REVERSIBLE_LABEL_STRING + NO);
        netLiveLabel = new JLabel(NET_LIVE_LABEL_STRING + NO);
        netConservativeLabel = new JLabel(NET_CONSERVATIVE_LABEL_STRING + NO);
        placecKBoundednessLabel = new JLabel(NET_KBOUNDEDNESS_LABEL_STRING + NO);
        placecKBoundednessVector = new JLabel("[]");

        netSafeLabel.setMinimumSize(new Dimension(150,1));
        netBoundedLabel.setMinimumSize(new Dimension(150,1));
        netReversibleLabel.setMinimumSize(new Dimension(150,1));
        netSafeLabel.setMinimumSize(new Dimension(150,1));
        netSafeLabel.setMinimumSize(new Dimension(150,1));

        add(netSafeLabel);
        add(netBoundedLabel);
        add(netReversibleLabel);
        add(netLiveLabel);
        add(netConservativeLabel);
        add(placecKBoundednessLabel);
    }

    public void updateProperties(PetriNetProperties properties) {
        netSafeLabel.setText(NET_SAFE_LABEL_STRING + (properties.isPetriNetSafe() ? YES : NO));
        netBoundedLabel.setText(NET_BOUNDED_LABEL_STRING + (properties.isPetriNetBounded() ? YES : NO));
        netReversibleLabel.setText(NET_REVERSIBLE_LABEL_STRING + (properties.isPetriNetReversible() ? YES : NO));
        netLiveLabel.setText(NET_LIVE_LABEL_STRING + (properties.isPetriNetLive() ? YES : NO));
        netConservativeLabel.setText(NET_CONSERVATIVE_LABEL_STRING + (properties.isPetriNetConservative() ? YES : NO));

        StringBuilder sb = new StringBuilder("[");
        properties.getkBoundedness().forEach((placeId, kBoundedness) -> sb.append(kBoundedness).append(", "));
        if (sb.length() > 1)
            sb.setLength(sb.length() - 1);
        sb.append("]");
        placecKBoundednessVector.setText(sb.toString());

        repaint();
    }
}
