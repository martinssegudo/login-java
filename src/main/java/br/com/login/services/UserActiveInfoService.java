package br.com.login.services;

import br.com.login.entities.User;
import br.com.login.exceptions.CreateActiveInfoException;

public interface UserActiveInfoService {

    void saveNewActiveInfo(User user) throws CreateActiveInfoException;
    void removeAccess(String login) throws CreateActiveInfoException;
}
