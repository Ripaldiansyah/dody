package ac.id.unindra.dody_spk.report.controller;

import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.DatabaseConnection;

import java.util.Locale;

public class ReportController {

	public ReportController() {
		conn = new DatabaseConnection().connect();
	}

	public void ReportById(int id) {

		try {
			String file = "app/src/main/java/ac/id/unindra/dody_spk/report/jasper/ReportSPKById.jasper";
			HashMap<String, Object> parameters = new HashMap<>();
			parameters.put("id_spk", id);
			parameters.put(JRParameter.REPORT_LOCALE, new Locale("id"));

			JasperPrint print = JasperFillManager.fillReport(file, parameters, conn);
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage());
		}
	}

	private Connection conn;

}
