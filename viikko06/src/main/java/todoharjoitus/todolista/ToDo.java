package todoharjoitus.todolista;

public class ToDo {
    private int id;
    private String task;

    public ToDo() {

    }

    public ToDo(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Tehtävä " + task + ", ID: " + id;
    }
}
