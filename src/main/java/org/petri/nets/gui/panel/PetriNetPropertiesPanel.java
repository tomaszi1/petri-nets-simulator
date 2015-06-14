package org.petri.nets.gui.panel;

import org.petri.nets.model.PetriNetElement;
import org.petri.nets.model.PetriNetProperties;
import org.petri.nets.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Tomasz on 2015-06-14.
 */
public class PetriNetPropertiesPanel extends JPanel {
    private static final String PANEL_TITLE = "Właściwości sieci";
    public static final String SAFE_LABEL_STRING = "Sieć bezpieczna: ";
    public static final String BOUNDED_LABEL_STRING = "Sieć ograniczona: ";
    public static final String REVERSIBLE_LABEL_STRING = "Sieć odwracalna: ";
    public static final String LIVE_LABEL_STRING = "Sieć żywa: ";
    public static final String CONSERVATIVE_LABEL_STRING = "Sieć zachowawcza: ";
    public static final String NET_KBOUNDEDNESS_LABEL_STRING = "Wektor k-ograniczoności przejść:";
    public static final String YES = "TAK";
    public static final String NO = "NIE";
    private static final String TRANSITION_LIVENESS_STRING = "Przejścia L1-żywotne:";

    private final JLabel safeLabel;
    private final JLabel boundedLabel;
    private final JLabel reversibleLabel;
    private final JLabel liveLabel;
    private final JLabel conservativeLabel;
    private final JLabel placecKBoundednessLabel;
    private final JLabel placecKBoundednessVector;
    private final JLabel transitionLivenessLabel;
    private final JLabel transitionLivenessVector;


    public PetriNetPropertiesPanel() {
        setLayout(new GridLayout(10, 1));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), PANEL_TITLE));
        safeLabel = new JLabel(SAFE_LABEL_STRING);
        boundedLabel = new JLabel(BOUNDED_LABEL_STRING);
        reversibleLabel = new JLabel(REVERSIBLE_LABEL_STRING);
        liveLabel = new JLabel(LIVE_LABEL_STRING);
        conservativeLabel = new JLabel(CONSERVATIVE_LABEL_STRING);

        placecKBoundednessLabel = new JLabel(NET_KBOUNDEDNESS_LABEL_STRING);
        placecKBoundednessVector = new JLabel("[]");

        transitionLivenessLabel = new JLabel(TRANSITION_LIVENESS_STRING);
        transitionLivenessVector = new JLabel("[]");
      /*  safeLabel.setMinimumSize(new Dimension(150,1));
        boundedLabel.setMinimumSize(new Dimension(150,1));
        reversibleLabel.setMinimumSize(new Dimension(150,1));
        safeLabel.setMinimumSize(new Dimension(150,1));
        safeLabel.setMinimumSize(new Dimension(150,1));*/

        add(safeLabel);
        add(boundedLabel);
        add(reversibleLabel);
        add(liveLabel);
        add(conservativeLabel);
        add(placecKBoundednessLabel);
        add(placecKBoundednessVector);
        add(transitionLivenessLabel);
        add(transitionLivenessVector);
    }

    public void updateProperties(PetriNetProperties properties) {
        safeLabel.setText(SAFE_LABEL_STRING + (properties.isPetriNetSafe() ? YES : NO));
        boundedLabel.setText(BOUNDED_LABEL_STRING + (properties.isPetriNetBounded() ? YES : NO));
        reversibleLabel.setText(REVERSIBLE_LABEL_STRING + (properties.isPetriNetReversible() ? YES : NO));
        liveLabel.setText(LIVE_LABEL_STRING + (properties.isPetriNetLive() ? YES : NO));
        conservativeLabel.setText(CONSERVATIVE_LABEL_STRING + (properties.isPetriNetConservative() ? YES : NO));

        placecKBoundednessVector.setText(toString(properties.getkBoundedness().values(), null));
        placecKBoundednessVector.setToolTipText(wrapHtml(buildTooltip(properties.getkBoundedness())));

        Set<Integer> transIds = properties.getTransitionLiveness().stream().map(PetriNetElement::getId).collect(Collectors.toSet());
        String vectorString = toString(transIds, "T");
        transitionLivenessVector.setText(vectorString);
        transitionLivenessVector.setToolTipText(wrapHtml(vectorString));
    }

    private String toString(Collection<Integer> collection, String prefix) {
        StringBuilder sb = new StringBuilder("[");
        collection.forEach(kBoundedness -> {
            if (prefix != null)
                sb.append(prefix);
            sb.append(kBoundedness==-1 ? Utils.INF_SIGN : kBoundedness)
                    .append(", ");
        });
        if (sb.length() > 1)
            sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    private String buildTooltip(Map<Integer, Integer> map) {
        StringBuilder sb = new StringBuilder();
        map.forEach((transId, kBoundedness) -> {
            sb.append("P")
                    .append(transId)
                    .append("=")
                    .append(kBoundedness==-1 ? Utils.INF_SIGN : kBoundedness)
                    .append("<br>");
        });
        return sb.toString();
    }

    private String wrapHtml(String text){
        return "<html><font size=+1>" + text + "</font></html>";
    }
}
