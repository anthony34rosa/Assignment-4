import java.util.LinkedList;
import java.util.Objects;

public class CourseDBElement implements Comparable<CourseDBElement> {

	// Attributes of the CourseDBElement class
	private String courseID;
	private int CRN;
	private int NumOfCredits;
	private String RoomNum;
	private String InstructorName;

	// Default constructor initializing attributes to default values
	public CourseDBElement() {
		this.courseID = null;
		this.CRN = 0;
		this.NumOfCredits = 0;
		this.RoomNum = null;
		this.InstructorName = null;
	}

	// Parameterized constructor to assign values to attributes
	public CourseDBElement(String courseID, int CRN, int nofCredits, String roomN, String instructorName) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.NumOfCredits = nofCredits;
		this.RoomNum = roomN;
		this.InstructorName = instructorName;
	}

    // setter and getter methods 
	public String getID() {
		return courseID;
	}

	public void setID(String iD) {
		courseID = iD;
	}

	public int getCRN() {
		return CRN;
	}

	public void setCRN(int CRN) {
		CRN = CRN;
	}

	public int getNumOfCredits() {
		return NumOfCredits;
	}

	public void setNumOfCredits(int nofCredits) {
		NumOfCredits = nofCredits;
	}

	public String getRoomNum() {
		return RoomNum;
	}

	public void setRoomNum(String roomNum) {
		RoomNum = roomNum;
	}

	public String getInstructorName() {
		return InstructorName;
	}

	public void setInstructorName(String instructorName) {
		InstructorName = instructorName;
	}

	/**
	 * Compares two CourseDBElement objects based on their Course Reference Number
	 * (CRN).
	 * 
	 * @param element The CourseDBElement to compare with.
	 * @return 0 if the CRNs are equal, -1 if this CRN is smaller, 1 if this CRN is
	 *         greater.
	 */
	@Override
	public int compareTo(CourseDBElement element) {
		if (this.CRN == element.getCRN()) {
			return 0;
		} else if (this.CRN < element.getCRN()) {
			return -1;
		} else {
			return 1;
		}
	}

	// Overriding the toString method to represent the object as a string

	/**
	 * Converts the CourseDBElement object into a string representation.
	 * 
	 * @return A string representing the CourseDBElement.
	 */
	@Override
	public String toString() {
		return "\nCourse:" + this.courseID + " CRN:" + this.CRN + " Credits:" + this.NumOfCredits + " Instructor:"
				+ this.InstructorName + " Room:" + this.RoomNum;
	}

}
