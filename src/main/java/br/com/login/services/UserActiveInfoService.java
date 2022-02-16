package br.com.login.services;

import br.com.login.exceptions.CreateActiveInfoException;

public interface UserActiveInfoService {

    void saveNewActiveInfo(String login) throws CreateActiveInfoException;
    void removeAccess(String login) throws CreateActiveInfoException;
}
