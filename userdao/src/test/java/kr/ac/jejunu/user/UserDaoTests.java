package kr.ac.jejunu;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;

public class UserDaoTests {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "sksk";
        String password = "qwer";
        UserDao userDao = new JejuUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "난가?";
        String password = "3456";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new JejuUserDao();
        userDao.insert(user);
        User insertedUser = userDao.findById(user.getId());
        assertThat(user.getId(), greaterThan(0));
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "sksk";
        String password = "qwer";
        UserDao userDao = new HallaUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insertHalla() throws SQLException, ClassNotFoundException {
        String name = "난가?";
        String password = "3456";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new HallaUserDao();
        userDao.insert(user);
        User insertedUser = userDao.findById(user.getId());
        assertThat(user.getId(), greaterThan(0));
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

}
