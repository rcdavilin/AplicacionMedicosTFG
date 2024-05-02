package controller;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

import repository.medico.MedicoRepositoryImpl;

public class MedicoController {

	private final MedicoRepositoryImpl medicoRepositoryImpl = new MedicoRepositoryImpl();

	public Optional<Document> findByDni(String dni) {
		Optional<Document> medico = medicoRepositoryImpl.findById(dni);

		return medico;

	}
	
	public String getPacientesCargo() {
		Optional<Document> medico = medicoRepositoryImpl.findPacientesCargo();
		if (medico.isEmpty()) {
			return "No se encontraron pacientes.";
		} else {
			return medicoRepositoryImpl.mostrar(medico);
		}

	}

	public Optional<Document> comprobarDni(String dni) {
		Optional<Document> medicos = medicoRepositoryImpl.findById(dni);
		return medicos;

	}

	public Optional<Document> comprobarContraseña(String contraseña) {
		Optional<Document> medicos = medicoRepositoryImpl.findByContraseña(contraseña);
		return medicos;

	}

	public Boolean anadirContraseña(String dni, String atributo, String valor) {
		Optional<Document> medicos;

		medicos = medicoRepositoryImpl.findById(dni);
		Boolean actualizado = medicoRepositoryImpl.update(medicos, atributo, valor);

		return actualizado;
	}

	public Boolean actualizarContraseña(Optional<Document> medicos, String atributo, String contraseña) {
		return medicoRepositoryImpl.update(medicos, atributo, contraseña);
	}
	
	public String mostrar(List<Document> medicos) {
		String ensenar = medicoRepositoryImpl.mostrarMedicos(medicos);
		return ensenar;
	}
	public String mostrar(Optional<Document> medicos) {
		String ensenar = medicoRepositoryImpl.mostrar(medicos);
		return ensenar;
	}

	public String mostrar(String mensaje) {
		return mensaje;
	}
	

}
