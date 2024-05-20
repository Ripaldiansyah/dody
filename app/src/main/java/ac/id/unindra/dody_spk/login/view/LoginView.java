package ac.id.unindra.dody_spk.login.view;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.dody_spk.Main;
import ac.id.unindra.dody_spk.login.controller.LoginController;
import ac.id.unindra.dody_spk.login.model.LoginModel;
import ac.id.unindra.dody_spk.register.view.RegisterView;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;
import utils.MD5;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LoginView extends JPanel {

        public LoginView() {
                init();
                registerButton();
        }

        boolean fieldValidation() {
                if (txtUsername.getText().trim().isEmpty()) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Username tidak boleh Kosong");
                        return false;
                } else if (txtPassword.getText().trim().isEmpty()) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Password tidak boleh Kosong");
                        return false;
                }
                return true;
        }

        boolean isRegistered() {
                getInput();
                if (fieldValidation()) {
                        if (!controller.isRegistered(model)) {
                                Notifications.getInstance().show(Notifications.Type.ERROR,
                                                "Akun tidak sesuai atau tidak terdaftar");
                                return false;
                        }
                } else {
                        return false;
                }
                return true;
        }

        void getInput() {
                String username = txtUsername.getText().toLowerCase();
                String password = MD5.getMD5(txtPassword.getText().toLowerCase());
                model.setUsername(username);
                model.setPassword(password);
        }

        private void init() {
                setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
                txtUsername = new JTextField();
                txtPassword = new JPasswordField();
                cmdLogin = new JButton("Login");

                panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
                panel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "[light]background:darken(@background,3%);"
                                + "[dark]background:lighten(@background,3%)");

                txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                                + "showRevealButton:true");

                cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""

                                + "margin:4,6,4,6;"
                                + "borderWidth:0;"
                                + "focusWidth:0;"
                                + "innerFocusWidth:0");

                cmdLogin.addActionListener((e) -> {
                        if (isRegistered()) {
                                Main.main.showMainForm();

                        }

                });
                txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukan username Anda!");
                txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukan Password Anda!");

                JLabel lbTitle = new JLabel("Selamat Datang!");
                JLabel description = new JLabel("Silakan masuk untuk mengakses akun Anda");

                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
                description.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:lighten(@foreground,30%);"
                                + "[dark]foreground:darken(@foreground,30%)");

                panel.add(lbTitle);
                panel.add(description);
                panel.add(new JLabel("Username"), "gapy 8");
                panel.add(txtUsername);
                panel.add(new JLabel("Password"), "gapy 8");
                panel.add(txtPassword);
                panel.add(cmdLogin, "gapy 15");
        }

        void registerButton() {
                cmdRegister = new JButton(
                                "<html><a href=\"#\" style=\"text-decoration: none;\"><b>Daftar Sekarang</b></a></html>");
                cmdRegister.setHorizontalAlignment(SwingConstants.LEFT);
                cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
                                "border:1,1,1,1");
                cmdRegister.setContentAreaFilled(false);
                cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cmdRegister.addActionListener((e) -> {
                        registerPage();
                });
                JLabel lbRegister = new JLabel("Belum punya akun, ");
                panel.add(lbRegister, "split 2 ,gapy 0 ");
                panel.add(cmdRegister, "gap 0, growx");
                add(panel);
        }

        void registerPage() {
                panel.setLayout(new MigLayout("wrap,fillx,insets 0", "[fill,500]"));
                panel.removeAll();
                removeAll();
                panel.add(new RegisterView());
                add(panel);
                controller.refreshPanel(panel);

        }

        JPanel panel;
        private JTextField txtUsername;
        private JPasswordField txtPassword;
        private JButton cmdLogin;
        private JButton cmdRegister;
        private LoginController controller = new LoginController();
        private LoginModel model = new LoginModel();

}
