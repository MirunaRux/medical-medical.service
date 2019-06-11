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

import medical.model.Drug;

@Repository
public class DrugDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Drug> getAllDrugs() {
        List drugs = new ArrayList<Drug>();
        String getAllSql = "SELECT * FROM drug";
        try {
            drugs = jdbcTemplate.query(getAllSql, new RowMapper<Drug>() {
                @Override
                public Drug mapRow(ResultSet result, int rowNum) throws SQLException {
                    return new Drug(result.getString("id"), result.getString("name"), result.getInt("drugNumber"));
                }
            });

        } catch (Exception e) {

        }
        return drugs;
    }

    public void create(Drug drug) {
        String insertSql = "INSERT INTO drug (id, name, drugNumber) VALUES (?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate(dataSource);

        // define query arguments
        Object[] params = new Object[]{drug.getId(), drug.getName(), drug.getDrugNumber()};

        // define SQL types of the arguments
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        template.update(insertSql, params, types);

    }

    public void update(Drug drug) {
        String sqlUpdateQuery = "UPDATE drug SET drugNumber=? WHERE id=?";
        jdbcTemplate.update(sqlUpdateQuery, drug.getDrugNumber(), drug.getId());
    }
}
