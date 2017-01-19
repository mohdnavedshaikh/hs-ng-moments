package in.hopscotch.moments.db.util;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JDBCAccess {

    private final Logger logger = LoggerFactory.getLogger(JDBCAccess.class);
    private JdbcTemplate jdbcTemplate;

    public <T> List<T> find(String sql, RowMapper<T> rowMapper, Object... params) {
        List<T> results = jdbcTemplate.query(sql, params, rowMapper);
        return results;
    }

    public <T> T findOne(String sql, RowMapper<T> rowMapper, Object... params) {
        try {
            return jdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.debug("findOne did not find any result", e);
            return null;
        }
    }

    public int update(String sql, Object... params) {
        int updatedRows = 0;
        updatedRows = jdbcTemplate.update(sql, params);
        return updatedRows;
    }

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
