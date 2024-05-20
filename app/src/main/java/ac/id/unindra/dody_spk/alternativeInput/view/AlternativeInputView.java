package ac.id.unindra.dody_spk.alternativeInput.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatTextField;

import ac.id.unindra.dody_spk.SPKCount.view.SPKCountView;
import ac.id.unindra.dody_spk.alternativeInput.model.AlternativeInputModel;
import ac.id.unindra.dody_spk.alternativeInput.model.TableModelSaham;
import component.IconCustom;
import component.TableCustom;
import component.ButtonCustom;
import net.miginfocom.swing.MigLayout;
import java.util.ArrayList;
import java.util.List;

public class AlternativeInputView extends JPanel {

    public AlternativeInputView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new MigLayout("fill", "[center, fill]", "[center, fill]"));
        add(field());
        add(tablePanel());
        disableBtn();
    }

    JPanel field() {
        TableModelSaham.model = model;

        fieldPanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "center, fill", "center"));

        final FlatTextField txtSaham = textField("Masukan Nama Saham");
        fieldPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:40;"
                + "background:lighten(@accentColor,100%)");
        IconCustom addIcon = new IconCustom("images/add.svg", 1f, false);

        ButtonCustom btnAdd = new ButtonCustom("Tambah", addIcon.getIcon(), (e) -> {
            if (txtSaham.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama Saham tidak boleh kosong", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                alternativeInput.add(txtSaham.getText().toString());
                model.setAlternativeInput(alternativeInput);
                count++;
                model.setRowCount(count);
                refreshUI();

            }
        });

        fieldPanel.add(label("Masukan Nama Saham", 4, "bold"));
        fieldPanel.add(txtSaham);
        fieldPanel.add(btnAdd, "gapy 10");
        return fieldPanel;

    }

    JPanel tablePanel() {
        tablePanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "center, fill", "center"));

        tablePanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:40;"
                + "background:lighten(@accentColor,40%)");

        TableModelSaham tableModel = new TableModelSaham();
        tableModel.setData();
        TableCustom tableCustom = new TableCustom(tableModel, "Saham yang dibandingkan");

        IconCustom confirmIcon = new IconCustom("images/confirm.svg", 1f, false);
        ButtonCustom btnConfirm = new ButtonCustom("Konfirmasi", confirmIcon.getIcon(), (e) -> {
            SPKCountView.alternativeSelectedList = alternativeInput;
            SPKCountView.criteriaSelectedList = criteriaList;

            removeAll();
            add(new SPKCountView());
            repaint();
            revalidate();

        });
        tablePanel.add(tableCustom, "gapy 10");
        btnDelete.setBackground(Color.RED);

        if (model.getRowCount() > 0) {
            tablePanel.add(btnConfirm, "gapy 10");
            tableCustom.getTable().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    index = tableCustom.getSelectedRow();
                    showBtn();

                }
            });
        }
        tablePanel.add(btnDelete, "gapy 10");
        return tablePanel;

    }

    JButton btnDelete() {
        IconCustom deleteIcon = new IconCustom("images/delete.svg", 1f, false);
        return new ButtonCustom("Hapus", deleteIcon.getIcon(), (e) -> {
            alternativeInput.remove(index);
            model.setAlternativeInput(alternativeInput);
            count--;
            model.setRowCount(count);
            refreshUI();
        });
    }

    void disableBtn() {
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
    }

    void showBtn() {
        btnDelete.setEnabled(true);
        btnDelete.setVisible(true);
    }

    private void refreshUI() {
        removeAll();
        initComponents();
        repaint();
        revalidate();
        disableBtn();
    }

    FlatTextField textField(String placeHolderText) {
        FlatTextField textField = new FlatTextField();
        textField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeHolderText);
        textField.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 40));

        return textField;

    }

    JLabel label(String title, int fontSize, String typeFont) {
        JLabel lbTitle = new JLabel(title);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:" + typeFont + " +" + fontSize);

        return lbTitle;
    }

    JPanel tablePanel = new JPanel();
    JPanel fieldPanel = new JPanel();
    JButton btnDelete = btnDelete();
    AlternativeInputModel model = new AlternativeInputModel();
    List<String> alternativeInput = new ArrayList<>();
    List<String> criteriaList = new ArrayList<>(
            List.of(
                    "PER",
                    "PBV",
                    "ROE",
                    "EPS",
                    "DPR",
                    "DY"));
    int count;
    int index;
}
