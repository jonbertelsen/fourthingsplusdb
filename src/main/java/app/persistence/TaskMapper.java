package app.persistence;

import app.entities.Task;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskMapper
{

    public static List<Task> getAllTasksPerUser(int user_id, ConnectionPool connectionPool) throws DatabaseException
    {
        List<Task> taskList = new ArrayList<>();
        String sql = "select * from task where user_id=?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Boolean done = rs.getBoolean("done");
                    taskList.add(new Task(id, name, done, user_id));
                }
            }

        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!");
        }
        return taskList;
    }
}
