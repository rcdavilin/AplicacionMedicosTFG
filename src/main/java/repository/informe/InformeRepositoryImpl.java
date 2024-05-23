package repository.informe;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;

import db.MongoDB;

public class InformeRepositoryImpl implements InformeRepository {

	MongoClient mongoClient = MongoDB.getClient();
	MongoDatabase database = mongoClient.getDatabase("TrabajoMongo");
	MongoCollection<Document> collection = database.getCollection("Informes");

	@Override
	public List<Document> findAll() {
		Bson projectionFields = Projections.fields(Projections.excludeId());

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
		Bson filter = eq("Dni_Paciente", id);
		Bson projectionFields = Projections.excludeId();
		Document result = collection.find(filter).projection(projectionFields).first();
		return Optional.ofNullable(result);
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

	public Boolean guardarInforme(Optional<Document> paciente, byte[] pdfBytes) {
		try {
			if (paciente.isPresent()) {
				Document pdfInforme = new Document("pdf", new Binary(pdfBytes));
				Document filter = new Document("Dni_Paciente", paciente.get().getString("Dni_Paciente"));
				Document update = new Document("$push", new Document("Informes", pdfInforme));

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

	@SuppressWarnings("unchecked")
	public ArrayList<byte[]> verInformes(String medico) {
		Bson filter = Filters.eq("Dni_Paciente", medico);
		Document document = collection.find(filter).first();
		ArrayList<byte[]> informes = new ArrayList<>();

		if (document != null && document.containsKey("Informes")) {
			ArrayList<Document> informesLista = (ArrayList<Document>) document.get("Informes");

			for (Document obj : informesLista) {
				if (obj.containsKey("pdf")) {
					Binary pdfBinary = (Binary) obj.get("pdf");
					informes.add(pdfBinary.getData());
				}
			}
		}

		return informes;
	}
}
