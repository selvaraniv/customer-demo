package au.com.demo.customer.data;

public class CsvData {

	private String firstName;
	private String lastName;
	private String postCode;
	private int uniqueId;

	public CsvData(String firstName, String lastName, String postCode, int uniqueId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.postCode = postCode;
		this.uniqueId = uniqueId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

}
