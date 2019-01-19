package com.mycompany.schoolme.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.schoolme.domain.SchoolDB;

/**
 * A cache holding the schools data and methods to import the data into the cache.
 */
public class Cache {

  /**
   * Initializes the cache.
   * 
   * @throws IOException an exception if there are issues loading the data
   * @see StudentCache
   * @see ClassCache
   */
  public static void init() throws IOException{
    SchoolDB storedTable = getData();
    StudentCache.load(storedTable.getStudents());
    ClassCache.load(storedTable.getClasses());
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
