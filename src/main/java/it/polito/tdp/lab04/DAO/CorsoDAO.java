package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c= new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
				
//				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
	}

	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		String sql= "SELECT s.matricola, s.cognome,s.nome, s.CDS FROM iscrizione i, studente s WHERE i.matricola=s.matricola AND i.codins=?";
		List<Studente> risultato = new ArrayList<>();

		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				risultato.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}
			return risultato;
		} 
		
		catch (Exception e) {
			System.err.println("Errore di connessione al database");
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		String sql="SELECT * FROM iscrizione i, corso c WHERE i.codins=c.codins AND i.matricola=? AND i.codins=? " ;
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				return true;
			}
		} 
		
		catch (Exception e) {
			System.err.println("Errore di connessione al database");
			e.printStackTrace();
			return false;
		}
		
		
		
		return false;
	}

}
