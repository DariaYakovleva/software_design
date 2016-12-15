package model;
/**
 * Created by Daria on 06.11.2016.
 */
public class Task {

    private String name;
    private int TODOID;

    public Task() {
    }

    public Task(int TODOID, String name) {
        this.TODOID = TODOID;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTODOID(int TODOID) {
        this.TODOID = TODOID;
    }

    public int getTODOID() {
        return TODOID;
    }
}
