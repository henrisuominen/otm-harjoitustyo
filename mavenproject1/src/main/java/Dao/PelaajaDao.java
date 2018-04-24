package dao;

 
import domain.Pelaaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhenri
 */
public class PelaajaDao implements Dao<Pelaaja, Integer> {

    private Database database;

    public PelaajaDao(Database database) {
        this.database = database;
    }

    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Pelaaja WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    private Pelaaja save(Pelaaja pelaaja) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Pelaaja"
                + " (nimi, rahaa)"
                + " VALUES (?, ?)");
        stmt.setString(1, pelaaja.getNimi());
        stmt.setInt(2, pelaaja.getRahaa());

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM Pelaaja"
                + " WHERE nimi = ?");
        stmt.setString(1, pelaaja.getNimi());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        Pelaaja p = new Pelaaja(rs.getString("nimi"), rs.getInt("rahaa"));

        stmt.close();
        rs.close();

        conn.close();

        return p;
    }

    private Pelaaja update(Pelaaja pelaaja) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Pelaaja SET"
                + " name = ? WHERE id = ?");
        stmt.setInt(1, pelaaja.getId());
        stmt.setString(2, pelaaja.getNimi());

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return pelaaja;
    }


    public Pelaaja saveOrUpdate(Pelaaja pelaaja) throws SQLException {
        if ((Integer) pelaaja.getId() == null) {
            return save(pelaaja);
        } else {

            return update(pelaaja);
        }
    }

    public List<Pelaaja> findAll() throws SQLException {
        List<Pelaaja> pelaajat = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pelaaja");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Pelaaja pelaaja = new Pelaaja(rs.getString("nimi"), rs.getInt("rahaa"));
            pelaajat.add(pelaaja);
        }

        stmt.close();
        rs.close();

        connection.close();
        return pelaajat;
    }

    public Pelaaja findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Pelaaja WHERE id = ?");
        stmt.setInt(1, key);
        

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Pelaaja pelaaja = new Pelaaja(rs.getString("nimi"), rs.getInt("rahaa"));

        stmt.close();
        rs.close();

        conn.close();

        return pelaaja;
    }
}