package firsttest;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class MongoDBOne {
    public static void main(String[] args) {
        try {
            // Conecta ao banco de dados sakilla
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("sakilla");
            System.out.println("Banco de dados conectado com sucesso");

            //Seleciona a collection "carnaval_2020" dentro de sakilla:
            DBCollection collection = db.getCollection("carnaval_2020");

            //Insere os dados:
            BasicDBObject carnaval_2020 = new BasicDBObject();
            carnaval_2020.put("nome", "Rodrigo");
            carnaval_2020.put("sobrenome", "Aramburu");
            carnaval_2020.put("telefone", "1234-5678");

            BasicDBObject end = new BasicDBObject();
            end.put("rua", "Rua Fulano");
            end.put("numero", "221B");
            end.put("bairro", "Centro");

            carnaval_2020.put("endereco", end);

            collection.insert(carnaval_2020);

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            //Ou conecta ao banco de dados "teste":
            DB db1 = mongo.getDB("teste");
            DBCollection coll = db1.getCollection("ubs");
            DBCursor cursor2 = coll.find();

            while (cursor2.hasNext()) {
                BasicDBObject ubs1 = (BasicDBObject) cursor2.next();
                System.out.println("codigo: " + ubs1.getString("codigo"));
                System.out.println("empreendimento: " + ubs1.getString("empreendimento"));
                System.out.println("unidade_federativa: " + ubs1.getString("unidade_federativa"));
                System.out.println("\n\n");
            }

            /*
            //Tentativa de autenticação de usuário:
            boolean auth = db.authenticate("Jones", "123".toCharArray());
            System.out.println("Autenticacao: " + auth);
            */
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        
        /*
        try {
            MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        
            MongoDatabase database = mongoClient.getDatabaseNames("realdatabase");                
            MongoCollection<Document> collection = database.getCollection("channel");

            Document doc = new Document();
            doc.put("name", "somalia");
            doc.put("state", "casado");

            collection.insertOne(doc);

            } catch (UnknownHostException ex) {
                Logger.getLogger(MongoDBOne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
    }

    /*
    //Outro código para inserir dados na collection "actor" do banco de dados "sakilla":
    public static void main(String[] args) throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("sakilla");
        DBCollection collection = db.getCollection("actor");

        //Delete All documents before running example again
        WriteResult result = collection.remove(new BasicDBObject());
        System.out.println(result.toString());

        basicDBObject_Example(collection);

        basicDBObjectBuilder_Example(collection);

        hashMap_Example(collection);

        parseJSON_Example(collection);

        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void basicDBObject_Example(DBCollection collection) {
        BasicDBObject document = new BasicDBObject();
        document.put("name", "lokesh");
        document.put("website", "howtodoinjava.com");

        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("addressLine1", "Sweet Home");
        documentDetail.put("addressLine2", "Karol Bagh");
        documentDetail.put("addressLine3", "New Delhi, India");

        document.put("address", documentDetail);

        collection.insert(document);
    }
    
    //Adicionar na collection usando builder:
    private static void basicDBObjectBuilder_Example(DBCollection collection) {
        BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
                .add("name", "lokesh")
                .add("website", "howtodoinjava.com");

        BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
                .add("addressLine1", "Some address")
                .add("addressLine2", "Karol Bagh")
                .add("addressLine3", "New Delhi, India");

        documentBuilder.add("address", documentBuilderDetail.get());

        collection.insert(documentBuilder.get());
    }
    
    //Adicionando os dados através de um hashmap (uma estrutura de armazenamento de dados em modo key, value):
    private static void hashMap_Example(DBCollection collection) {
        Map<String, Object> documentMap = new HashMap<String, Object>();
        documentMap.put("name", "lokesh");
        documentMap.put("website", "howtodoinjava.com");

        Map<String, Object> documentMapDetail = new HashMap<String, Object>();
        documentMapDetail.put("addressLine1", "Some address");
        documentMapDetail.put("addressLine2", "Karol Bagh");
        documentMapDetail.put("addressLine3", "New Delhi, India");

        documentMap.put("address", documentMapDetail);

        collection.insert(new BasicDBObject(documentMap));
    }
    
    //Adiciona em formato JSON:
    private static void parseJSON_Example(DBCollection collection) {
        String json = "{ 'name' : 'lokesh' , "
                + "'website' : 'howtodoinjava.com' , "
                + "'address' : { 'addressLine1' : 'Some address' , "
                + "'addressLine2' : 'Karol Bagh' , "
                + "'addressLine3' : 'New Delhi, India'}"
                + "}";

        DBObject dbObject = (DBObject) JSON.parse(json);

        collection.insert(dbObject);
    }
    */
}