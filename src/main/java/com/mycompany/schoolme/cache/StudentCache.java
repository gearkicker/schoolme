package com.mycompany.schoolme.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import com.mycompany.schoolme.domain.Student;

/**
 * Cache that holds the data relating to students.
 */
public class StudentCache {

  private static final ListMultimap<String, Integer> mapByFirst = ArrayListMultimap.create();
  private static final ListMultimap<String, Integer> mapByLast = ArrayListMultimap.create();
  static final Map<Integer, Student> students = new ConcurrentHashMap<>();

  /**
   * Loads the data from a list of <tt>Student</tt> objects.
   * 
   * @param students a list of Students
   * @see Student
   */
  public static final void load(List<Student> students) {
    AtomicInteger studentId = new AtomicInteger(0);
    students.stream().forEach(s -> {
      s.setId(studentId.intValue());
      StudentCache.students.put(studentId.intValue(), s);
      Multimaps.synchronizedListMultimap(StudentCache.mapByFirst).put(s.getFirst().toLowerCase(),
          studentId.intValue());
      Multimaps.synchronizedListMultimap(StudentCache.mapByLast).put(s.getLast().toLowerCase(),
          studentId.intValue());
      studentId.incrementAndGet();
    });
  }

  /**
   * Get a student by id
   * 
   * @param id the id of the student
   * @return the student that matches the id
   */
  public static Student get(Integer id) {
    return students.get(id);
  }

  /**
   * Get all students with the first name <tt>first</tt>
   * 
   * @param first the first name to match against
   * @return list of all students matching by the first name
   */
  public static List<Student> getByFirst(String first) {
    List<Student> students = new ArrayList<>();
    if (StudentCache.mapByFirst.containsKey(first.toLowerCase())) {
      StudentCache.mapByFirst.get(first.toLowerCase()).forEach(id -> {
        students.add(StudentCache.students.get(id));
      });
    }
    return students;
  }

  /**
   * Get all students with the last name <tt>last</tt>
   * 
   * @param last the last name to match against
   * @return list of all students matching by the last name
   */
  public static List<Student> getByLast(String last) {
    List<Student> students = new ArrayList<>();
    if (StudentCache.mapByLast.containsKey(last.toLowerCase())) {
      StudentCache.mapByLast.get(last.toLowerCase()).forEach(id -> {
        students.add(StudentCache.students.get(id));
      });
    }
    return students;
  }

  /**
   * Get all students that match against <tt>first</tt> and <tt>last</tt> names
   * @param first first name of the student to match by
   * @param last last name of the student to match by
   * @return a list of students matching by first and last names
   */
  public static List<Student> getByFirstLast(String first, String last) {
    List<Student> students = new ArrayList<>();
    if (StudentCache.mapByFirst.containsKey(first.toLowerCase())) {
      StudentCache.mapByFirst.get(first.toLowerCase()).forEach(id -> {
        if (StudentCache.students.get(id).getLast().equalsIgnoreCase(last))
          students.add(StudentCache.students.get(id));
      });
    }
    return students;
  }
}
