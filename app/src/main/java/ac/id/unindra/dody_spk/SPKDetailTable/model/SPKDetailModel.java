package ac.id.unindra.dody_spk.SPKDetailTable.model;

import ac.id.unindra.dody_spk.login.model.LoginModel;

public class SPKDetailModel {
    String SPKId;
    String SPKName;
    String fullname;
    String createdAt;
    private final String[] columnHeader = {
            "ID SPK",
            "Nama SPK",
            "Nama Operator",
            "Tanggal perhitungan",
    };

    public String getSPKId() {
        return SPKId;
    }

    public void setSPKId(String sPKId) {
        SPKId = sPKId;
    }

    public String getSPKName() {
        return SPKName;
    }

    public void setSPKName(String sPKName) {
        SPKName = sPKName;
    }

    public String[] getColumnHeader() {
        return columnHeader;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
