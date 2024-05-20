package ac.id.unindra.dody_spk.register.controller;

import javax.swing.JPanel;

import ac.id.unindra.dody_spk.register.dao.RegisterDAO;
import ac.id.unindra.dody_spk.register.model.RegisterModel;

public class RegisterController {

    private RegisterDAO user;

    public RegisterController() {
        this.user = new RegisterDAO();
    }

    public static int checkPasswordStrength(String password) {
        int score = 0;
        if (password.length() >= 8) {
            score++;
        }
        boolean hasUppercase = !password.equals(password.toLowerCase());
        if (hasUppercase) {
            score++;
        }
        boolean hasLowercase = !password.equals(password.toUpperCase());
        if (hasLowercase) {
            score++;
        }
        boolean hasDigit = password.matches(".*\\d.*");
        if (hasDigit) {
            score++;
        }
        boolean hasSpecialChar = !password.matches("[A-Za-z0-9]*");
        if (hasSpecialChar) {
            score++;
        }
        if (score < 3) {
            return 1;
        } else if (score < 5) {
            return 2;
        } else {
            return 3;
        }
    }

    public boolean checkUsername(RegisterModel model) {
        return user.checkUsername(model);
    }

    public void registerUser(RegisterModel model) {
        user.registerUser(model);
    }

    public void refreshPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();

    }

}
