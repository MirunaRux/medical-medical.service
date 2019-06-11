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

import medical.model.Request;

@Repository
public class RequestDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Request> getAllRequests() {
        List requests = new ArrayList<Request>();
        String getAllSql = "SELECT * FROM request";
        try {
            requests = jdbcTemplate.query(getAllSql, new RowMapper<Request>() {
                @Override
                public Request mapRow(ResultSet result, int rowNum) throws SQLException {
                    return new Request(result.getString("id"), result.getString("drugName"), result.getInt("cantity"));
                }
            });

        } catch (Exception e) {

        }
        return requests;
    }

    public void create(Request request) {
        String insertSql = "INSERT INTO request (id, drugName, cantity) VALUES (?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate(dataSource);

        // define query arguments
        Object[] params = new Object[]{request.getId(), request.getDrugName(), request.getCantity()};

        // define SQL types of the arguments
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        template.update(insertSql, params, types);

    }

    public void delete(String id) {
        String sqlUpdateQuery = "DELETE FROM request WHERE id=?";
        jdbcTemplate.update(sqlUpdateQuery, id);
    }
}
