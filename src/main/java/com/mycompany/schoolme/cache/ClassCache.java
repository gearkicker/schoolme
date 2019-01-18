package com.mycompany.schoolme.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.mycompany.schoolme.domain.SchoolClass;

public class ClassCache {

  // Hashmap is concurrent so future update methods will work
  static final Map<String, String> classes = new ConcurrentHashMap<>();

  public static final void load(Map<String, String> classes) {
    classes.forEach((k, v) -> ClassCache.classes.put(k, v));
  }

  public static SchoolClass getByClassId(String classId) {
    String name = classes.get(classId);
    
    if (name != null) {
      return new SchoolClass(classId, name);
    }
    return null;
  }
}
