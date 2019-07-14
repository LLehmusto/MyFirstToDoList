package todoharjoitus.todolista.dao;
        import java.sql.SQLException;
        import java.util.List;
        import todoharjoitus.todolista.ToDo;

public interface ToDoDao {

    List<ToDo> palautaKaikki();
    int lisaaTehtava(ToDo task);
    ToDo poista(int id);

}
