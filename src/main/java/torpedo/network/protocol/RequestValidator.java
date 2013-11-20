package torpedo.network.protocol;

public class RequestValidator {
	
	private String request;

	public RequestValidator(String request) {
		this.request = request;
	}
	
	public boolean validate() {
		if(request.length() == 0) {
			return false;
		}
		return isAvailableProcedure(request) && hasParameters(request);		
	}

	private boolean isAvailableProcedure(String request) {
		if(request.toUpperCase().startsWith(Procedures.FIRE.name()) || request.toUpperCase().startsWith(Procedures.GREETING.name())) {
			return true;
		}
		return false;
	}

	private boolean hasParameters(String request) {
		if(request.split(" ").length > 0) {
			return true;
		} else {
			return false;
		}
	}
}
