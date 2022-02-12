package br.com.login.services;

import br.com.login.entities.User;
import br.com.login.exceptions.CreateUserException;

public interface UserService {
    User saveUser(User newUser) throws CreateUserException;
}
