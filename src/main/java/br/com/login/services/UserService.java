package br.com.login.services;

import br.com.login.entities.User;
import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.FindUserException;

public interface UserService {
    User saveUser(User newUser) throws CreateUserException;

    User findUserByLogin(String login) throws FindUserException;
}
