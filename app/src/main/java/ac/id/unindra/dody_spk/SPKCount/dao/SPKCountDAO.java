package ac.id.unindra.dody_spk.SPKCount.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.id.unindra.dody_spk.SPKCount.model.SPKCountModel;
import ac.id.unindra.dody_spk.SPKCount.service.SPKCountService;
import utils.DatabaseConnection;

public class SPKCountDAO implements SPKCountService {
    public SPKCountDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean isNotRegistered(SPKCountModel criteria) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS count FROM criteria WHERE LOWER(criteria_name) = LOWER(?)";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getSPKName());
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
            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public void addCriteria(SPKCountModel criteria) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO criteria (criteria_name, criteria_type, criteria_weight) VALUES (?,?,?)";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getSPKName());

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

    private Connection conn;

    @Override
    public List<String> getCriteria() {
        PreparedStatement stat = null;
        List<String> criteria = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM criteria ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                String value = rs.getString("criteria_name");
                criteria.add(value);
            }
            return criteria;
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
    public List<String> getAlternative() {
        PreparedStatement stat = null;
        List<String> alternative = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM alternative ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                String value = rs.getString("alternative_name");
                alternative.add(value);
            }
            return alternative;
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
    public String getCriteriaType(SPKCountModel criteria) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT criteria_type FROM criteria WHERE criteria_name = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getCriteriaName());
            rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getString("criteria_type");
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
        return null;
    }

    @Override
    public int getCriteriaWeight(SPKCountModel criteria) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT criteria_weight FROM criteria WHERE criteria_name = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getCriteriaName());
            rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getInt("criteria_weight");
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
        return 0;

    }
}
