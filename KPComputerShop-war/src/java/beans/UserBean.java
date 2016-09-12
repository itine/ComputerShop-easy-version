package beans;

import dao.UserDAOLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model1.Users;
import java.util.List;
import model1.UserRole;
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    UserDAOLocal userDAO;
    private String userRole;
    private String userName;
    private String name;
    private String surName;
    private String patronymic;
    private String password;
    private String pass2;
    private String error;
    private Users user;
    @PostConstruct
    public void initialize() {
        setUser(new Users());
    }
    
    public String toNewUser() throws Exception {
        return "/newUser.xhtml";
    }
    public List<Users> getUsers() throws Exception {
        return userDAO.getAllUsers();

    }
     public String changeRole(String userName) throws Exception {
        boolean flag= userDAO.changeRole(userName, this.userRole);
        if (flag) {
            return "/adminPanel.xhtml";
        }
        else return "/error.xhtml";
     }
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public String addNewUser() throws Exception {
        if ((passIsEquals(password, pass2)) == true) {
            userDAO.addNewUser(userName, name, surName, patronymic, password);
            return "/login.xhtml";
        } else {
            if (passIsEquals(password, pass2) == false) {
                error = ("Пароли не совпадают!");
            } else {
                error = ("Такое имя уже существует!");
            }
            return "/error.xhtml";
        }
    }

    public boolean passIsEquals(String pass1, String pass2) {
        if (pass1.equals(pass2)) {
            return true;
        } else {
            return false;
        }
    }
     public String deleteUser(String userName) throws Exception {
        boolean flag = userDAO.deleteUser(userName);
        if (flag) {
            return "/adminPanel.xhtml";
        }
        else return "/error.xhtml";
     }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surName
     */
    public String getSurName() {
        return surName;
    }

    /**
     * @param surName the surName to set
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * @param patronymic the patronymic to set
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the pass2
     */
    public String getPass2() {
        return pass2;
    }

    /**
     * @param pass2 the pass2 to set
     */
    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * @return the userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
