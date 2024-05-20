package ac.id.unindra.dody_spk.aboutApp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.swing.AvatarIcon;

public class AboutAppView extends JPanel {

        public AboutAppView() {
                init();
        }

        void init() {
                setLayout(new MigLayout("fill", "[center]", "[center]"));
                putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20");
                firstPanel.setLayout(new MigLayout("wrap,fillx", "[center]", "[center, fill]"));
                JPanel panelIdentity = new JPanel(new MigLayout("wrap"));
                AvatarIcon unindraIcon = new AvatarIcon(getClass().getResource("/images/unindra_logo.png"), 210,
                                205,
                                0);

                firstPanel.add(label("SISTEM PENDUKUNG KEPUTUSAN (SPK)", 12, "bold"));
                firstPanel.add(label("REKOMENDASI EMITEN SAHAM MENGGUNAKAN METODE SAW", 12, "bold"));
                firstPanel.add(label("PADA BURSA EFEK INDONESIA (BEI)", 12, "bold"));
                firstPanel.add(new JLabel(unindraIcon), "gap 30");
                panelIdentity.add(label("NAMA : ", 8, "semibold"), "split 2");
                panelIdentity.add(label("VIRGIAWAN DODY PASCAL", 8, "semibold"));
                panelIdentity.add(label("NPM : ", 8, "semibold"), "split 2, gapy 10");
                panelIdentity.add(label("202043500309", 8, "semibold"), "gapy 10");
                firstPanel.add(panelIdentity, "gapy 30");
                add(firstPanel);
        }

        JLabel label(String title, int fontSize, String typeFont) {
                JLabel lbTitle = new JLabel(title);
                lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                                "font:" + typeFont + " +" + fontSize);

                return lbTitle;
        }

        private JPanel firstPanel = new JPanel();

}
