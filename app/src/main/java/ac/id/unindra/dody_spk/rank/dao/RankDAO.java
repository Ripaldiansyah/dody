package ac.id.unindra.dody_spk.rank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ac.id.unindra.dody_spk.rank.model.RankTableModel;
import ac.id.unindra.dody_spk.rank.service.RankService;
import ac.id.unindra.dody_spk.rank.view.RankView;
import utils.DatabaseConnection;

public class RankDAO implements RankService {

    public RankDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public void saveSPK(RankTableModel model) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO spk_save (spk_name, user_id, created_at) VALUES (?,?,?)";
        try {
            stat = conn.prepareStatement(sql, new String[] { "id_spk" });
            stat.setString(1, model.getSPKName());
            stat.setString(2, model.getUserID());
            stat.setString(3, model.getCurrentDateTime());
            stat.executeUpdate();

            ResultSet generatedKeys = stat.getGeneratedKeys();

            if (generatedKeys.next()) {
                SPKid = generatedKeys.getInt(1);
                saveRankSPK(model);
            }

            RankView.spkID = SPKid;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveRankSPK(RankTableModel model) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO spk_rank (id_spk, alternative_name, alternative_rank) VALUES (?,?,?)";
        try {
            stat = conn.prepareStatement(sql);

            for (Map<Object, Object> rank : model.getRankListMap()) {

                stat.setInt(1, SPKid);
                stat.setString(2, rank.get("alternativeName").toString());
                stat.setString(3, rank.get("rank").toString());
                stat.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

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
    int SPKid;
}
