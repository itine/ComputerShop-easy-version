package dao;

import java.util.List;
import javax.ejb.Local;
import model1.UserRole;
import model1.Users;

@Local
public interface UserDAOLocal {
    public void addNewUser(String userName, String name, String surName, String patronymic, String password) throws Exception;
    public boolean changeRole(String userName, String role) throws Exception;
    public boolean deleteUser(String userName) throws Exception;
    public List<Users> getAllUsers() throws Exception;
    
}
