package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDAO; 
	private StudenteDAO studenteDAO;
	
	
	public Model() {
		this.corsoDAO= new CorsoDAO();
		this.studenteDAO= new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		return corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudente(int matrciola) {
		return studenteDAO.getStudente(matrciola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}

	
	public List<Corso> getCorsiPerMatrciola(int matricola){
		return studenteDAO.getCorsiPerMatrciola(matricola);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDAO.inscriviStudenteACorso(studente, corso);
	}
	
	public void putMatricolaNelCorso(Corso c, Studente s) {
		studenteDAO.putMatricolaNelCorso(c, s);
	}
}
