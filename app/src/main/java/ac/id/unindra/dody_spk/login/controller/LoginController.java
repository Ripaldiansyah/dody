package ac.id.unindra.dody_spk.login.controller;

import javax.swing.JPanel;

import ac.id.unindra.dody_spk.login.dao.LoginDAO;
import ac.id.unindra.dody_spk.login.model.LoginModel;

public class LoginController {

    private LoginDAO login;

    public LoginController() {
        this.login = new LoginDAO();
    }

    public boolean isRegistered(LoginModel model) {
        return login.isRegistered(model);
    }

    public void refreshPanel(JPanel panel) {
        panel.invalidate();
        panel.revalidate();
        panel.repaint();

    }
}
