package view;

import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.unwind;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBExample {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://David:JKKlMwKBScFfEs4E@cluster0.c4xmls2.mongodb.net/"; // Update with your MongoDB URI
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("TrabajoMongo"); // Update with your database name
            MongoCollection<Document> pacientesCollection = database.getCollection("Pacientes");

            // Define the aggregation pipeline
            List<Bson> pipeline = Arrays.asList(
                lookup("Medicos", "Dni_Medico", "Dni", "medico_info"),
                unwind("$medico_info"),
                project(new Document("dniPaciente", "$Dni")
                        .append("nombrePaciente", "$Nombre")
                        .append("apellidosPaciente", "$Apellidos")
                        .append("fechaNacimientoPaciente", "$Fecha_Nacimiento")
                        .append("sexoPaciente", "$Sexo")
                        .append("alergenos", "$Alergenos")
                        .append("medicamentos", "$Medicamentos")
                        .append("enfermedad", "$Enfermedad")
                        .append("tipo", "$Tipo")
                        .append("fechaIngreso", "$Fecha_Ingreso")
                        .append("dniMedico", "$medico_info.Dni")
                        .append("nombreMedico", "$medico_info.Nombre")
                        .append("apellidosMedico", "$medico_info.Apellidos")
                        .append("especialidadMedico", "$medico_info.Especialidad"))
            );

            MongoCursor<Document> cursor = pacientesCollection.aggregate(pipeline).iterator();

            try {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            } finally {
                cursor.close();
            }
        }
    }
}

