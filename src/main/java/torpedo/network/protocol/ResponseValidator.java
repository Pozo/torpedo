package torpedo.network.protocol;

public class ResponseValidator {
	private String response;

	public ResponseValidator(String response) {
		this.response = response;
	}
	
	public boolean validate() {
		if(response.length() == 0) {
			return false;
		}
		return isAvailableResponse(response);
	}

	private boolean isAvailableResponse(String response) {
		for (FireResultType fireResultType : FireResultType.values()) {
			if(fireResultType.name().toUpperCase().equals(response.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
}
