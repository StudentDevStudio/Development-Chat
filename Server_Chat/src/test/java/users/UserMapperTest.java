package users;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserMapperTest {
    @Test
    public void findAll_emptyDB_returnsEmptyList() {
        UserMapper userMapper = new UserMapper();

        List<User> users = userMapper.findAll();

        assertTrue(users.isEmpty());
    }

    @Test
    public void findAll_dbWithOneUser_returnsListWhichContainsUser() {
        UserMapper userMapper = new UserMapper();
        User user = new User("any string", "any string");
        userMapper.create(user);

        List<User> users = userMapper.findAll();

        assertTrue(users.contains(user));
    }

    @Test
    public void findUserByLogin_emptyDB_returnsEmptyList() {
        UserMapper userMapper = new UserMapper();

        List<User> users = userMapper.findUserByLogin("login");

        assertTrue(users.isEmpty());
    }
}
