import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {

	// Load factor to manage resizing of the hash table
	double load_factor = 1.5;

	// Number of elements in the CourseDBStructure
	private int size;

	// ArrayList to store string representations of elements in the hash table
	ArrayList<String> stri;

	// Size of the linked list used for the hash table buckets
	private int linkedlistsize;

	// Array of linked lists (buckets) to store CourseDBElement objects
	private LinkedList<CourseDBElement>[] bucket;

	/**
	 * Constructor to create a CourseDBStructure with a size calculated by the
	 * fourKPlusthree function.
	 * 
	 * @param n Size used for calculating the linked list size for the hash table
	 */
	public CourseDBStructure(int n) {
		// Calculate the linked list size using a hash table size determined by
		// fourKPlusthree
		linkedlistsize = fourKPlusthree(n, load_factor);
		// Initialize an array of linked lists to store CourseDBElement objects
		bucket = new LinkedList[linkedlistsize];
		// Initialize size counter
		size = 0;
		// Initialize an ArrayList to store string representations of elements
		stri = new ArrayList<String>();
	}

	/**
	 * Constructor to create a CourseDBStructure with a specified size and hash
	 * table.
	 * 
	 * @param str A string, possibly used for testing purposes
	 * @param n   Size of the linked list for the hash table
	 */
	public CourseDBStructure(String str, int n) {
		// Initialize the size of the linked list for the hash table
		linkedlistsize = n;
		// Initialize an array of linked lists to store CourseDBElement objects
		bucket = new LinkedList[linkedlistsize];
		// Initialize size counter
		size = 0;
		// Initialize an ArrayList to store string representations of elements
		stri = new ArrayList<String>();
	}

	/**
	 * Incorporates a CourseDBElement object into the CourseDBStructure, employing
	 * the hashcode derived from the CourseDatabaseElement object's crn value. If
	 * the CourseDatabaseElement already exists, the method quietly updates the
	 * existing object.
	 * 
	 * 
	 * @param element The CourseDBElement to be added to CourseDBStructure
	 */
	@Override
	public void add(CourseDBElement element) {
		// Flag to check if an element already exists
		boolean exist = false;
		// Retrieve the index based on the hashcode of the element
		int index = getelementhash(element);

		// Check if the bucket at the index is empty
		if (bucket[index] == null) {
			// If empty, create a new linked list and add the element
			bucket[index] = new LinkedList<CourseDBElement>();
			bucket[index].add(element);
			size++;
		} else {
			// Otherwise, iterate through the bucket elements
			boolean exist1 = false;
			for (CourseDBElement ele : bucket[index]) {
				// If the element already exists, update its attributes
				if (ele.compareTo(element) == 0) {
					ele.setID(element.getID());
					ele.setNumOfCredits(element.getNumOfCredits());
					ele.setRoomNum(element.getRoomNum());
					ele.setInstructorName(element.getInstructorName());
					exist1 = true;
					break;
				}
			}
			// If the element does not exist, add it to the bucket
			if (!exist1) {
				bucket[index].add(element);
				size++;
			}
		}
	}

	/**
	 * Retrieves a CourseDBElement based on the provided course registration number
	 * (crn). Searches for the CourseDBElement using its hashed index, and if found,
	 * returns it. If not found, an IOException is thrown.
	 * 
	 * @param crn The course registration number to search for
	 * @return The CourseDBElement associated with the provided crn
	 * @throws IOException if the requested course registration number is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		// Getting the hashed index for the specified crn
		int index = getCRNhash(crn);

		// Check if the index exists in the bucket
		if (bucket[index] != null) {
			// Loop through the elements at the specified index
			for (int i = 0; i < bucket[index].size(); i++) {
				// Check if the current element's CRN matches the given crn
				if (bucket[index].get(i).getCRN() == (int) crn) {
					return bucket[index].get(i); // Return the matched CourseDBElement
				}
			}
		}

		throw new IOException(); // Throw IOException if no matching CourseDBElement is found
	}

	/**
	 * Returns an ArrayList containing string representations of each course in the
	 * data structure. Each course's representation is separated by a new line. The
	 * format for each course's details includes: Course, CRN, Credits, Instructor,
	 * and Room. For example: Course:CMSC100 CRN:12345 Credits:3 Instructor:Sally
	 * InParticular Room:SW203 Course:CMSC800 CRN:67890 Credits:4 Instructor:John
	 * Room:SV100
	 * 
	 * @return An ArrayList of strings, each representing a course in the data
	 *         structure
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> str = new ArrayList<>();

		// Iterate through the elements in the bucket to collect course information
		for (LinkedList<CourseDBElement> elements : bucket) {
			if (elements != null) {
				for (CourseDBElement element : elements) {
					str.add(element.toString()); // Adds each course's string representation to the ArrayList
				}
			}
		}

		return str; // Returns the ArrayList containing course details
	}

	/**
	 * Returns the size of the ConcordanceDataStructure (number of indexes in the
	 * array)
	 */
	@Override
	public int getTableSize() {
		return linkedlistsize;
	}

	public int getelementhash(CourseDBElement element) {
		int index = element.getCRN() % linkedlistsize;
		return index;
	}

	public int getCRNhash(int crn) {
		int index = crn % linkedlistsize;
		return index;
	}

	/**
	 * Determines the next prime number based on a given value and load factor. It
	 * starts the search for the next prime number from the provided value. It
	 * applies the provided load factor for the calculation.
	 *
	 * @param n          The value to start searching for the next prime from
	 * @param loadfactor The load factor used to calculate the prime number
	 * @return The next prime number based on the given value and load factor
	 */
	public static int fourKPlusthree(int n, double loadfactor) {
		boolean fkp3 = false;
		boolean aPrime = false;
		int prime = (int) (n / loadfactor);

		if (prime % 2 == 0) { // If the number is even, change it to the next odd number
			prime = prime + 1;
		}

		while (!fkp3) { // Continuously searching for the next 4k+3 prime
			while (!aPrime) { // Looking for a prime number
				int highDivisor = (int) (Math.sqrt(prime) + 0.5);
				boolean foundDivisor = false;
				for (int d = highDivisor; d > 1; d--) {
					if (prime % d == 0) {
						foundDivisor = true;
						break; // This number is not a prime
					}
				}

				if (foundDivisor) {
					prime = prime + 2; // Move to the next odd number
				} else {
					aPrime = true; // It's a prime number
				}
			}

			if ((prime - 3) % 4 == 0) {
				fkp3 = true; // Found the next 4k+3 prime
			} else {
				prime = prime + 2;
				aPrime = false;
			}
		}
		return prime; // The next 4k+3 prime number
	}
}
