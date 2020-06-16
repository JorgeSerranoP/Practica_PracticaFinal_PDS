package Transport4Future.TokenManagement.Data;

import java.util.Base64;
import java.util.HashMap;

import Transport4Future.TokenManagement.Data.Attributes.TokenValue;
import Transport4Future.TokenManagement.Data.Attributes.TypeOfOperation;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;
import Transport4Future.TokenManagement.IO.TokenValueActionParser;
import Transport4Future.TokenManagement.Store.TokensRequestStore;

public class TokenValueAction {
	private TokenValue tokenValue;
	private TypeOfOperation typeOfOperation;

	public TokenValueAction(String InputFile) throws TokenManagementException {
		TokenValueActionParser myParser = new TokenValueActionParser();
		HashMap<String, String> items = myParser.Parse(InputFile);
		this.tokenValue = new TokenValue(items.get(TokenValueActionParser.TOKEN_VALUE));
		this.typeOfOperation = new TypeOfOperation(items.get(TokenValueActionParser.TYPE_OF_OPERATION));
	}

	public TokenValue getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(TokenValue tokenValue) {
		this.tokenValue = tokenValue;
	}

	public TypeOfOperation getTypeOfOperation() {
		return typeOfOperation;
	}

	public void setTypeOfOperation(TypeOfOperation typeOfOperation) {
		this.typeOfOperation = typeOfOperation;
	}

	public String Decode() {
		byte[] decodedBytes = Base64.getDecoder().decode(this.tokenValue.getValue());
		String decodedString = new String(decodedBytes);
		String hash = decodedString.substring(decodedString.length() - 64, decodedString.length());
		return hash;
	}

	public boolean actionAcepted() throws TokenManagementException {
		Token token = new Token();
		boolean tokenExist = token.Decode(this.tokenValue.getValue());

		if (tokenExist == true) {
			String tokenValue = token.getDevice();

			TokensRequestStore myStore = TokensRequestStore.getInstance();
			TokenRequest tokenFound = myStore.Find(tokenValue);

			if (tokenFound != null) {
				if (token.isValid()) {
					if (this.typeOfOperation.getValue().equals("Send Information from Sensor")
							&& tokenFound.getTypeOfDevice().equals("Sensor")) {
						return true;
					} else if (this.typeOfOperation.getValue().equals("Send Information from Sensor")
							&& tokenFound.getTypeOfDevice().equals("Actuator")) {
						throw new TokenManagementException("Error: the operation is not accepted");
					} else if (this.typeOfOperation.getValue().equals("Send Request to Actuator")
							&& tokenFound.getTypeOfDevice().equals("Actuator")) {
						return true;
					} else if (this.typeOfOperation.getValue().equals("Send Request to Actuator")
							&& tokenFound.getTypeOfDevice().equals("Sensor")) {
						throw new TokenManagementException("Error: the operation is not accepted");
					} else if (this.typeOfOperation.getValue().equals("Check State")) {
						return true;
					}
				} else {
					throw new TokenManagementException("Error: el token no es valido");
				}

			} else {
				throw new TokenManagementException("Error: el token no existe");
			}
		}
		throw new TokenManagementException("Error: el token no existe");
	}

}
