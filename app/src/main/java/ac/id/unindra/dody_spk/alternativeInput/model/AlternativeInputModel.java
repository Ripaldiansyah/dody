package ac.id.unindra.dody_spk.alternativeInput.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlternativeInputModel {

    int rowCount;
    List<String> alternativeInput = new ArrayList<>();
    List<Map<Object, Object>> rankListMap = new ArrayList<>();

    public List<Map<Object, Object>> getRankListMap() {
        return rankListMap;
    }

    public void setRankListMap(List<Map<Object, Object>> rankListMap) {
        this.rankListMap = rankListMap;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<String> getAlternativeInput() {
        return alternativeInput;
    }

    public void setAlternativeInput(List<String> alternativeInput) {
        this.alternativeInput = alternativeInput;
    }

}
