package todoharjoitus.todolista.dao;

import todoharjoitus.todolista.ToDo;
import todoharjoitus.todolista.dao.ToDoDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Qualifier("jdbc")
public class ToDoDaoJdbcImpl implements ToDoDao {
    private Connection con;

    public ToDoDaoJdbcImpl() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todot", "postgres", "postgresLaura");
    }

    @Override
    public List<ToDo> palautaKaikki() {
        String sql = "SELECT * FROM todot";
        List<ToDo> haetut = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (ResultSet rs = pstmt.executeQuery() ; rs.next() ;) {
                ToDo t = new ToDo();
                t.setId(rs.getInt("id"));
                t.setTask(rs.getString("task"));
                haetut.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST; // tai null kun nyt virhetilanteesta on kyse...
        }
        return haetut;
    }


    @Override
    public int lisaaTehtava(ToDo task) {
        int avain = -1;
        String teksti = task.getTask();
        String sql = ("INSERT INTO todot(task) VALUES (?)");
        try (PreparedStatement lause = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            lause.setString(1, teksti);
            lause.execute();
            ResultSet avaimet = lause.getGeneratedKeys();
            if (avaimet.next()) {
                avain = avaimet.getInt(1);
            }
            lause.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avain;
    }

    @Override
    public ToDo poista(int id) {
        String sql = ("DELETE FROM task WHERE id=?");
        try (PreparedStatement lause = con.prepareStatement(sql)) {
            lause.setInt(1, id);
            lause.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
