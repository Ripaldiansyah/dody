package ac.id.unindra.dody_spk.SPKCount.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import ac.id.unindra.dody_spk.SPKCount.controller.SPKCountController;
import ac.id.unindra.dody_spk.SPKCount.model.SPKCountModel;

public class SAWAlgorithmBackup {

    public SAWAlgorithmBackup() {
        isiDataDummy();

        getCriteriaWeight();
        getInterest();
        getDivider();
        normalizeMatrix();
        preference();
        rank();
        getHeader();
        getDividerListMap();
        setTable();

    }

    public static void main(String[] args) {
        SAWAlgorithmBackup saw = new SAWAlgorithmBackup();
    }
    // public SAWAlgorithmBackup(
    // List<Map<Object, Object>> inputList,
    // List<Map<Object, Object>> criteriaTypeMap) {
    // this.inputListMap = inputList;
    // this.criteriaTypeMap = criteriaTypeMap;

    // getCriteriaWeight();
    // getInterest();
    // getDivider();
    // normalizeMatrix();
    // preference();
    // rank();
    // getHeader();
    // getDividerListMap();
    // setTable();

    // }

    // SAW Proses ke-1
    void getCriteriaWeight() {
        int index = 0;
        for (Map<Object, Object> criteria : criteriaTypeMap) {

            String criteriaName = criteria.get("criteriaName").toString();
            model.setCriteriaName(criteriaName);
            criteriaWeightList.add(controller.getCriteriaWeight().get(index));
            index++;
        }

        sumCriteriaWeight = criteriaWeightList.stream().reduce(0.0, Double::sum);
    }

    // SAW Proses ke-2
    void getInterest() {
        for (double criteriaWeight : criteriaWeightList) {
            double interest = criteriaWeight / sumCriteriaWeight;
            interestList.add(interest);
        }
    }

    // SAW Proses ke-3
    void getDivider() {
        int after = 1;
        for (int i = 0; i < criteriaTypeMap.size(); i++) {
            List<Integer> dividerTemp = new ArrayList<>();

            for (Map<Object, Object> input : inputListMap) {
                int before = 0;
                for (Map.Entry<Object, Object> criteriaInput : input.entrySet()) {
                    if (before == after) {
                        int value = (Integer) criteriaInput.getValue();
                        dividerTemp.add(value);

                    }
                    before++;
                }
            }
            String criteriaType = criteriaTypeMap.get(i).get("criteriaType").toString();
            criteriaTypeList.add(criteriaType);
            boolean isBenefit = criteriaType.equalsIgnoreCase("benefit") ? true : false;
            int divider = 0;
            if (isBenefit) {
                divider = Collections.max(dividerTemp);
            } else {
                divider = Collections.min(dividerTemp);
            }
            dividerList.add(divider);
            after++;
        }
    }

    // SAW Proses ke-4
    void normalizeMatrix() {
        int after = 1;
        for (int i = 0; i < criteriaTypeMap.size(); i++) {
            for (Map<Object, Object> input : inputListMap) {
                boolean isCost = criteriaTypeList.get(i).equalsIgnoreCase("cost") ? true : false;
                int before = 0;
                for (Map.Entry<Object, Object> criteriaInput : input.entrySet()) {
                    if (before == after) {
                        int value = (Integer) criteriaInput.getValue();
                        double normalize;
                        if (isCost) {
                            normalize = dividerList.get(i) / (double) value;
                        } else {
                            normalize = (double) value / dividerList.get(i);
                        }
                        normalizeList.add(normalize);
                    }
                    before++;
                }
            }
            after++;
        }
    }

    // SAW Proses ke-5
    void preference() {
        for (int indexInput = 0; indexInput < inputListMap.size(); indexInput++) {
            double preferenceValue = 0;
            int index = indexInput;
            for (int indexCriteria = 0; indexCriteria < criteriaTypeMap.size(); indexCriteria++) {
                double value = (interestList.get(indexCriteria) * normalizeList.get(index));
                preferenceValue += value;
                index += inputListMap.size();
            }

            preferenceList.add(preferenceValue);

        }

    }

    void rank() {
        List<Map<Object, Object>> alternativeListMap = new ArrayList<>(inputListMap);
        List<Double> sortedList = new ArrayList<>(preferenceList);
        Collections.sort(sortedList, Collections.reverseOrder());

        for (int i = 0; i < preferenceList.size(); i++) {
            Map<Object, Object> rankMap = new LinkedHashMap<>();
            Map<Object, Object> rowData = alternativeListMap.get(i);
            rankMap.put("alternativeName", rowData.get("alternativeName"));
            int rank = sortedList.indexOf(preferenceList.get(i)) + 1;
            rankMap.put("rank", rank);

            rankListMap.add(rankMap);
        }

        System.out.println(rankListMap);

    }

    void getHeader() {
        Map<Object, Object> input = inputListMap.get(1);
        criteriaHeaderList.add("Nama Alternatif");
        int index = 0;
        for (Map.Entry<Object, Object> header : input.entrySet()) {
            if (index > 0) {
                criteriaHeaderList.add(header.getKey().toString());
            }
            index++;
        }
    }

    void getDividerListMap() {
        int index = 0;
        for (int i = 0; i < criteriaTypeMap.size(); i++) {
            Map<Object, Object> dividerMap = new LinkedHashMap<>();
            dividerMap.put("divider", dividerList.get(i));
            for (int indexInput = 0; indexInput < inputListMap.size(); indexInput++) {
                dividerMap.put(inputListMap.get(indexInput).get("alternativeName"), normalizeList.get(index));
                index++;
            }
            dividerListMap.add(dividerMap);
        }
    }

