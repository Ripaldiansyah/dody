package ac.id.unindra.dody_spk.SPKCount.model;

public class SPKCountModel {
    String SPKid;
    String SPKName;
    String criteriaName;
    final Integer[] criteriaWeightModel = {
            1,
            2,
            3,
            4,
            5
    };

    public Integer[] getCriteriaWeightModel() {
        return criteriaWeightModel;
    }

    public String getSPKid() {
        return SPKid;
    }

    public void setSPKid(String SPKid) {
        this.SPKid = SPKid;
    }

    public String getSPKName() {
        return SPKName;
    }

    public void setSPKName(String SPKName) {
        this.SPKName = SPKName;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

}
