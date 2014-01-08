package torpedo.network.protocol;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * ResponseValidator.
 * @author Zoltan_Polgar
 *
 */
public class ResponseValidator {
    private String response;

    /**
     * ResponseValidator.
     * @param response response
     */
    public ResponseValidator(String response) {
        this.response = response;
    }
    /**
     * validate.
     * @return validate
     */
    public boolean validate() {
        return validateLength() || isAvailableResponse(response);
    }
    private boolean validateLength() {
        return response.length() == 0;
    }
    /**
     * isAvailableResponse.
     * @param response response
     * @return
     */
    private boolean isAvailableResponse(String response) {
        boolean available = false;

        EnumSet<FireResultType> enumset = EnumSet.allOf(FireResultType.class);
        Iterator<FireResultType> itarator = enumset.iterator();

        while (itarator.hasNext() && !available) {
            if (itarator.next().name().toUpperCase().equals(response.toUpperCase())) {
                available = true;
            }
        }
        return available;
    }
}
