package users;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IAUglov on 05.03.14.
 * 
 * 
 */
@SuppressWarnings("restriction")
public class UsersData {
    private List<User> users = new ArrayList<User>();

    @XmlElement(name="user")
    @XmlElementWrapper(name="users")
    public List<User> getUsers(){
        return this.users;
    }
    public void setUsers(List<User> users){
        this.users = users;
    }
    public void clear(){
        users.clear();
    }
}
