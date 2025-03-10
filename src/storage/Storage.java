package storage;

import java.util.ArrayList;
import java.util.List;

import ordination.Laegemiddel;
import ordination.Patient;

public class Storage {
	private List<Patient> patienter;
	private List<Laegemiddel> laegemidler;

	public Storage() {
		patienter = new ArrayList<>();
		laegemidler = new ArrayList<>();
	}

	/**
	 * Returnerer en liste med alle gemte patienter
	 */
	public List<Patient> getAllPatienter() {
		return new ArrayList<>(patienter);
	}

	/**
	 * Gemmer patient
	 */
	public void addPatient(Patient patient) {
		if (!patienter.contains(patient)) {
			patienter.add(patient);
		}
	}

	/**
	 * Returnerer en liste med alle gemte lægemidler
	 */
	public List<Laegemiddel> getAllLaegemidler() {
		return new ArrayList<>(laegemidler);
	}

	/**
	 * Gemmer lægemiddel
	 */
	public void addLaegemiddel(Laegemiddel laegemiddel) {
		if (!laegemidler.contains(laegemiddel)) {
			laegemidler.add(laegemiddel);
		}
	}

}
