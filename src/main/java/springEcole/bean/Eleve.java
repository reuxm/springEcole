package springEcole.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name="t_eleve" )
public class Eleve {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nom", nullable=false, length=100)
	private String nom;
	
	@Column(name="prenom", nullable=true, length=100)
	private String prenom;
	
	@ManyToOne
	@JoinColumn( name="classe", nullable=false )
	private Classe classe;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name="date_naissance" )
	private Date dateNaissance;
	
	@Column(name="adresse", nullable=true, length=250)
	private String adresse;
	
	@Enumerated(EnumType.STRING)
	private Sexe sexe;

	@OneToMany(mappedBy="eleve", fetch=FetchType.LAZY)
	private List<Note> notes = new ArrayList<Note>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "Eleve [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", classe=" + classe + ", dateNaissance="
				+ dateNaissance + ", adresse=" + adresse + ", sexe=" + sexe + ", notes=" + notes + "]";
	}
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	
}
