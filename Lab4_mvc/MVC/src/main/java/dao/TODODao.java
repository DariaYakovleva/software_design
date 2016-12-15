package dao;


import model.TODO;
import model.Task;

import java.util.List;
import java.util.Optional;

public interface TODODao {
    int addTODO(TODO TODO);
    int deleteTODO(TODO TODO);
    int deleteTask(Task task);
    int addTask(Task task);
    List<TODO> getTODOs();
    List<Task> getTasks(int id);
}
