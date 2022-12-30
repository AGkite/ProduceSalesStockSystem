package dao;

public interface LogDao {
    void updateLog(String type, String name, String state, String id, String count);
}
