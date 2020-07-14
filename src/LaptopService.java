

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaptopService {
    private Connection con;

    public LaptopService() {
    }

    public LaptopService(Connection connection) {
        this.con = connection;
    }

    public List<Statistic>getStatisticByMaker() {
        try {
            List<Statistic> statistics = new ArrayList();
            String sql = "SELECT maker, sold, (price*sold) AS totalMoney FROM store_cms_plusplus.laptop order by totalMoney;";
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Statistic statistic = new Statistic(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getFloat(3)
                );
                statistics.add(statistic);
            }
            return statistics;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

