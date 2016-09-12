package dao;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import model1.UserRole;
import model1.Users;

@Stateless
public class UserDAO implements UserDAOLocal {

    
    @PersistenceContext(unitName = "KPComputerShop-ejbPU")
    private EntityManager em;
    
    @Override
    public List<Users> getAllUsers() throws Exception {
        Query query = em.createQuery("SELECT u FROM Users u", Users.class);
        return query.getResultList();
    }
    @Override
    public void addNewUser(String userName, String name, String surName, String patronymic, String password) throws Exception {
        Users user = new Users();
        user.setUsername(userName);
        user.setName(name);
        user.setSurname(surName);
        user.setPatronymic(patronymic);
        MessageDigest md = MessageDigest.getInstance("MD5");
        password = (new HexBinaryAdapter()).marshal(md.digest(password.getBytes(Charset.forName("UTF-8"))));
        user.setPassword(password);
        em.persist(user);
        em.flush();
        UserRole role = new UserRole();
        role.setUserName(userName);
        role.setRoleName("client");
        em.persist(role);
        em.flush();        
    }
    @Override
    public boolean changeRole(String userName, String role) throws Exception {
        Query query = em.createQuery("update UserRole u set u.roleName=?1 WHERE u.userName=?2", UserRole.class);
        query.setParameter(1, role);
        query.setParameter(2, userName);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteUser(String userName) throws Exception {
        em.remove(em.getReference(Users.class, userName));
        Query query = em.createQuery("delete from UserRole u WHERE u.userName=?1", UserRole.class);
        query.setParameter(1, userName);
        query.executeUpdate();
        return true;
    }
   
}
