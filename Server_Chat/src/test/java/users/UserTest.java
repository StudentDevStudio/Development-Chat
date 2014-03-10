package users;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void getLogin_user_returnsLogin() {
        User user = new User("login", "password123");

        assertEquals("login", user.getLogin());
    }

    @Test
    public void getEncryptedPassword_user_returnsEncryptedPassword() {
        User user = new User("login", "password123");

        assertEquals("password123", user.getEncryptedPassword());
    }

}
