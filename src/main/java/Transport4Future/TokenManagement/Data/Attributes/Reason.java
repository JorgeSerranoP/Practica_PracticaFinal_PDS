package Transport4Future.TokenManagement.Data.Attributes;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class Reason extends Attribute {
	public Reason(String Value) throws TokenManagementException {
		this.pattern = "([A-Za-z0-9\\s]{1,100})";
		this.errorMessage = "Error: invalid Reason data in json structure.";
		this.value = validate(Value);
	}
}
