package ac.id.unindra.dody_spk.login.service;

import ac.id.unindra.dody_spk.login.model.LoginModel;

public interface LoginService {

    boolean isRegistered(LoginModel login);
}