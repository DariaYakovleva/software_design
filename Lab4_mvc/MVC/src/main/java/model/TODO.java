package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TODO {
    private int id;
    private String name;
    private List<Task> tasks;
    public TODO() {
    }

    public TODO(int id, String name) {
        this.id = id;
        this.name = name;
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(new Task(id, task));
    }
    public void deleteTask(String name) {
        int pos = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().compareTo(name) == 0) {
                pos = i;
            }
        }
        tasks.remove(pos);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
