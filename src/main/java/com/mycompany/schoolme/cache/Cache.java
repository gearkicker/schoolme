package com.mycompany.schoolme.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.schoolme.domain.SchoolDB;

public class Cache {

  public static void init() throws IOException{
    SchoolDB storedTable = getData();
    StudentCache.load(storedTable.getStudents());
    ClassCache.load(storedTable.getClasses());
  }
  
  private static SchoolDB getData() throws IOException {
    byte[] jsonData = Files.readAllBytes(Paths.get("students_classes.json"));
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(jsonData, SchoolDB.class);
  }

}
