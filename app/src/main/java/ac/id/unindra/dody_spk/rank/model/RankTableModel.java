package ac.id.unindra.dody_spk.rank.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import ac.id.unindra.dody_spk.alternativeInput.controller.AlternativeInputController;
import ac.id.unindra.dody_spk.alternativeInput.model.AlternativeInputModel;

public class RankTableModel extends AbstractTableModel {

    AlternativeInputController controller = new AlternativeInputController();
    List<Map<Object, Object>> rankListMap = new ArrayList<>();
    String SPKName;
    String userID;
    public static AlternativeInputModel model;
    String currentDateTime;

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String author) {
        this.userID = author;
    }

    public List<Map<Object, Object>> getRankListMap() {
        return rankListMap;
    }

    public void setRankListMap(List<Map<Object, Object>> rankListMap) {
        this.rankListMap = rankListMap;
    }

    @Override
    public int getRowCount() {
        return controller.getRowCount(model);
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Map<Object, Object> rowData = rankListMap.get(rowIndex);
        Object[] keys = rowData.keySet().toArray();
        Object key = keys[columnIndex];
        return rowData.get(key);

    }

    @Override
    public String getColumnName(int column) {
        String[] header = {
                "Nama Saham",
                "Peringkat"
        };
        return header[column];
    }

    public void setData() {
        rankListMap.addAll(model.getRankListMap());
        fireTableDataChanged();
    }

    public String getSPKName() {
        return SPKName;
    }

    public void setSPKName(String sPKName) {
        SPKName = sPKName;
    }

}
