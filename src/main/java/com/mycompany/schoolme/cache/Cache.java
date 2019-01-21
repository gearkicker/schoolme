package com.mycompany.schoolme.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.schoolme.application.SchoolMeApp;
import com.mycompany.schoolme.domain.SchoolDB;

/**
 * A cache holding the schools data and methods to import the data into the cache.
 */
public class Cache {

  // Ensures the cache is only initialized once
  static final private AtomicBoolean initialized = new AtomicBoolean(false);
  static final Logger logger = LoggerFactory.getLogger(Cache.class);


  /**
   * Initializes the cache.
   * 
   * @see StudentCache
   * @see ClassCache
   */
  public static void init() {
    // Check to see if the cache has been initialized
    if (Cache.initialized.getAndSet(true) != true) {
      try {
        SchoolDB storedTable = getData();
        StudentCache.load(storedTable.getStudents());
        ClassCache.load(storedTable.getClasses());
      } catch (IOException e) {
        logger.error("Can not open student data file: " + e.getMessage());
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Load the data from the file system.
   * 
   * @return a {@link SchoolDB object} containing the school information
   * @throws IOException exception if there are issues reading the file
   * @see SchoolDB
   */
  private static SchoolDB getData() throws IOException {
    byte[] jsonData = Files.readAllBytes(Paths.get("students_classes.json"));
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(jsonData, SchoolDB.class);
  }


}
