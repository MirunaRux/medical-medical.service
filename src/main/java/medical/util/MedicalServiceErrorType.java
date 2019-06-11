package medical.util;

/**
 *  Structure for sending error details to the client.
 */
public class MedicalServiceErrorType {

    private String error;
    private String message;

    public MedicalServiceErrorType(String error, String message){
        this.error = error;
        this.message = message;
    }

}