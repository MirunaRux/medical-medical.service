package medical.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import medical.model.User;

@Repository
public class UserDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        List users = new ArrayList<User>();
        String getAllSql = "SELECT * FROM users";
        try {
            users = jdbcTemplate.query(getAllSql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet result, int rowNum) throws SQLException {
                    return new User(result.getString("username"), result.getString("password"), result.getString("role"));
                }
            });

        } catch (Exception e) {

        }
        return users;
    }

    public void create(User user) {
        String insertSql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate(dataSource);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getRole());
        // define query arguments
        Object[] params = new Object[]{user.getUsername(), user.getPassword(), user.getRole()};

        // define SQL types of the arguments
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        template.update(insertSql, params, types);

    }

}
