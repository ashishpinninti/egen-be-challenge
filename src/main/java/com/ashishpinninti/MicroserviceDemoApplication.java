package com.ashishpinninti;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.MongoClient;

@SpringBootApplication
public class MicroserviceDemoApplication {
	private static Morphia morphia;
	private static Datastore datastore;

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(MicroserviceDemoApplication.class, args);
		morphia = new Morphia();

		// tell morphia where to find your classes
		// can be called multiple times with different packages or classes
		morphia.mapPackage("com.ashishpinninti");

		// create the Datastore connecting to the database running on the
		// default port on the local host
		datastore = morphia.createDatastore(new MongoClient(),
				"morphia_example");
		datastore.getDB().dropDatabase();
		datastore.ensureIndexes();
	}

	public static Morphia getMorphia() {
		return morphia;
	}

	public static Datastore getDatastore() {
		return datastore;
	}
}
