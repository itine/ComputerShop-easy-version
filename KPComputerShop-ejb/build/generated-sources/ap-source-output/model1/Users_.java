package model1;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model1.Orders;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-14T23:01:29")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> patronymic;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> surname;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile ListAttribute<Users, Orders> ordersList;
    public static volatile SingularAttribute<Users, String> username;

}