package Transport4Future.TokenManagement.IO;

import java.util.HashMap;

import javax.json.JsonObject;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public class TokenValueActionParser extends JSONParser implements ITokenManagementParser {
	public static final String TOKEN_VALUE = "Token Value";
	public static final String TYPE_OF_OPERATION = "Type of operation";

	public HashMap<String, String> Parse(String FileName) throws TokenManagementException {
		HashMap<String, String> items = new HashMap<String, String>();

		JsonObject jsonObject = (JsonObject) super.Parse(FileName);
		try {
			items.put(TOKEN_VALUE, jsonObject.getString(TOKEN_VALUE));
			items.put(TYPE_OF_OPERATION, jsonObject.getString(TYPE_OF_OPERATION));
		} catch (Exception pe) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		return items;
	}
}
