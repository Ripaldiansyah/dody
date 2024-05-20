package ac.id.unindra.dody_spk;

import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Font;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.util.UIScale;

import ac.id.unindra.dody_spk.aboutApp.view.AboutAppView;
import ac.id.unindra.dody_spk.alternativeInput.view.AlternativeInputView;
import ac.id.unindra.dody_spk.login.view.LoginView;
import ac.id.unindra.dody_spk.menu.view.Menu;

public class Main extends JFrame {
	public static Main main;

	public Main() {
		initUI();
	}

	private void initUI() {
		configureFrame();
		setContentPane(new LoginView());

	}

	private void configureFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(UIScale.scale(new Dimension(1366, 768)));
		setLocationRelativeTo(null);

	}

	public void login() {
		changeContentPane(new LoginView());
	}

	public void showMainForm() {
		setContentPane(new Menu());
		Menu.contentPanel.add(new AboutAppView());
		refreshUI();
	}

	private void changeContentPane(JPanel newPanel) {
		setContentPane(newPanel);
		refreshUI();
	}

	private void refreshUI() {
		revalidate();
		repaint();
	}

	private static void configureApplication() {
		FlatRobotoFont.install();
		Locale.setDefault(new Locale("id"));
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatLaf.registerCustomDefaultsSource("themes");
		FlatIntelliJLaf.setup();

	}

	public static void main(String[] args) {
		configureApplication();
		java.awt.EventQueue.invokeLater(() -> {
			main = new Main();
			main.setVisible(true);
		});
	}
}
