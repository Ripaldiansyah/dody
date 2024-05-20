package ac.id.unindra.dody_spk.SPKDetailTable.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ac.id.unindra.dody_spk.SPKDetailTable.model.SPKDetailModel;
import ac.id.unindra.dody_spk.SPKDetailTable.service.SPKDetailService;
import ac.id.unindra.dody_spk.login.model.LoginModel;
import utils.DatabaseConnection;

public class SPKDetailTableDAO implements SPKDetailService {
	private Connection conn;

	public SPKDetailTableDAO() {
		conn = new DatabaseConnection().connect();
	}

	@Override
	public List<SPKDetailModel> getData() {

		PreparedStatement stat = null;
		List spk = new ArrayList();
		ResultSet rs = null;
		String sql = "SELECT spk.id_spk, spk.spk_name, user.full_name, spk.created_at " +
				"FROM spk_save spk INNER JOIN users user " +
				"ON spk.user_id = user.user_id";
		LoginModel user = new LoginModel();
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				SPKDetailModel model = new SPKDetailModel();
				model.setSPKId(rs.getString("id_spk"));
				model.setSPKName(rs.getString("spk_name"));
				user.setFullname(rs.getString("full_name"));
				model.setUser(user);
				model.setCreatedAt(rs.getString("created_at"));
				spk.add(model);

			}
			return spk;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	}

	@Override
	public void deleteData(SPKDetailModel detail) {
		PreparedStatement stat = null;
		String sqlSPKSave = "DELETE From spk_save WHERE id_spk=?";
		String sqlSPKRank = "DELETE From spk_rank WHERE id_spk=?";

		try {
			stat = conn.prepareStatement(sqlSPKSave);
			stat.setString(1, detail.getSPKId());
			stat.executeUpdate();

			stat = conn.prepareStatement(sqlSPKRank);
			stat.setString(1, detail.getSPKId());
			stat.executeUpdate();

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
	public List<SPKDetailModel> searchData(String key) {
		PreparedStatement stat = null;
		List spk = new ArrayList();
		ResultSet rs = null;
		String sql = "SELECT spk.id_spk, spk.spk_name, user.full_name, spk.created_at " +
				"FROM spk_save spk INNER JOIN users user " +
				"ON spk.user_id = user.user_id " +
				"WHERE spk.id_spk LIKE '%" + key + "%' " +
				"OR spk.spk_name LIKE '%" + key + "%' " +
				"OR user.full_name LIKE '%" + key + "%' " +
				"OR spk.created_at LIKE '%" + key + "%' ";

		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();

			while (rs.next()) {
				SPKDetailModel model = new SPKDetailModel();
				LoginModel user = new LoginModel();
				model.setSPKId(rs.getString("id_spk"));
				model.setSPKName(rs.getString("spk_name"));
				user.setFullname(rs.getString("full_name"));
				model.setUser(user);
				model.setCreatedAt(rs.getString("created_at"));
				spk.add(model);
			}

			return spk;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

				}

			}

		}

	}

	@Override
	public List<Map<Object, Object>> rankDetail(SPKDetailModel model) {
		PreparedStatement stat = null;
		List<Map<Object, Object>> rankListMap = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM spk_rank WHERE id_spk = ?; ";

		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, model.getSPKId());
			rs = stat.executeQuery();
			while (rs.next()) {
				String alternativeName = rs.getString("alternative_name");
				int rank = rs.getInt("alternative_rank");

				Map<Object, Object> rankMap = new LinkedHashMap<>();
				rankMap.put("alternativeName", alternativeName);
				rankMap.put("rank", rank);
				rankListMap.add(rankMap);
			}
			return rankListMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	}
}
