package ac.id.unindra.dody_spk.register.service;

import ac.id.unindra.dody_spk.register.model.RegisterModel;

public interface RegisterService {

    boolean checkUsername(RegisterModel user);

    void registerUser(RegisterModel user);

}
