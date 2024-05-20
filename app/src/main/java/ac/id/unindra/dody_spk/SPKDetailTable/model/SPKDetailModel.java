package ac.id.unindra.dody_spk.SPKDetailTable.model;

import ac.id.unindra.dody_spk.login.model.LoginModel;

public class SPKDetailModel {
    String SPKId;
    String SPKName;
    LoginModel user;
    String createdAt;
    private final String[] columnHeader = {
            "ID SPK",
            "Nama SPK",
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

    public LoginModel getUser() {
        return user;
    }

    public void setUser(LoginModel user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
