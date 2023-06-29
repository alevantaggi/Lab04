package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola) {
		String sql= "SELECT * FROM studente s WHERE s.matricola=?";
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Studente s= new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				return s;
			}
			return null;
		} 
		
		catch (Exception e) {
			System.err.println("Errore di connessione al database");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Corso> getCorsiPerMatrciola(int matricola){
		String sql="SELECT c.codins,c.crediti,c.nome,c.pd FROM iscrizione i, corso c WHERE i.codins=c.codins AND i.matricola=? ";
		List<Corso> risultato= new ArrayList<>();
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c= new Corso(codins, numeroCrediti, nome, periodoDidattico);
				risultato.add(c);
			}
			return risultato;
		} 
		
		catch (Exception e) {
			System.err.println("Errore di connessione al database");
			e.printStackTrace();
			return null;
		}
			
			
	}
	
	public void putMatricolaNelCorso(Corso c, Studente s) {
		String sql= "INSERT INTO `iscrizione` (`matricola`, `codins`) VALUES (?, ?)";
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			
			st.executeUpdate();
			
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		
	}
		
		
}
		
		
		

