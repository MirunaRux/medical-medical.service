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

import medical.model.File;

@Repository
public class FileDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<File> getAllFiles() {
        List files = new ArrayList<File>();
        String getAllSql = "SELECT * FROM pacient_file";
        try {
            files = jdbcTemplate.query(getAllSql, new RowMapper<File>() {
                @Override
                public File mapRow(ResultSet result, int rowNum) throws SQLException {
                    return new File(result.getString("id"), result.getString("content"), result.getString("pacient_id"));
                }
            });

        } catch (Exception e) {

        }
        return files;
    }

    public void create(File file) {
        String insertSql = "INSERT INTO pacient_file (id, content, pacient_id) VALUES (?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate(dataSource);

        // define query arguments
        Object[] params = new Object[]{file.getId(), file.getContent(), file.getPacient_id()};

        // define SQL types of the arguments
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        template.update(insertSql, params, types);

    }
    //TO DO
    public void update(File file) {
        String sqlUpdateQuery = "UPDATE pacient_file SET content=? WHERE id=?";
        jdbcTemplate.update(sqlUpdateQuery, file.getContent(), file.getId());
    }
}
