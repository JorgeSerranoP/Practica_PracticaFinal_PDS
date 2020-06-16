package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

class RevokeTokenTest {

	private TokenManager myManager;

	private String jsonFilesFolder;

	public RevokeTokenTest() {
		jsonFilesFolder = System.getProperty("user.dir") + "/TestData/RevokeTokenTest/";
		myManager = TokenManager.getInstance();
	}

	private void insertCorrectToken() throws TokenManagementException {
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestGenerationTest/Correct1.json";
		myManager.TokenRequestGeneration(InputFile);
		InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectToken.json";
		myManager.RequestToken(InputFile);

	}

	private void insertTokenVL1() throws TokenManagementException {
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectTokenRequest.json";
		myManager.RequestToken(InputFile);
	}

	private void insertTokenVL2() throws TokenManagementException {
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestGenerationTest/Correct2.json";
		myManager.TokenRequestGeneration(InputFile);
		InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectTokenRequest2.json";
		myManager.RequestToken(InputFile);
	}

	private void insertTokenVL99() throws TokenManagementException {
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestGenerationTest/Correct3.json";
		myManager.TokenRequestGeneration(InputFile);
		InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectTokenRequest3.json";
		myManager.RequestToken(InputFile);
	}

	private void insertTokenVL100() throws TokenManagementException {
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestGenerationTest/Correct4.json";
		myManager.TokenRequestGeneration(InputFile);
		InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/CorrectTokenRequest4.json";
		myManager.RequestToken(InputFile);

	}

	private void insertExpiredToken() throws TokenManagementException {
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/ExpiredTokenRequest.json";
		Token token = new Token(InputFile, 1450604211238l, 1770604212248l);
	}

	/*
	 * **********ATENCIÓN************ 
	 * Cada caso de prueba correspondiente a cada
	 * test y sus debidos datos han sido recopilados 
	 * en una tabla en la memoria del ejercicio.
	 * 
	 */

	@DisplayName("Correct Token Generation")
	@Test
	void CorrectTokenValueTest() throws TokenManagementException {
		insertCorrectToken();
		String filePath = this.jsonFilesFolder + "Correct.json";
		String expectedToken = "autonomous@vehicle.com";
		String obtainedToken = myManager.RevokeToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}

	@DisplayName("File is missing")
	@Test
	void FileIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "FileIsMissing.json";
		String expectedMessage = "Error: input file not found.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Brackets are missing")
	@Test
	void BracketsAreMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "BracketsAreMissing.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Bracket is duplicated")
	@Test
	void BracketIsDuplicatedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "BracketIsDuplicated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Bracket is missing")
	@Test
	void BracketIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "BracketIsMissing.json";
		String expectedMessage = "Error: input file not found.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token Value is missing")
	@Test
	void TokenValueIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TokenValueIsMissing.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token Value value is missing")
	@Test
	void TokenValueValueIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TokenValueValueIsMissing.json";
		String expectedMessage = "Error: invalid TokenValue data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token Value value not valid")
	@Test
	void TokenValueValueNotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TokenValueValueNotValid.json";
		String expectedMessage = "Error: invalid TokenValue data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Type of revocation is missing")
	@Test
	void TypeOfRevocationIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TypeOfRevocationIsMissing.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Type of revocation value is missing")
	@Test
	void TypeOfRevocationValueIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TypeOfRevocationValueIsMissing.json";
		String expectedMessage = "Error: invalid type of revocation.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Type of revocation value not valid")
	@Test
	void TypeOfRevocationValueNotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TypeOfRevocationValueNotValid.json";
		String expectedMessage = "Error: invalid type of revocation.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Reason is missing")
	@Test
	void ReasonIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "ReasonIsMissing.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Reason value is missing")
	@Test
	void ReasonValueIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "ReasonValueIsMissing.json";
		String expectedMessage = "Error: invalid Reason data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Reason value not valid")
	@Test
	void ReasonValueNotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "ReasonValueNotValid.json";
		String expectedMessage = "Error: invalid Reason data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Reason length not valid")
	@Test
	void ReasonLengthNotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "ReasonLengthNotValid.json";
		String expectedMessage = "Error: invalid Reason data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token Value not in TokensStore")
	@Test
	void TokenValueNotInTokensStoreTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TokenValueNotInTokensStore.json";
		String expectedMessage = "Error: token not in tokensStore";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});

		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token Value is Expired")
	@Test
	void TokenValueExpiredTest() throws TokenManagementException {
		insertExpiredToken();
		String filePath = this.jsonFilesFolder + "TokenValueExpired.json";
		String expectedMessage = "Error: the token has expired";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token Value has been previously revoked")
	@Test
	void TokenValueRevokedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TokenValueRevoked.json";
		String expectedMessage = "Error: the token has been previosuly revoked";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("VLReason1")
	@Test
	void VLReason1Test() throws TokenManagementException {
		insertTokenVL1();
		String filePath = this.jsonFilesFolder + "VLReason1.json";
		String expectedToken = "autonomous@vehicle.com";
		String obtainedToken = myManager.RevokeToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}

	@DisplayName("VLReason2")
	@Test
	void VLReason2Test() throws TokenManagementException {
		insertTokenVL2();
		String filePath = this.jsonFilesFolder + "VLReason2.json";
		String expectedToken = "autonomous22@vehicle.com";
		String obtainedToken = myManager.RevokeToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}

	@DisplayName("VLReason99")
	@Test
	void VLReason99Test() throws TokenManagementException {
		insertTokenVL99();
		String filePath = this.jsonFilesFolder + "VLReason99.json";
		String expectedToken = "autonomous@vehicle.com";
		String obtainedToken = myManager.RevokeToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}

	@DisplayName("VLReason100")
	@Test
	void VLReason100Test() throws TokenManagementException {
		insertTokenVL100();
		String filePath = this.jsonFilesFolder + "VLReason100.json";
		String expectedToken = "selfdriving@vehicles.com";
		String obtainedToken = myManager.RevokeToken(filePath);
		assertEquals(expectedToken, obtainedToken);
	}

	@DisplayName("VLReason101")
	@Test
	void VLReason101Test() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "VLReason101.json";
		String expectedMessage = "Error: invalid Reason data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.RevokeToken(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

}
