import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
	CourseDBStructure element;
	CourseDBElement element1;
	private int size;
	
	public CourseDBManager() {
		size = 20;
		element = new CourseDBStructure(size);
		 }
	
	/**
	 * Incorporates a new course (CourseDBElement) using the provided information to CourseDBStructure.
	 * 
	 * @param id         The course identification
	 * @param crn        The course CRN
	 * @param credits    The number of credits
	 * @param roomNum    The classroom number
	 * @param instructor The name of the instructor
	 */

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
	    element1 = new CourseDBElement(id, crn, credits, roomNum, instructor);
	    try {
	        if (element != null) {
	            element.add(element1);
	        }
	    } catch (NullPointerException e) {
	        System.err.println("An error occurred while adding the course: " + e.getMessage());
	    }
	}


	/**
	 * Searches for a CourseDBElement based on the provided CRN key.
	 * 
	 * @param crn The course CRN (key) to be searched for.
	 * @return A CourseDBElement object associated with the provided key.
	 */

	public CourseDBElement get(int crn) {
	    try {
	        return element.get(crn);
	    } catch (IOException e) {
	        System.err.println("An error occurred while retrieving the course: " + e.getMessage());
	    }
	    return null;
	}
	/**
	 * Retrieves course information from a designated file and appends the details into the CourseDBStructure data structure.
	 * 
	 * @param input The input file containing course information.
	 * @throws FileNotFoundException if the specified file does not exist.
	 */

	public void readFile(File input) throws FileNotFoundException {
	    try (Scanner read = new Scanner(input)) {
	        if (!input.exists()) {
	            throw new FileNotFoundException("File not found");
	        }

	        while (read.hasNextLine()) {
	            String[] courses = read.nextLine().split(" ");
	            if (courses.length >= 5) {
	                add(courses[0], Integer.parseInt(courses[1]), Integer.parseInt(courses[2]), courses[3], courses[4]);
	            } else {
	                System.err.println("Incomplete course entry: " + String.join(" ", courses));
	            }
	        }
	    } catch (FileNotFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        System.err.println("An error occurred while reading the file: " + e.getMessage());
	    }
	}

	

	/**
	 * Returns an ArrayList containing string representations of each course within the data structure, separated by a new line.
	 * Illustrative examples:
	 * Course: CMSC100 CRN: 12345 Credits: 3 Instructor: someone: TC400
	 * Course: CMSC200 CRN: 67890 Credits: 4 Instructor: noone Room: SW500
	 *
	 * @return An ArrayList containing string representations of the courses.
	 */

	
	@Override
	public ArrayList<String> showAll() {

		
		return element.showAll();
	}
}
