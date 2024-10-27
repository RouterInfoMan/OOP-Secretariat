
# OOP-Secretariat

This homework implements a system for managing students, courses, and allocating students to their preferred courses.




## API Documentation

Using the API requires creating a ```Secretariat``` object first and calling the following methods through it.


### Method: `addStudent`

Adds a new student to the system.

```java
public void addStudent(String type, String name) throws ExceptionDuplicateStudent, ExceptionInvalidDegree
```

- **Parameters:**
  - `type` (String): The type of degree ("licenta" or "master").
  - `name` (String): The name of the student.

- **Exceptions:**
  - `ExceptionDuplicateStudent`: Thrown if a student with the same name already exists.
  - `ExceptionInvalidDegree`: Thrown if an invalid degree type is provided.

### Method: `addCourse`

Adds a new course to the system.

```java
public void addCourse(String type, String name, int max_size)
```

- **Parameters:**
  - `type` (String): The type of degree ("licenta" or "master").
  - `name` (String): The name of the course.
  - `max_size` (int): The maximum size of the course.

### Method: `readAverages`

Reads average scores from files and updates student averages.

```java
public void readAverages(String folder)
```

- **Parameters:**
  - `folder` (String): The folder path where average files are located.

### Method: `getAverages`

Returns a sorted list of students along with their average scores.

```java
public String getAverages()
```

### Method: `updateAverage`

Updates the average score of a specific student.

```java
public void updateAverage(String name, float average)
```

- **Parameters:**
  - `name` (String): The name of the student.
  - `average` (float): The new average score.

### Method: `addPreferences`

Adds course preferences for a student.

```java
public void addPreferences(String[] args)
```

- **Parameters:**
  - `args` (String[]): An array containing the student's name and course preferences.

### Method: `allocate`

Allocates students to their preferred courses based on average scores.

```java
public void allocate()
```

### Method: `getCourse`

Returns information about a specific course.

```java
public String getCourse(String name)
```

- **Parameters:**
  - `name` (String): The name of the course.

### Method: `getStudent`

Returns information about a specific student.

```java
public String getStudent(String name)
```

- **Parameters:**
  - `name` (String): The name of the student.

## Optimizations

- I found that ```Hashtables``` may be the the best structure to use for indexing and retrieving objects fast.  
- ```SortedSets``` may have been an option compared to ```ArrayLists``` but they are difficult to update considering the fact that a student's data may be changed.
- ```SortedSets``` work on a balanced RB Tree, thus adding and removing an element won't trigger a whole tree sort.
- ```ArrayLists``` provide an useful API for sorting data with the ```Comparator``` class and ```Comparable``` interface and has been used in method calls for fetching data.
- Added an additional test to check if students are allocated correctly in case all the preferences are occupied.

