package model1;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model1.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-22T18:44:12")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Integer> totalSum;
    public static volatile SingularAttribute<Orders, Integer> orderId;
    public static volatile SingularAttribute<Orders, String> paymentMethod;
    public static volatile SingularAttribute<Orders, String> orderStatus;
    public static volatile SingularAttribute<Orders, Users> userName;

}