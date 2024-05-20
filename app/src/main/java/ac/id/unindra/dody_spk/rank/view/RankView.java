package ac.id.unindra.dody_spk.rank.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.formdev.flatlaf.FlatClientProperties;
import ac.id.unindra.dody_spk.rank.controller.RankController;
import ac.id.unindra.dody_spk.rank.model.RankTableModel;
import ac.id.unindra.dody_spk.report.controller.ReportController;
import component.ButtonCustom;
import component.TableCustom;
import net.miginfocom.swing.MigLayout;
import utils.CurrentDateTime;

public class RankView extends JPanel {

    public RankView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new MigLayout("fill,wrap", "[center, fill]", "[center, fill]"));
        add(tablePanel());
    }

    JPanel tablePanel() {
        tablePanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "center, fill", "center"));
        tablePanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:40;"
                + "background:lighten(@accentColor,40%)");

        RankTableModel tableModel = new RankTableModel();
        tableModel.setData();
        TableCustom tableCustom = new TableCustom(tableModel, "Peringkat");
        if (!isSaved) {
            tablePanel.add(btnSave(), "w 150!, right, h 35!");
        } else {
            tablePanel.add(print(), "w 150!, right, h 35!");
        }
        tablePanel.add(tableCustom, "gapy 10");

        return tablePanel;

    }

    JButton print() {
        return btnPrint = new ButtonCustom("Print", null, (e) -> {
            ReportController reportController = new ReportController();
            reportController.ReportById(spkID);

        });
    }

    JButton btnSave() {
        btnSave = new ButtonCustom("Simpan", null, (e) -> {
            String nameSPK = JOptionPane.showInputDialog(null, "Masukkan Nama SPK", "Nama SPK",
                    JOptionPane.PLAIN_MESSAGE);

            if (nameSPK.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                model.setSPKName(nameSPK);
                model.setUserID(userID);
                model.setCurrentDateTime(CurrentDateTime.getDateTime());
                model.setRankListMap(rankListMap);

                controller.saveSPK(model);
                JOptionPane.showMessageDialog(null, "Berhasil disimpan", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                btnSave.setEnabled(false);
                btnSave.setVisible(false);
                tablePanel.add(print(), "w 150!, right, h 35!, gapy 15");
                repaint();
                revalidate();
            }
        });

        return btnSave;
    }

    public static List<Map<Object, Object>> rankListMap = new ArrayList<>();
    public static String userID;
    public static int spkID;

    JPanel tablePanel = new JPanel();
    ButtonCustom btnSave;
    ButtonCustom btnPrint;
    RankController controller = new RankController();
    RankTableModel model = new RankTableModel();
    public static boolean isSaved = false;
}
