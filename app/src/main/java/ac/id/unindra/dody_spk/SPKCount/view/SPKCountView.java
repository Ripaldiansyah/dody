package ac.id.unindra.dody_spk.SPKCount.view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.dody_spk.SPKCount.model.SPKCountModel;
import ac.id.unindra.dody_spk.SPKCount.util.SAWAlgorithm;
import ac.id.unindra.dody_spk.rank.view.RankView;
import net.miginfocom.swing.MigLayout;
import utils.IntFilter;

public class SPKCountView extends JPanel {

    public SPKCountView() {
        init();

    }

    private void init() {
        setLayout(new MigLayout("", "[grow, fill]", "[grow, fill]"));
        firstPanel.setLayout(new MigLayout("wrap,insets 35 45 30 45", "[fill]"));
        firstPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[dark]background:lighten(@background,100%)");
        firstPanel.add(alternativeListPanel(), "split 2, grow, top");
        firstPanel.add(criteriaListPanel(), "grow, top, gapx 20");
        firstPanel.add(countSPK(), "wrap, w 150! , gapy 20");
        add(firstPanel);

    }

    JPanel alternativeListPanel() {
        JPanel alternativeListPanel = new JPanel();
        alternativeListPanel.setLayout(new MigLayout("wrap"));
        alternativeListPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[dark]background:lighten(@background,100%)");
        JLabel header = new JLabel("Nama Alternatif");
        header.putClientProperty(FlatClientProperties.STYLE, "" +
                "font: +2");

        alternativeListPanel.add(header, "gapy 0 -5");
        for (String alternative : alternativeSelectedList) {
            JLabel lbAlternative = new JLabel(alternative);
            alternativeListPanel.add(lbAlternative, "gapy 17");
        }
        return alternativeListPanel;
    }

    JPanel criteriaListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("wrap, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[dark]background:lighten(@background,100%)");

        JPanel headerPanel = createHeaderPanel();
        panel.add(headerPanel, "wrap");

        for (int i = 0; i < alternativeSelectedList.size(); i++) {
            JPanel panelField = createPanelFieldForAlternative();
            panel.add(panelField, "wrap, gapy 10");
            panelList.add(panelField);
        }

        return panel;
    }

    private JPanel createHeaderPanel() {
        int index = 0;
        JPanel headerPanel = new JPanel(new MigLayout());
        headerPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:lighten(@accentColor,30%)");

        for (String criteria : criteriaSelectedList) {
            Map<Object, Object> criteriaType = new LinkedHashMap<>();
            criteriaType.put("criteriaName", criteria);
            model.setCriteriaName(criteria);
            criteriaType.put("criteriaType", criteriaTypeList.get(index));
            JLabel lbCriteria = new JLabel(criteria);
            lbCriteria.putClientProperty(FlatClientProperties.STYLE, "font: +2");
            headerPanel.add(lbCriteria, "w 150!");
            criteriaTypeMap.add(criteriaType);
            index++;
        }
        return headerPanel;
    }

    private JPanel createPanelFieldForAlternative() {
        JPanel panelField = new JPanel(new MigLayout("insets 0"));
        panelField.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:lighten(@background,100%)");
        for (int i = 0; i < criteriaSelectedList.size(); i++) {
            panelField.add(rating(), "w 150!");
        }
        return panelField;
    }

    JButton countSPK() {
        btnCount = new JButton("Hitung SPK");
        btnCount.putClientProperty(FlatClientProperties.STYLE, ""
                + "margin:4,6,4,6;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        btnCount.addActionListener(e -> {
            if (validasiComponent()) {

                getInput();
                SAWAlgorithm spk = new SAWAlgorithm(
                        inputList,
                        criteriaTypeMap);
                showRank();

            } else {
                JOptionPane.showMessageDialog(null, "Pastikan semua terisi", "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        return btnCount;

    }

    private void refreshUI() {
        repaint();
        revalidate();
    }

    boolean validasiComponent() {
        for (JPanel panel : panelList) {// jumlah alternatif
            for (Component comp : panel.getComponents()) {// jumlah kriteria
                if (comp instanceof JTextField) {
                    if (((JTextField) comp).getText().isEmpty()) {
                        return false;
                    }
                } else if (comp instanceof JComboBox) {
                    if (((JComboBox<?>) comp).getSelectedItem() == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void getInput() {
        int indexCriteria = 0;
        int indexAlternative = 0;
        for (JPanel panel : panelList) {// jumlah alternatif
            Map<Object, Object> input = new LinkedHashMap<>();
            input.put("alternativeName", alternativeSelectedList.get(indexAlternative));
            for (Component comp : panel.getComponents()) {// jumlah kriteria

                double value = Double.parseDouble(((JTextField) comp).getText());
                input.put(criteriaSelectedList.get(indexCriteria), value);
                indexCriteria++;
            }
            indexCriteria = 0;
            indexAlternative++;
            inputList.add(input);
        }
    }

    JTextField rating() {
        txtCost = new JTextField();
        txtCost.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukan bobot");
        // PlainDocument doc = (PlainDocument) txtCost.getDocument();
        // doc.setDocumentFilter(new IntFilter());
        return txtCost;
    }
    // JComboBox<Integer> rating() {
    // cbCriteriaWeight = new JComboBox<>(model.getCriteriaWeightModel());
    // cbCriteriaWeight.setSelectedItem(null);
    // return cbCriteriaWeight;
    // }

    public void showRank() {
        removeAll();
        RankView.isSaved = false;
        add(new RankView());
        refreshUI();
    }

    private JTextField txtCost;
    private JComboBox<Integer> cbCriteriaWeight;
    private JPanel firstPanel = new JPanel();
    JCheckBox cbCriteria;
    JCheckBox cbAlternative;
    public static List<String> criteriaSelectedList = new ArrayList<>();
    public static List<String> alternativeSelectedList = new ArrayList<>();
    private SPKCountModel model = new SPKCountModel();
    boolean isRefresh = true;
    JButton btnCount;
    List<JPanel> panelList = new ArrayList<>();
    List<Map<Object, Object>> inputList = new ArrayList<>();
    List<Map<Object, Object>> criteriaTypeMap = new ArrayList<>();
    List<String> criteriaTypeList = new ArrayList<>(
            List.of(
                    "cost",
                    "cost",
                    "benefit",
                    "benefit",
                    "benefit",
                    "benefit"));

}