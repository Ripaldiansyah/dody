package ac.id.unindra.dody_spk.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ac.id.unindra.dody_spk.register.model.RegisterModel;
import ac.id.unindra.dody_spk.register.service.RegisterService;
import utils.DatabaseConnection;

public class RegisterDAO implements RegisterService {

    public RegisterDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean checkUsername(RegisterModel user) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS count FROM users WHERE username = ?;";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, user.getUsername());
            rs = stat.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Menutup ResultSet, PreparedStatement, dan koneksi
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
        return false;
    }

    @Override
    public void registerUser(RegisterModel user) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO users (full_name, username, gender, password,role) VALUES (?,?,?,?,?)";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, user.getFullname());
            stat.setString(2, user.getUsername());
            stat.setString(3, user.getGender());
            stat.setString(4, user.getPassword());
            stat.setString(5, "user");
            stat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Menutup ResultSet, PreparedStatement, dan koneksi

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Connection conn;

}
