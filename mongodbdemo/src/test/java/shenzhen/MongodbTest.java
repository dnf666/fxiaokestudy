package shenzhen;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shenzhen.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author demo
 */
@ContextConfiguration("classpath:springconfig.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MongodbTest {
        private static Logger logger = LoggerFactory.getLogger(MongodbTest.class);
    /**
     * 列举全部数据库名
     */
    @Test
    public void testSlf4j(){
        logger.debug("lalalala");
        logger.warn("lalala");
        logger.info("lalala");
    }
    private static MongoClient mongoClient = new MongoClient("10.22.0.30", 27017);
    static Morphia morphia = new Morphia();
    @Test
    public void listDatabaseNames() {
        MongoIterable<String> iterable = mongoClient.listDatabaseNames();
        for (String string : iterable
                ) {
            System.out.println(string);
        }
    }

    /**
     * 对数据库进行添加操作
     */
    @Test
    public void mongodbOperate() {
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        //默认创建collection capped=false autoindex = false
        mongoDatabase.createCollection("mycollection");
        //创建collection选项实现定制collection
        CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions();
        createCollectionOptions.autoIndex(true);
        createCollectionOptions.capped(true);
        createCollectionOptions.maxDocuments(100);
        createCollectionOptions.sizeInBytes(5000);
        mongoDatabase.createCollection("mycollection1", createCollectionOptions);

        MongoIterable<String> iterable = mongoDatabase.listCollectionNames();
        Document document = new Document();
        document.append("name", "123");
        List<Document> list = new ArrayList<>();
        for (String s : iterable
                ) {
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(s);
            mongoCollection.insertMany(list);
        }

    }

    @Test
    public void morphiaTest() {
        //配置映射包位置
        morphia.mapPackage("shenzhen.model");
        //创建数据库连接
        Datastore datastore = morphia.createDatastore(new MongoClient("10.22.0.30", 27017), "a");
        //对数据库进行操作
        DB db = datastore.getDB();
   //     addDocument(datastore);
     findDocument(datastore);
//     updateDocument(datastore);
//      deleteDocument(datastore);
        //删库
//        db.dropDatabase();
        //查看collection是否存在
       boolean b = db.collectionExists("employees");
        System.out.println(b);
    }

    /**
     * 删除工资大于90000的行
     * @param datastore 数据库连接
     */
    private void deleteDocument(Datastore datastore) {
        final Query<Employee> overPaidQuery = datastore.createQuery(Employee.class).filter("salary >", 90000);
        datastore.delete(overPaidQuery);
    }

    /**
     * 为工资小于等于30000的document工资提高10000
     *
     * @param datastore 数据库连接
     */
    private void updateDocument(Datastore datastore) {
        //取得需要更新的数据行
        final Query<Employee> underPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary <=", 30000);
        //创建更新的操作
        final UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class)
                .inc("salary", 10000);
        //执行更新
        final UpdateResults results = datastore.update(underPaidQuery, updateOperations);
        System.out.println(results);
    }

    /**
     * 查询工资小于等于30000的document
     * @param datastore 数据库连接
     */
    private void findDocument(Datastore datastore) {
        //查询的两种形式 第一种
        List<Employee> underpaid = datastore.createQuery(Employee.class)
                .field("salary").lessThanOrEq(30000)
                .asList();
        System.out.println(underpaid);
        System.out.println(datastore.createQuery(Employee.class).countAll());
        //第二种 我喜欢第二种
        underpaid = datastore.createQuery(Employee.class)
                .filter("name =", null)
                .asList();
        System.out.println(underpaid);
    }

    /**
     * 添加document
     * @param datastore 数据库连接
     */
    private void addDocument(Datastore datastore) {
        //添加document
        final Employee user = new Employee("zhansan", 10000000.0);
        datastore.save(user);
    }
}
