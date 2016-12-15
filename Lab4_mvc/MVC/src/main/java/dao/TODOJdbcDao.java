package dao;

import model.TODO;
import model.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;

public class TODOJdbcDao extends JdbcDaoSupport implements TODODao {

    public TODOJdbcDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    @Override
    public int addTODO(TODO TODO) {
        String sql = "INSERT INTO todo (NAME) VALUES (?)";
        return getJdbcTemplate().update(sql, new Object[] { TODO.getName()});
    }


    public int deleteTODO(TODO TODO) {
        String sql = "DELETE FROM todo WHERE ID = ?";
        return getJdbcTemplate().update(sql, new Object[] { TODO.getId()});
    }

    @Override
    public List<TODO> getTODOs() {
//        getJdbcTemplate().update("DROP TABLE todo;");
//        getJdbcTemplate().update("CREATE TABLE IF NOT EXISTS todo (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NAME TEXT NOT NULL);");
//        getJdbcTemplate().update("CREATE TABLE IF NOT EXISTS tasks (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TODOID INTEGER NOT NULL, NAME TEXT NOT NULL);");
//        getJdbcTemplate().update("INSERT INTO tasks (TODOID, NAME) VALUES (2, 'task');");
//        getJdbcTemplate().update("INSERT INTO todo (NAME) VALUES ('first');");
        String sql = "SELECT * FROM todo";
        List<TODO> res = getTODOsByRequest(sql);
        for (int i = 0; i < res.size(); i++) {
            res.get(i).setTasks(getTasks(res.get(i).getId()));
        }
        return res;
    }

    @Override
    public int addTask(Task task) {
        String sql = "INSERT INTO tasks (TODOID, NAME) VALUES (?, ?)";
        return getJdbcTemplate().update(sql, new Object[] { task.getTODOID(), task.getName()});
    }

    @Override
    public int deleteTask(Task task) {
        String sql = "DELETE FROM tasks WHERE TODOID = ? AND NAME = ?";
        return getJdbcTemplate().update(sql, new Object[] { task.getTODOID(), task.getName()});

    }

    @Override
    public List<Task> getTasks(int id) {
        String sql = "SELECT TODOID, NAME FROM tasks where TODOID = " + id +";";
        return getTasksByRequest(sql);
    }

    private List<TODO> getTODOsByRequest(String sql) {
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TODO.class));
    }

    private List<Task> getTasksByRequest(String sql) {
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Task.class));
    }
}
