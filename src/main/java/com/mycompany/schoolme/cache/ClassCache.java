package com.mycompany.schoolme.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.mycompany.schoolme.domain.SchoolClass;

/**
 * Cache that holds the data relating to classes.
 */
public class ClassCache {

  // Hashmap is concurrent so future update methods will work
  static final Map<String, String> classes = new ConcurrentHashMap<>();

  /**
   * Load the cache with a map consisting of a class id, class name  pair.
   * 
   * @param classes the classes to load into the hash map
   */
  public static final void load(Map<String, String> classes) {
    classes.forEach((k, v) -> ClassCache.classes.put(k, v));
  }

  /**
   * Get a class by class id
   * 
   * @param classId the class id
   * @return a <tt>StudentClass</tt> consisting of a class id and a class name
   */
  public static SchoolClass getByClassId(String classId) {
    String name = classes.get(classId);
    
    if (name != null) {
      return new SchoolClass(classId, name);
    }
    return null;
  }
}
