package org.example.crud_personas;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

public class ClsCrud {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;


    public void MongoConexion() {

        private MongoClient mongoClient;
        private MongoDatabase database;
        private MongoCollection<Document> collection;

        //cadena de conexion, contiene la información de la insalacion del mongodb
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");

        //se crean las configuraciones especificas para conexion y manejo de la base de datos
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("miBasedatos");
        collection = database.getCollection("Persona");
        System.out.println("conexion realizada");


    }


    public void crearPersona() {
        Persona usuario = new Persona();
        usuario.setNombre("Monica");
        usuario.setEdad(12);
        usuario.setCiudad("GUATEMALA");

        Document document = new Document("nombre", usuario.getNombre())
                .append("CIUDAD", usuario.getCiudad())
                .append("EDAD", usuario.getEdad());
        collection.insertOne(document);
        System.out.println("Persona Creada");
    }

    public void leerPersona() {
        List<Persona> usuario = new ArrayList<>();
        for (Document doc : collection.find()) {
            Persona u = new Persona();
            u.setEdad(doc.getLong("edad "));
            u.setNombre(doc.getString("nombre"));
            u.setCiudad(doc.getString("ciudad "));
            usuario.add(u);
        }
        for (Persona u : usuario) {
            System.out.println("usuario:" + u.getNombre() + "ciudad:" = u.getCiudad());
        }
    }

    private void actualizarPersona() {
        collection.updateOne(Filters.eq("edad", 12),
                Updates.set("ciudad", "GUATEMALA"));
        System.out.println("Usuario actualizado");
    }

    private void eliminarPersona() {
        collection.deleteOne(Filters.eq("nombre", "Monica"));
        System.out.println("persona eliminada");
    }

    private void desconectarMongoDB() {
        // Cerrar la conexión
        mongoClient.close();
        System.out.println("Desconectado de MongoDB");
    }


}


