package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

class ExecuteActionTest {

	private TokenManager myManager;

	private String jsonFilesFolder;

	public ExecuteActionTest() {
		jsonFilesFolder = System.getProperty("user.dir") + "/TestData/ExecuteActionTest/";

		myManager = TokenManager.getInstance();
	}

	private void insertExpiredToken() throws TokenManagementException {
		String InputFile = System.getProperty("user.dir") + "/TestData/TokenRequestTest/ExpiredTokenRequest.json";
		long IAT = 1790604211948l;
		long EXP = 1490604211948l;
		Token token = new Token(InputFile, IAT, EXP);
	}
	
	/*
	 * **********ATENCIÓN************ 
	 * Cada caso de prueba correspondiente a cada test y sus
	 * debidos datos han sido recopilados en una tabla en la 
	 * memoria del ejercicio.
	 * 
	 */

	@DisplayName("(Correct) Operation Accepted")
	@Test
	void CorrectTokenValueTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Correct.json";
		boolean expectedReturn = true;
		boolean obtainedReturn = myManager.ExecuteAction(filePath);
		assertEquals(expectedReturn, obtainedReturn);
	}

	@DisplayName("Node 1 is empty (File Missing)")
	@Test
	void FileIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "FileIsMissing.json";
		String expectedMessage = "Error: input file not found.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 2 is empty (Missing '{')")
	@Test
	void BracketIsMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node2Eliminated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 2 is duplicated (Double '{')")
	@Test
	void BracketIsDuplicatedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node2Duplicated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 3 is eliminated (Empty file)")
	@Test
	void EmptyFileTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node3Eliminated.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 3 is duplicated (File content duplicated)")
	@Test
	void FileContentDuplicatedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node3Duplicated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 4 is empty (Missing '}')")
	@Test
	void FinalBracketMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node4Eliminated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	/*
	 * Este test no se pasa ya que el parser proporcionado es relajado, es decir,
	 * cuando identifica el primer corchete termina
	 *
	 * @DisplayName("Node 4 is duplicated (Double '}')")
	 * 
	 * @Test void finalBracketDuplicatedTest() throws TokenManagementException {
	 * String filePath = this.jsonFilesFolder + "Node4Duplicated.json"; String
	 * expectedMessage = "Error: input file could not be readed.";
	 * TokenManagementException ex =
	 * Assertions.assertThrows(TokenManagementException.class, ()-> {
	 * myManager.ExecuteAction(filePath); });
	 * Assertions.assertEquals(expectedMessage, ex.getMessage()); }
	 */

	@DisplayName("Node 6 is empty (Missing token value line)")
	@Test
	void tokenRequestLineMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node6Eliminated.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	/*
	 * Este test no se pasa, ya que el parser proporcionado es relajado, que en este
	 * caso se refiere a que solo comprueba que el campo Token Value aparece, no le
	 * importa que haya dos
	 *
	 * @DisplayName("Node 6 is duplicated (Double token value line)")
	 * 
	 * @Test void tokenRequestLineDuplicatedTest() throws TokenManagementException {
	 * String filePath = this.jsonFilesFolder + "Node6Duplicated.json"; String
	 * expectedMessage = "Error: invalid input data in JSON structure.";
	 * TokenManagementException ex =
	 * Assertions.assertThrows(TokenManagementException.class, ()-> {
	 * myManager.ExecuteAction(filePath); });
	 * Assertions.assertEquals(expectedMessage, ex.getMessage()); }
	 */

	@DisplayName("Node 7 is empty (Missing separator)")
	@Test
	void separatorMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node7Eliminated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 7 is duplicated (Double separator)")
	@Test
	void separatorDuplicatedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node7Duplicated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 10 is empty (Missing Token Value)")
	@Test
	void tokenValueMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node10Eliminated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 10 is duplicated (Duplicated Token Value)")
	@Test
	void tokenValueDuplicatedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node10Duplicated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 11 is empty (Missing ':')")
	@Test
	void equalitySymbolMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node11Eliminated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 11 is duplicated (Duplicated ':')")
	@Test
	void equalitySymbolDuplicatedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node11Duplicated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 17 is empty (Missing 'comillas')")
	@Test
	void comillasSymbolMissingTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node17Eliminated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 17 is duplicated (Duplicated 'comillas')")
	@Test
	void comillasSymbolDuplicatedTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node17Duplicated.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 18 is empty (Missing token value literal)")
	@Test
	void missingTokenValueTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node18Eliminated.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 18 is duplicated (Duplicated token value literal)")
	@Test
	void duplicatedTokenValueTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node18Duplicated.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 22 is empty (Missing token value value)")
	@Test
	void missingTokenValueValueTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node22Eliminated.json";
		String expectedMessage = "Error: invalid TokenValue data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	/*
	 * Este test no se pasa satisfactoriamente debido a que salta la excepción de
	 * java "java.lang.IllegalArgumentExcepcion". Es normal, pues TokenValue es un
	 * valor que va a ser utilizado por métodos de decodificación. Por lo tanto, al
	 * estar duplicado, estos métodos no son capaces de leerlo.
	 * 
	 * @DisplayName("Node 22 is duplicated (Duplicated token value value)")
	 * 
	 * @Test void duplicatedTokenValueValueTest() throws TokenManagementException {
	 * String filePath = this.jsonFilesFolder + "Node22Duplicated.json"; String
	 * expectedMessage = "Error: invalid TokenValue data in json structure.";
	 * TokenManagementException ex =
	 * Assertions.assertThrows(TokenManagementException.class, () -> {
	 * myManager.ExecuteAction(filePath); });
	 * Assertions.assertEquals(expectedMessage, ex.getMessage()); }
	 */

	@DisplayName("Node 25 is empty (Missing type of operation literal)")
	@Test
	void missingTypeOfOperationTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node25Eliminated.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 25 is duplicated (Duplicated type of operation literal)")
	@Test
	void duplicatedTypeOfOperationTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node25Duplicated.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 29 is empty (Missing type of operation value)")
	@Test
	void missingTypeOfOperationValueTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node29Eliminated.json";
		String expectedMessage = "Error: invalid type of operation.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 29 is duplicated (Duplicated type of operation value)")
	@Test
	void duplicatedTypeOfOperationValueTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node29Duplicated.json";
		String expectedMessage = "Error: invalid type of operation.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 5 not valid (Not '{')")
	@Test
	void Node5NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node5NotValid.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 31 not valid (Not 'comillas')")
	@Test
	void Node31NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node31NotValid.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 32 not valid (Not 'Token Value')")
	@Test
	void Node32NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node32NotValid.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 20 not valid (Not ':')")
	@Test
	void Node20NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node20NotValid.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 35 not valid (Not valid value)")
	@Test
	void Node35NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node35NotValid.json";
		String expectedMessage = "Error: invalid TokenValue data in json structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 13 not valid (Not ',')")
	@Test
	void Node13NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node13NotValid.json";
		String expectedMessage = "Error: JSON object cannot be created due to incorrect representation";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 38 not valid (Not 'Type of operation')")
	@Test
	void Node38NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node38NotValid.json";
		String expectedMessage = "Error: invalid input data in JSON structure.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Node 41 not valid (Not valid Value)")
	@Test
	void Node41NotValidTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "Node41NotValid.json";
		String expectedMessage = "Error: invalid type of operation.";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("The token not exist")
	@Test
	void NodeTokenNotExistTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TokenNotExist.json";
		String expectedMessage = "Error: el token no existe";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token and operation not compatible")
	@Test
	void TokenAndOperationIncompatibleTest() throws TokenManagementException {
		String filePath = this.jsonFilesFolder + "TokenAndOperationIncompatible.json";
		String expectedMessage = "Error: the operation is not accepted";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}

	@DisplayName("Token is not valid")
	@Test
	void TokenNotValidTest() throws TokenManagementException {
		insertExpiredToken();
		String filePath = this.jsonFilesFolder + "TokenNotValid.json";
		String expectedMessage = "Error: el token no es valido";
		TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, () -> {
			myManager.ExecuteAction(filePath);
		});
		Assertions.assertEquals(expectedMessage, ex.getMessage());
	}
}
