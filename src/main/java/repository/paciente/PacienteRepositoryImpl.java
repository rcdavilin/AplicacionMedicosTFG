package repository.paciente;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import db.MongoDB;

public class PacienteRepositoryImpl implements PacienteRepository {

	MongoClient mongoClient = MongoDB.getClient();
	MongoDatabase database = mongoClient.getDatabase("TrabajoMongo");
	MongoCollection<Document> collection = database.getCollection("Pacientes");
	String dni = "Dni", nombre = "Nombre", apellidos = "Apellidos", fechaNacimiento = "Fecha_Nacimiento", sexo = "Sexo",
			lugarNacimiento = "Lugar_Nacimiento", altura = "Altura", peso = "Peso", grupo_Sanguineo = "Grupo_Sanguineo",
			enfermedad = "Enfermedad", tipo = "Tipo";

	@Override
	public List<Document> findAll() {
		Bson projectionFields = Projections.fields(Projections.include(dni, nombre, enfermedad, tipo),
				Projections.excludeId());

		MongoCursor<Document> cursor = collection.find().projection(projectionFields).iterator();

		List<Document> documentList = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				documentList.add(document);
			}
		} finally {
			cursor.close();
		}

		return documentList;
	}

	public String findDniPorDni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(dni);
		return (String) dniList;

	}

	public String findNombrePordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(nombre);
		return (String) dniList;

	}

	public String findApellidosPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(apellidos);
		return (String) dniList;

	}

	public String findFechaNacimientoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(fechaNacimiento);
		return (String) dniList;

	}

	public String findSexoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(sexo);
		return (String) dniList;

	}

	public String findLugarNacimientoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(lugarNacimiento);
		return (String) dniList;

	}

	public Integer findAlturaPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(altura);
		return (Integer) dniList;

	}

	public Integer findPesoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(peso);
		return (Integer) dniList;

	}

	public String findGrupoSanguineoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(grupo_Sanguineo);
		return (String) dniList;

	}

	@Override
	public Boolean save(Document entity) {
		try {
			collection.insertOne(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<Document> findById(String id) {
		Bson filter = eq(dni, id);
		Bson projectionFields = Projections.excludeId();
		Document result = collection.find(filter).projection(projectionFields).first();
		return Optional.ofNullable(result);
	}

	public List<Document> findByNombre(String nombre) {

		Bson filter = eq("Nombre", nombre);
		Bson projectionFields = Projections.excludeId();

		List<Document> results = collection.find(filter).projection(projectionFields).into(new ArrayList<>());
		return results;
	}

	@Override
	public DeleteResult delete(String dni) {
		DeleteResult resultado = null;
		try {
			Bson filter = eq("Dni", dni);
			resultado = collection.deleteOne(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public String[] findCitasPacientes(String medico) {
		Bson filter = eq(dni, medico);
		Document result = collection.find(filter).first();
		List<String> dniList = (List<String>) result.get("Citas_Paciente");
		return dniList.toArray(new String[0]);

	}

	@SuppressWarnings("unchecked")
	public String[] guardarMedicamentos(String paciente) {
		Bson filter = eq(dni, paciente);
		Document document = collection.find(filter).first();
		List<String> dniList = (List<String>) document.get("Tarjeta_Medica");
		return dniList.toArray(new String[0]);
	}

	public Boolean updateCitasMedicos(Optional<Document> paciente, String atributo, List<String> citas) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				collection.updateOne(eq("Dni", filter.getString("Dni")), Updates.pushEach(atributo, citas));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean updateMedicamentosTarjeta(Optional<Document> paciente, String atributo, List<String> valor) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				collection.updateOne(eq("Dni", filter.getString("Dni")), Updates.pushEach(atributo, valor));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean eliminarValorDeArray(Optional<Document> paciente, String atributo, String valor) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				// Eliminamos el valor especificado del array existente
				collection.updateOne(eq("Dni", filter.getString("Dni")), Updates.pull(atributo, valor));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(Optional<Document> paciente, String atributo, List<String> valores) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				Document update = new Document("$set", new Document(atributo, valores));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(Optional<Document> paciente, String atributo, String valor) {
		try {

			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				Document update = new Document("$set", new Document(atributo, valor));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(Optional<Document> paciente, String atributo, Document valores) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				Document update = new Document("$set", new Document(atributo, valores));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Document> findByAttribute(String atributo, String valor) {
		Bson filter = eq(atributo, valor);
		Bson projectionFields = Projections.excludeId();
		List<Document> results = collection.find(filter).projection(projectionFields).into(new ArrayList<>());
		return results;
	}

}
