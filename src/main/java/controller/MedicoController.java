package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import repository.medico.MedicoRepositoryImpl;
import repository.paciente.PacienteRepositoryImpl;

public class MedicoController {

	private final MedicoRepositoryImpl medicoRepositoryImpl = new MedicoRepositoryImpl();
	private final PacienteRepositoryImpl pacienteRepositoryImpl = new PacienteRepositoryImpl();

	public Optional<Document> findByDni(String dni) {
		Optional<Document> medico = medicoRepositoryImpl.findById(dni);

		return medico;

	}

	public Optional<Document> findByDniPaciente(String dni) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);

		return paciente;
	}

	public Optional<Document> findbyCitasPaciente(String dni) {
		Optional<Document> medico = medicoRepositoryImpl.findCitasPacientes(dni);
		return medico;
	}

	public String[] dniPacientes(String dni) {
		String[] dniPacientes = medicoRepositoryImpl.guardarDniPacientes(dni);
		return dniPacientes;
	}

	public Optional<Document> comprobarDni(String dni) {
		Optional<Document> medicos = medicoRepositoryImpl.findById(dni);
		return medicos;

	}

	public Optional<Document> comprobarContraseña(String contraseña) {
		Optional<Document> medicos = medicoRepositoryImpl.findByContraseña(contraseña);
		return medicos;

	}

	public Boolean anadirTarjeta(Optional<Document> medicos, String[] valor) {
		List<String> list = Arrays.asList(valor); // Convertir array en lista

		Boolean actualizado = pacienteRepositoryImpl.updateMedicamentosTarjeta(medicos, "Tarjeta_Medica", list);

		return actualizado;
	}

	public Boolean eliminarMedicamentoTarjeta(Optional<Document> medicos, String valor) {
		Boolean actualizado = pacienteRepositoryImpl.eliminarValorDeArray(medicos, "Tarjeta_Medica", valor);
		return actualizado;
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
