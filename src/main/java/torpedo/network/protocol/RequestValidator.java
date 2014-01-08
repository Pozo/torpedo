package torpedo.network.protocol;
/**
 * RequestValidator.
 * @author Zoltan_Polgar
 *
 */
public class RequestValidator {

    private String request;

    /**
     * RequestValidator.
     * @param request request
     */
    public RequestValidator(String request) {
        this.request = request;
    }
    /**
     * validate.
     * @return true is the request is valid
     */
    public boolean validate() {
        return isLengthValid() && isAvailableProcedure(request) && hasParameters(request);
    }
    private boolean isLengthValid() {
        return request.length() != 0;
    }
    private boolean isAvailableProcedure(String request) {
        return request.toUpperCase().startsWith(Procedures.FIRE.name()) || request.toUpperCase().startsWith(Procedures.GREETING.name());
    }

    private boolean hasParameters(String request) {
        return request.split(" ").length > 0;
    }
}
