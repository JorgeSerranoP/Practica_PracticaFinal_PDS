package Transport4Future.TokenManagement.Data.Attributes;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class TokenValue extends Attribute {
	public TokenValue(String Value) throws TokenManagementException {
		this.pattern = "([A-Za-z0-9-=]{1,})";
		this.errorMessage = "Error: invalid TokenValue data in json structure.";
		this.value = validate(Value);
	}
}
