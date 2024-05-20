package ac.id.unindra.dody_spk.register.view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.dody_spk.Main;
import ac.id.unindra.dody_spk.register.component.PasswordStrengthStatus;
import ac.id.unindra.dody_spk.register.controller.RegisterController;
import ac.id.unindra.dody_spk.register.model.RegisterModel;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;
import utils.MD5;

public class RegisterView extends JPanel {

    public RegisterView() {
        init();

    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        firstPanel.setLayout(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtUsername = new JTextField();

        firstPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtFirstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Depan");
        txtLastName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Belakang");
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukan username");
        JLabel lbTitle = new JLabel("Form Pendaftaran");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");

        firstPanel.add(lbTitle);

        firstPanel.add(new JLabel("Nama Lengkap"), "gapy 10");
        firstPanel.add(txtFirstName, "split 2");
        firstPanel.add(txtLastName);
        firstPanel.add(new JLabel("Jenis Kelamin"), "gapy 8");
        firstPanel.add(createGenderPanel());
        firstPanel.add(new JSeparator(), "gapy 5 5");
        firstPanel.add(new JLabel("Username"));
        firstPanel.add(txtUsername, "split 2 ,growx");
        firstPanel.add(checkUsername());
        firstPanel.add(isUsernameAvailable());
        add(firstPanel);
    }

    private void successCreated() {
        firstPanel.removeAll();
        controller.refreshPanel(firstPanel);
        isVisible = false;
        setEnableComponent();
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        firstPanel.setLayout(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        firstPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");
        JLabel lbTitle = new JLabel("Akun berhasil dibuat");
        JLabel lbName = new JLabel("Nama");
        JLabel lbNameValue = new JLabel(": " + user.getFullname());
        JLabel lbGender = new JLabel("Jenis Kelamin");
        JLabel lbGenderValue = new JLabel(": " + user.getGender());
        JLabel lbUsername = new JLabel("Username");
        JLabel lbUsernameValue = new JLabel(": " + user.getUsername());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");

        lbName.setPreferredSize(new Dimension(150, 8));
        lbGender.setPreferredSize(new Dimension(150, 8));
        lbUsername.setPreferredSize(new Dimension(150, 8));

        firstPanel.add(lbTitle);
        firstPanel.add(new JSeparator(), "gapy 5 5");
        firstPanel.add(lbName, "split 2");
        firstPanel.add(lbNameValue, "growx");
        firstPanel.add(lbGender, "split 2");
        firstPanel.add(lbGenderValue, "growx");
        firstPanel.add(lbUsername, "split 2");
        firstPanel.add(lbUsernameValue, "growx");
        firstPanel.add(new JSeparator(), "gapy 5 5");
        firstPanel.add(btnBackToLogin(), "gapy 5");
        add(firstPanel);
    }

    JButton btnBackToLogin() {
        cmdLogin = new JButton("Kembali ke halaman Login");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background, 10%);foreground:darken(@foreground, 10%);" +
                "[dark]background:lighten(@background,10%);"
                + "margin:4,6,4,6;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        cmdLogin.addActionListener(e -> {
            Main.main.login();
        });
        return cmdLogin;

    }

    private Component isUsernameAvailable() {
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 0", "[fill,360]"));
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        cmdRegister = new JButton("Daftar");

        setEnableComponent();
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");

        cmdRegister.addActionListener(e -> {
            if (registerValidation()) {
                getInput();
                controller.registerUser(user);
                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        "Akun Berhasil dibuat");
                successCreated();
            }
        });
        passwordStrengthStatus = new PasswordStrengthStatus();

        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukan Kata Sandi");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukan Kata Sandi konfirmasi");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");

        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");

        cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:4,6,4,6;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");

        passwordStrengthStatus.initPasswordField(txtPassword);
        panel.add(new JLabel("Kata Sandi"), "gapy 8");
        panel.add(txtPassword);
        panel.add(passwordStrengthStatus, "gapy 0");
        panel.add(new JLabel("Kata Sandi konfirmasi"), "gapy 0");
        panel.add(txtConfirmPassword);
        panel.add(cmdRegister, "gapy 10");
        panel.add(btnBackToLogin(), "gapy 10");
        return panel;
    }

    void setEnableComponent() {
        Component[] component = {
                txtPassword,
                txtConfirmPassword,
                cmdRegister,
        };
        for (Component c : component) {
            c.setEnabled(isVisible);
        }

    }

    boolean registerValidation() {
        if (textFieldValidation()) {
            if (txtPassword.getText().trim().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Password tidak boleh kosong");
                return false;
            } else {
                if (!isMatchPassword()) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Passwords tidak sesuai");
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    boolean textFieldValidation() {
        JTextField[] component = {
                txtUsername,
                txtFirstName,
                txtLastName,
        };
        for (JTextField field : component) {
            if (field.getText().trim().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Mohon dipastikan semua telah terisi");
                return false;
            }
        }
        return true;
    }

    private Component createGenderPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        genderMale = new JRadioButton("Laki-Laki");
        genderFemale = new JRadioButton("Perempuan");
        groupGender = new ButtonGroup();
        groupGender.add(genderMale);
        groupGender.add(genderFemale);
        genderMale.setSelected(true);
        panel.add(genderMale);
        panel.add(genderFemale);
        return panel;
    }

    private Component checkUsername() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JButton cmdCheckUsername = new JButton("<html><a href=\"#\">Check Username</a></html>");
        cmdCheckUsername.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:1,1,1,1");
        cmdCheckUsername.setContentAreaFilled(false);
        cmdCheckUsername.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdCheckUsername.addActionListener(e -> {
            getInput();
            if (textFieldValidation()) {
                if (controller.checkUsername(user)) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Username dapat digunakan");
                    isVisible = !isVisible;
                    setEnableComponent();
                    txtUsername.setEnabled(false);
                    panel.remove(cmdCheckUsername);
                    controller.refreshPanel(firstPanel);
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Username telah terdaftar");
                }
            }

        });
        panel.add(cmdCheckUsername);
        return panel;
    }

    private void getInput() {
        String fullname = txtFirstName.getText() + " " + txtLastName.getText();
        String username = txtUsername.getText().toLowerCase();
        String gender = (genderMale.isSelected()) ? "Laki - Laki " : "Perempuan";
        String password = MD5.getMD5(txtPassword.getText());
        user.setFullname(fullname);
        user.setUsername(username);
        user.setGender(gender);
        user.setPassword(password);

    }

    public boolean isMatchPassword() {
        String password = String.valueOf(txtPassword.getPassword());
        String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
        return password.equals(confirmPassword);
    }

    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JRadioButton genderMale;
    private JRadioButton genderFemale;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private ButtonGroup groupGender;
    private JButton cmdRegister;
    private JButton cmdLogin;
    private PasswordStrengthStatus passwordStrengthStatus;
    private boolean isVisible = false;
    private JPanel firstPanel = new JPanel();
    private RegisterController controller = new RegisterController();
    private RegisterModel user = new RegisterModel();

}
