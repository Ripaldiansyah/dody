package ac.id.unindra.dody_spk.menu.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.components.FlatPopupMenu;

import ac.id.unindra.dody_spk.SPKDetailTable.view.SPKDetailTableView;
import ac.id.unindra.dody_spk.aboutApp.view.AboutAppView;
import ac.id.unindra.dody_spk.alternativeInput.view.AlternativeInputView;
import ac.id.unindra.dody_spk.login.view.LoginView;
import component.IconCustom;
import component.ButtonCustom;
import net.miginfocom.swing.MigLayout;

public class Menu extends JPanel {

    public Menu() {
        init();

    }

    public void refreshUI() {
        revalidate();
        repaint();
    }

    private void init() {
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        menu.add(menuBtnPanel());
        scrollPane.setBorder(null);
        setLayout(new MigLayout("fill, wrap", "[left, fill]", "[top, fill]"));
        contentPanel.setLayout(new MigLayout("fill", "[center, fill]", "[center, fill]"));

        mainPanel.setLayout(new MigLayout("wrap 1,fill", "[fill]", "fill"));

        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@accentColor,30%)");
        contentPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20");
        mainPanel.add(contentPanel, "grow");

        add(menuPanel());
        add(scrollPane, "grow, push");

    }

    JPanel menuPanel() {
        JPanel menuPanel = new JPanel();
        JPanel btnPanel = new JPanel(new MigLayout("fillx, insets 10 2 2 10"));
        menuPanel.setLayout(new MigLayout("fillx, insets 2 10 10 2"));
        menuPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@accentColor,20%)");
        btnPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@accentColor,20%)");

        btnPanel.add(btnMenu());
        menuPanel.add(btnPanel, "split 2, grow");
        menuPanel.add(titlePanel());

        return menuPanel;
    }

    JPanel titlePanel() {
        JPanel iconPanel = new JPanel(new MigLayout("fillx", "right, fill"));
        JPanel titlePanel = new JPanel(new MigLayout("wrap, fillx"));

        iconPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@accentColor,20%)");
        titlePanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@accentColor,20%)");

        IconCustom iconCompany = new IconCustom("images/report.svg", 3f, false);

        titlePanel.add(label("Sistem Pendukung Keputusan", 10, "bold"));
        titlePanel.add(label("Rekomendasi Emiten Saham", 10, "bold"));

        iconPanel.add(new JLabel(iconCompany.getIcon()));
        iconPanel.add(titlePanel);

        return iconPanel;
    }

    JButton btnMenu() {
        FlatSVGIcon icon = new FlatSVGIcon("images/menu.svg", 1f);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();

        colorFilter.add(Color.decode("#969696"), Color.decode("#ffffff"));
        icon.setColorFilter(colorFilter);
        btnMenu.setToolTipText("Menu");

        btnMenu.setIcon(icon);
        btnMenu.putClientProperty(FlatClientProperties.STYLE, ""

                + "margin:4,6,4,6;"
                + "arc:20;"
                + "borderWidth:0;"
                + "focusWidth:0;"

                + "innerFocusWidth:0");

        btnMenu.addActionListener((e) -> {
            menu.show(btnMenu, 0, btnMenu.getHeight());
        });
        return btnMenu;
    }

    JPanel menuBtnPanel() {
        JPanel menuPanel = new JPanel(new MigLayout("fillx,wrap", "[fill, 200]", "[center]"));
        menuPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@accentColor,100%)");

        IconCustom homeIcon = new IconCustom("images/home.svg", 1f, false);
        IconCustom reportIcon = new IconCustom("images/report_chart.svg", 1f, false);
        IconCustom calcIcon = new IconCustom("images/calc.svg", 1f, false);
        IconCustom logoutIcon = new IconCustom("images/logout.svg", 1f, false);

        ButtonCustom btnHome = new ButtonCustom("Beranda", homeIcon.getIcon(), (e) -> {
            changeContent(new AboutAppView());
            hideMenu();
        });
        ButtonCustom btnCalc = new ButtonCustom("Hitung", calcIcon.getIcon(), (e) -> {
            changeContent(new AlternativeInputView());
            hideMenu();
        });
        ButtonCustom btnReport = new ButtonCustom("Laporan", reportIcon.getIcon(), (e) -> {
            changeContent(new SPKDetailTableView());
            hideMenu();
        });
        ButtonCustom btnLogout = new ButtonCustom("Keluar", logoutIcon.getIcon(), (e) -> {
            logout(new LoginView());
            hideMenu();
        });

        menuPanel.add(btnHome, "gapy 10");
        menuPanel.add(btnCalc, "gapy 10");
        menuPanel.add(btnReport, "gapy 10");
        menuPanel.add(btnLogout, "gapy 10");

        return menuPanel;

    }

    public void changeContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        refreshUI();
    }

    void logout(JPanel panel) {
        contentPanel.removeAll();
        removeAll();
        add(panel);
        refreshUI();
    };

    public void hideMenu() {
        menu.setVisible(false);
    }

    JLabel label(String title, int fontSize, String typeFont) {
        JLabel lbTitle = new JLabel(title);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:" + typeFont + " +" + fontSize + ";"
                + "foreground : #ffffff");

        return lbTitle;
    }

    private JPanel mainPanel = new JPanel();
    public static JPanel contentPanel = new JPanel();
    JButton btnMenu = new JButton();
    FlatPopupMenu menu = new FlatPopupMenu();
}
