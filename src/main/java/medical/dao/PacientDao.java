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

import medical.model.Pacient;

@Repository
public class PacientDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pacient> getAllPacients() {
        List pacients = new ArrayList<Pacient>();
        String getAllSql = "SELECT * FROM pacient";
        try {
            pacients = jdbcTemplate.query(getAllSql, new RowMapper<Pacient>() {
                @Override
                public Pacient mapRow(ResultSet result, int rowNum) throws SQLException {
                    return new Pacient(result.getString("id"), result.getString("name"), result.getString("surname"), result.getString("birthday"),
                            result.getString("cnp"), result.getString("dateIn"), result.getString("dateEx"));
                }
            });

        } catch (Exception e) {

        }
        return pacients;
    }

    public void create(Pacient pacient) {
        String insertSql = "INSERT INTO pacient (id, name, surname, birthday, cnp, dateIn, dateEx) VALUES (?, ?, ?, ?, ?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate(dataSource);

        // define query arguments
        Object[] params = new Object[]{pacient.getId(), pacient.getName(), pacient.getSurname(), pacient.getBirthday(), pacient.getCnp(), pacient.getDateIn(), pacient.getDateEx()};

        // define SQL types of the arguments
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        template.update(insertSql, params, types);

    }

    public void update(Pacient pacient) {
        String sqlUpdateQuery = "UPDATE pacient SET name=?, surname=?, birthday=?, cnp=?, dateIn=?, dateEx=? WHERE id=?";
        jdbcTemplate.update(sqlUpdateQuery, pacient.getName(), pacient.getSurname(), pacient.getBirthday(), pacient.getCnp(), pacient.getDateIn(),
                pacient.getDateEx(), pacient.getId());
    }
}
