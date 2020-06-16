package Transport4Future.Utils;

import Transport4Future.TokenManagement.Exceptions.TokenManagementException;

public interface IHash {
	public String Hash(String text) throws TokenManagementException;
}
