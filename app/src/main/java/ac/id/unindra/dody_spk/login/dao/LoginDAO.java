package ac.id.unindra.dody_spk.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ac.id.unindra.dody_spk.login.model.LoginModel;
import ac.id.unindra.dody_spk.login.service.LoginService;
import ac.id.unindra.dody_spk.rank.view.RankView;
import utils.DatabaseConnection;

public class LoginDAO implements LoginService {

    public LoginDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean isRegistered(LoginModel login) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE username = ? AND Password = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, login.getUsername());
            stat.setString(2, login.getPassword());
            rs = stat.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                String userId = rs.getString("user_id");
                RankView.userID = userId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private Connection conn;
}
