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

import medical.model.Event;

@Repository
public class EventDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Event> getAllEvents() {
        List events = new ArrayList<Event>();
        String getAllSql = "SELECT * FROM event";
        try {
            events = jdbcTemplate.query(getAllSql, new RowMapper<Event>() {
                @Override
                public Event mapRow(ResultSet result, int rowNum) throws SQLException {
                    return new Event(result.getString("id"), result.getString("name"), result.getString("location"),
                            result.getString("startDate"), result.getString("startTime"),result.getString("pacientName"),
                            result.getString("doctorUsername"));
                }
            });

        } catch (Exception e) {

        }
        return events;
    }

    public void create(Event event) {
        String insertSql = "INSERT INTO event (id, name, location, startDate, startTime, pacientName, doctorUsername) VALUES (?, ?, ?, ?, ?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate(dataSource);

        // define query arguments
        Object[] params = new Object[]{event.getId(), event.getName(), event.getLocation(), event.getStartDate(), event.getStartTime(),
                event.getPacientName(), event.getDoctorUsername()};

        // define SQL types of the arguments
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        template.update(insertSql, params, types);

    }

    public void delete(String id){
        String deleteSql = "DELETE FROM event WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }

    public void update(Event event) {
        String sqlUpdateQuery = "UPDATE event SET name=?, location=?, startDate=?, startTime=?, pacientName=?, doctorUsername=? WHERE id=?";
        jdbcTemplate.update(sqlUpdateQuery, event.getName(), event.getLocation(), event.getStartDate(), event.getStartTime(),
                event.getPacientName(), event.getDoctorUsername(), event.getId());
    }
}