    /*
     * Set Table
     * Dibuat masing-masing model
     * Ubah Map ke Array String
     * 
     */

    // void initTable() {

    // detailModel.setColumnHeader(criteriaHeaderList.toArray(new String[0]));
    // detailModel.setRowCount(inputListMap.size());
    // // table ke-1
    // detailModel.setCriteriaType(criteriaTypeList);
    // detailModel.setCriteriaWeight(criteriaWeightList.stream().map(Object::toString).collect(Collectors.toList()));

    // // Table ke-2
    // detailModel.setDecisionMatrixListMap(inputListMap);

    // // table ke-3
    // detailModel.setNormalizeDecisionMatrix(dividerListMap);

    // // table ke-4
    // detailModel.setPreferenceList(preferenceList);

    // // table ke-5
    // detailModel.setRank(rankListMap);

    // TableModelDecisionMatrix.model = detailModel;
    // TableModelDecisionWeight.model = detailModel;
    // TableModelNormalizeDecisionMatrix.model = detailModel;
    // TableModelPreference.model = detailModel;
    // TableModelRank.model = detailModel;

    // }

    void setTable() {
        // initTable();
        // testingFrame();
    }

    void testingFrame() {

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Menambahkan baris
        frame.setSize(800, 600); // Atur lebar dan tinggi frame
        frame.setVisible(true);
    }

    public void isiDataDummy() {

        // Menambahkan map pertama ke dalam dataList1
        Map<Object, Object> data1 = new LinkedHashMap<>();
        data1.put("alternativeName", "BBCA");
        data1.put("per", 3);
        data1.put("pbv", 3);
        data1.put("roe", 2);
        data1.put("eps", 1);
        data1.put("dpr", 2);
        data1.put("dy", 1);
        inputListMap.add(data1);

        Map<Object, Object> data2 = new LinkedHashMap<>();
        data2.put("alternativeName", "BBNI");
        data2.put("per", 3);
        data2.put("pbv", 1);
        data2.put("roe", 1);
        data2.put("eps", 1);
        data2.put("dpr", 1);
        data2.put("dy", 1);
        inputListMap.add(data2);

        Map<Object, Object> data3 = new LinkedHashMap<>();
        data3.put("alternativeName", "BBRI");
        data3.put("per", 3.0);
        data3.put("pbv", 3);
        data3.put("roe", 1);
        data3.put("eps", 1);
        data3.put("dpr", 3);
        data3.put("dy", 1);
        inputListMap.add(data3);

        Map<Object, Object> data4 = new LinkedHashMap<>();
        data4.put("alternativeName", "BMRI");
        data4.put("per", 2);
        data4.put("pbv", 2);
        data4.put("roe", 1);
        data4.put("eps", 2);
        data4.put("dpr", 2);
        data4.put("dy", 1);
        inputListMap.add(data4);

        Map<Object, Object> data5 = new LinkedHashMap<>();
        data5.put("alternativeName", "MEGA");
        data5.put("per", 2);
        data5.put("pbv", 3);
        data5.put("roe", 2);
        data5.put("eps", 2);
        data5.put("dpr", 3);
        data5.put("dy", 1);
        inputListMap.add(data5);

        // List map kedua

        // Menambahkan map kedua ke dalam dataList2
        Map<Object, Object> criteria1 = new LinkedHashMap<>();
        criteria1.put("criteriaName", "per");
        criteria1.put("criteriaType", "Cost");
        criteriaTypeMap.add(criteria1);

        Map<Object, Object> criteria2 = new LinkedHashMap<>();
        criteria2.put("criteriaName", "pbv");
        criteria2.put("criteriaType", "Cost");
        criteriaTypeMap.add(criteria2);

        Map<Object, Object> criteria3 = new LinkedHashMap<>();
        criteria3.put("criteriaName", "roe");
        criteria3.put("criteriaType", "benefit");
        criteriaTypeMap.add(criteria3);

        Map<Object, Object> criteria4 = new LinkedHashMap<>();
        criteria4.put("criteriaName", "eps");
        criteria4.put("criteriaType", "benefit");
        criteriaTypeMap.add(criteria4);

        Map<Object, Object> criteria5 = new LinkedHashMap<>();
        criteria5.put("criteriaName", "dpr");
        criteria5.put("criteriaType", "benefit");
        criteriaTypeMap.add(criteria5);

        Map<Object, Object> criteria6 = new LinkedHashMap<>();
        criteria6.put("criteriaName", "dy");
        criteria6.put("criteriaType", "benefit");
        criteriaTypeMap.add(criteria6);
    }

    List<String> criteriaHeaderList = new ArrayList<>();
    public List<Map<Object, Object>> inputListMap = new ArrayList<>();
    public List<Map<Object, Object>> criteriaTypeMap = new ArrayList<>();
    List<Map<Object, Object>> dividerListMap = new ArrayList<>();
    List<Double> criteriaWeightList = new ArrayList<>();
    List<String> criteriaTypeList = new ArrayList<>();
    List<Double> interestList = new ArrayList<>();
    List<Integer> dividerList = new ArrayList<>();
    List<Double> normalizeList = new ArrayList<>();
    List<Double> preferenceList = new ArrayList<>();
    List<Map<Object, Object>> rankListMap = new ArrayList<>();
    double sumCriteriaWeight;

    private SPKCountController controller = new SPKCountController();
    private SPKCountModel model = new SPKCountModel();
}
