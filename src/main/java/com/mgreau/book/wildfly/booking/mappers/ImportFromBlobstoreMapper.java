package com.mgreau.book.wildfly.booking.mappers;
/**
import java.util.logging.Logger;

import javax.naming.Context;

import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.mapreduce.DatastoreMutationPool;
import com.google.appengine.tools.mapreduce.Mapper;
import com.google.appengine.tools.mapreduce.inputs.BlobstoreInput;


 * 
 * This Mapper imports from a CSV file in the Blobstore. The CSV assumes it's in
 * the MaxMind format for cities, states, zipcodes and lat/long.
 * 
 * 
 * @author Ikai Lan
 * 
 
public class ImportFromBlobstoreMapper extends
		Mapper<BlobstoreInputStream, String, byte[]> {
	private static final Logger log = Logger
			.getLogger(ImportFromBlobstoreMapper.class.getName());

	@Override
	public void map(BlobstoreInputStream key) {

		String line = new String(key.toString());

		log.info("At offset: " ); //key.getOffset());
		log.info("Got value: " + line);

		// Line format looks like this:
		// 10644,"US","VA","Tazewell","24651",37.0595,-81.5220,559,276
		// We're also assuming no errant commas in this simple example

		String[] values = line.split(",");
		String state = values[2];
		String cityName = values[3];
		String zipcode = values[4];
		Double latitude = Double.parseDouble(values[5]);
		Double longitude = Double.parseDouble(values[6]);

		state = state.replaceAll("\"", "");
		cityName = cityName.replaceAll("\"", "");
		zipcode = zipcode.replaceAll("\"", "");

		if (!zipcode.isEmpty()) {
			Entity zip = new Entity("Zip", zipcode);
			zip.setProperty("state", state);
			zip.setProperty("city", cityName);
			zip.setProperty("latitude", latitude);
			zip.setProperty("longitute", longitude);

			Entity city = new Entity("City", cityName);
			city.setProperty("state", state);
			city.setUnindexedProperty("zip", zipcode);

			//DatastoreMutationPool mutationPool = this.getAppEngineContext(
			//		getContext()).getMutationPool();
			//mutationPool.put(zip);
			//mutationPool.put(city);
		}

	}
}**/