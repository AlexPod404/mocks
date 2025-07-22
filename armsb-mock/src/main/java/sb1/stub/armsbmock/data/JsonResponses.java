package sb1.stub.armsbmock.data;

/**
 * Consolidated JSON responses for ARMSB mock services
 * Contains all unique response methods from the original services
 */
public class JsonResponses {
    
    public static String errorRequest() {
        return "{\n" +
                "    \"status\": \"Error\",\n" +
                "    \"comment\": \"Need signature in request. Требуется сигнатура в запросе\"\n" +
                "}";
    }
    
    public static String successResponse() {
        return "{\n" +
                "    \"status\": \"Success\",\n" +
                "    \"message\": \"Operation completed successfully\"\n" +
                "}";
    }
    
}