package dao;


import model.TODO;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TODOInMemoryDao implements TODODao {
    private final AtomicInteger lastId = new AtomicInteger(0);
    private final List<TODO> TODOs = new CopyOnWriteArrayList<>();

    public int addTODO(TODO TODO) {
        int id = lastId.incrementAndGet();
        TODO.setId(id);
        TODOs.add(TODO);
        return id;
    }

    @Override
    public int deleteTODO(TODO TODO) {
        TODOs.remove(TODO.getId());
        return TODO.getId(); //??
    }

    @Override
    public int addTask(Task task) {
        TODOs.get(task.getTODOID()).addTask(task.getName());
        return task.getTODOID();
    }
    @Override
    public int deleteTask(Task task) {
        TODOs.get(task.getTODOID()).deleteTask(task.getName());
        return task.getTODOID(); //??
    }


    public List<TODO> getTODOs() {
        return new ArrayList(TODOs);
    }

    @Override
    public List<Task> getTasks(int id) {
        return TODOs.get(id).getTasks();
    }
}
