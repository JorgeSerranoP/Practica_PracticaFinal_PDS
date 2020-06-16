package Transport4Future.TokenManagement.Data;

import java.util.Base64;
import java.util.HashMap;

import Transport4Future.TokenManagement.Data.Attributes.Reason;
import Transport4Future.TokenManagement.Data.Attributes.TokenValue;
import Transport4Future.TokenManagement.Data.Attributes.TypeOfRevocation;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;
import Transport4Future.TokenManagement.IO.TokenValueRevokeParser;
import Transport4Future.TokenManagement.Store.TokensStore;

public class TokenValueRevoke {
	private TokenValue tokenValue;
	private TypeOfRevocation typeOfRevocation;
	private Reason reason;

	public TokenValueRevoke(String InputFile) throws TokenManagementException {
		TokenValueRevokeParser myParser = new TokenValueRevokeParser();
		HashMap<String, String> items = myParser.Parse(InputFile);
		this.tokenValue = new TokenValue(items.get(TokenValueRevokeParser.TOKEN_VALUE));
		this.typeOfRevocation = new TypeOfRevocation(items.get(TokenValueRevokeParser.TYPE_OF_REVOCATION));
		this.reason = new Reason(items.get(TokenValueRevokeParser.REASON));
	}

	public TokenValue getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(TokenValue tokenValue) {
		this.tokenValue = tokenValue;
	}

	public TypeOfRevocation getTypeOfRevocation() {
		return typeOfRevocation;
	}

	public void setTypeOfRevocation(TypeOfRevocation typeOfRevocation) {
		this.typeOfRevocation = typeOfRevocation;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public String Decode() {
		byte[] decodedBytes = Base64.getDecoder().decode(this.tokenValue.getValue());
		String decodedString = new String(decodedBytes);
		return decodedString.substring(decodedString.length() - 64, decodedString.length());
	}

	public String revoke() throws TokenManagementException {
		TokensStore myStore = TokensStore.getInstance();
		Token tokenFound = myStore.Find(this.Decode());

		if (tokenFound != null) {
			if (tokenFound.isValid()) {
				if (!tokenFound.getRevoked().equals(this.getTypeOfRevocation().getValue())) {
					tokenFound.setRevoked(this.getTypeOfRevocation().getValue());
					return tokenFound.getNotificationEmail();
				} else {
					throw new TokenManagementException("Error: the token has been previosuly revoked");
				}
			} else {
				throw new TokenManagementException("Error: the token has expired");
			}
		} else {
			throw new TokenManagementException("Error: token not in tokensStore");
		}
	}
}
