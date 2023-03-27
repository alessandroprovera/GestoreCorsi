package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
public List<Studente> getStudentiByCorso(String codins) {
		
		String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS "
				+ "FROM iscrizione as i, studente as s "
				+ "WHERE i.matricola = s.matricola AND i.codins = ?";
		
		List<Studente> resultStudente = new ArrayList<>();
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), 
						rs.getString("cognome"), rs.getString("nome"), 
						rs.getString("CDS"));
				resultStudente.add(s);
			}
			
			st.close();
			rs.close();
			conn.close();
			return resultStudente;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Studente DAO");
			e.printStackTrace();
			return null;
		}
		
	}

public List<Divisione> getNstudentiPerCDS(String codins) {
	
	String sql = "SELECT s.cds, COUNT(*) as nStudenti "
			+ "FROM iscrizione as i, studente as s "
			+ "WHERE i.matricola = s.matricola AND i.codins = ? and s.CDS <> \"\" "
			+ "GROUP BY s.CDS";
	
	List<Divisione> result = new ArrayList<Divisione>();
	
	try {
		
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, codins);
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			
			Divisione d = new Divisione(rs.getString("cds"), 
					rs.getInt("nStudenti"));
			result.add(d);
			
		}
		
		st.close();
		rs.close();
		conn.close();
		return result;
		
	} catch(SQLException e) {
		System.out.println("Error in Corso DAO");
		e.printStackTrace();
		return null;		
		
	}
}

	

}
