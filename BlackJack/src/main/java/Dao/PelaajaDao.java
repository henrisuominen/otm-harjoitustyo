package Dao;

 
import domain.Pelaaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Pelaajan Tietokanta, joka tallettaa rahaa, nimen ja id:n.
 */
public class PelaajaDao implements Dao<Pelaaja, Integer> {
    private Database database;

    public PelaajaDao(Database database) {
        this.database = database;
    }

    /**
     * Poistaa henkilön tietokannasta sen tunnusluvun perusteella.
     * @param key on pelaajalle uniikki tunniste
     * @throws SQLException 
     */
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Pelaaja WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * Tallentaa uuden pelaajan tietokantaan.
     * @param pelaaja talletettava pelaaja
     * @return Pelaaja, joka talletettiin.
     * @throws SQLException 
     */
    private Pelaaja save(Pelaaja pelaaja) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Pelaaja"
                + " (nimi, rahaa)"
                + " VALUES (?, ?)");
        stmt.setString(1, pelaaja.getNimi());
        stmt.setInt(2, pelaaja.getRahaa());
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("SELECT * FROM Pelaaja WHERE nimi = ?");
        stmt.setString(1, pelaaja.getNimi());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Pelaaja p = new Pelaaja(rs.getString("nimi"), rs.getInt("rahaa"));
        stmt.close();
        rs.close();
        conn.close();
        return p;
    }

    /**
     * uudistaa pelaajan nimen.
     * @param pelaaja uudistettava pelaaja.
     * @return palauttaa pelaajan.
     * @throws SQLException 
     */
    private Pelaaja update(Pelaaja pelaaja) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Pelaaja SET rahaa = ? WHERE nimi = ?");
        stmt.setInt(1, pelaaja.getRahaa());
        stmt.setString(2, pelaaja.getNimi());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        return pelaaja;
    }

    /**
     * Tallentaa pelaajan jos se on kannassa muuten se kutsuu metodia update
     * @param pelaaja
     * @return palauttaa pelaajan
     * @throws SQLException 
     */
    public Pelaaja saveOrUpdate(Pelaaja pelaaja) throws SQLException {
        if (!this.findAll().contains(pelaaja)) {
            return save(pelaaja);
        } else {
            return update(pelaaja);
        }
    }

    /**
     * Löytää ja palauttaa kaikki tietokannassa olevat pelaajat.
     * @return Listan joka sisältää kaikki tietokannan pelaajat.
     * @throws SQLException 
     */
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

    /**
     * Löytää tietyn pelaajan tietokannasta sen nimen:n perusteella.
     * @param nimi pelaajan nimi
     * @return Kyseisen pelaajan
     * @throws SQLException 
     */
    @Override
    public Pelaaja findByNameOne(String nimi) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Pelaaja WHERE nimi = ?");
        System.out.println("1");
        stmt.setString(1, nimi);
        ResultSet rs = stmt.executeQuery();
        System.out.println("2");
        boolean onViela = rs.next();
        if (!onViela) {
            return null;
        }
        Pelaaja pelaaja = new Pelaaja(rs.getString("nimi"), rs.getInt("rahaa"));
        stmt.close();
        rs.close();
        conn.close();
        return pelaaja;
    }
}