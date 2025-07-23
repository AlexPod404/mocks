package sb1.stub.armsbmock.data;

/**
 * JSON responses for CTI subsystem
 * Migrated from armsb_cti service
 */
public class CtiJsonData {
    
    public static String errorRequest() {
        return "{\n" +
                "    \"status\": \"Error\",\n" +
                "    \"comment\": \"Need signature in request. Требуется сигнатура в запросе\"\n" +
                "}";
    }

    public static String getCommunications() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"communicationId\": \"comm-001\",\n" +
                "      \"type\": \"PHONE_CALL\",\n" +
                "      \"direction\": \"OUTBOUND\",\n" +
                "      \"phoneNumber\": \"+7 (495) 123-45-67\",\n" +
                "      \"duration\": 120,\n" +
                "      \"status\": \"COMPLETED\",\n" +
                "      \"startTime\": \"2024-01-01T10:00:00Z\",\n" +
                "      \"endTime\": \"2024-01-01T10:02:00Z\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"communicationId\": \"comm-002\",\n" +
                "      \"type\": \"PHONE_CALL\",\n" +
                "      \"direction\": \"INBOUND\",\n" +
                "      \"phoneNumber\": \"+7 (495) 987-65-43\",\n" +
                "      \"duration\": 180,\n" +
                "      \"status\": \"COMPLETED\",\n" +
                "      \"startTime\": \"2024-01-01T11:00:00Z\",\n" +
                "      \"endTime\": \"2024-01-01T11:03:00Z\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getClientPhones() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"phoneId\": \"phone-001\",\n" +
                "      \"clientId\": \"client-001\",\n" +
                "      \"phoneNumber\": \"+7 (495) 123-45-67\",\n" +
                "      \"type\": \"MOBILE\",\n" +
                "      \"isPrimary\": true,\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"phoneId\": \"phone-002\",\n" +
                "      \"clientId\": \"client-001\",\n" +
                "      \"phoneNumber\": \"+7 (495) 987-65-43\",\n" +
                "      \"type\": \"HOME\",\n" +
                "      \"isPrimary\": false,\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String initCall() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"callId\": \"call-001\",\n" +
                "    \"sessionId\": \"session-12345\",\n" +
                "    \"status\": \"INITIALIZED\",\n" +
                "    \"phoneNumber\": \"+7 (495) 123-45-67\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\",\n" +
                "    \"message\": \"Call initialized successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String addressBook() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"employeeNumber\": \"12345\",\n" +
                "      \"fullName\": \"Иванов Иван Иванович\",\n" +
                "      \"department\": \"VIP Banking\",\n" +
                "      \"phones\": [\n" +
                "        {\n" +
                "          \"type\": \"WORK\",\n" +
                "          \"number\": \"+7 (495) 111-11-11\",\n" +
                "          \"extension\": \"1234\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"MOBILE\",\n" +
                "          \"number\": \"+7 (985) 222-22-22\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String employee() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"employeeNumber\": \"12345\",\n" +
                "    \"firstName\": \"Иван\",\n" +
                "    \"lastName\": \"Иванов\",\n" +
                "    \"middleName\": \"Иванович\",\n" +
                "    \"position\": \"CTI Operator\",\n" +
                "    \"department\": \"Call Center\",\n" +
                "    \"status\": \"ACTIVE\"\n" +
                "  }\n" +
                "}";
    }

    public static String positionsGet() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"positionId\": \"cti-pos-001\",\n" +
                "      \"name\": \"CTI Operator\",\n" +
                "      \"level\": \"JUNIOR\",\n" +
                "      \"department\": \"Call Center\",\n" +
                "      \"permissions\": [\"MAKE_CALLS\", \"RECEIVE_CALLS\", \"TRANSFER_CALLS\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"positionId\": \"cti-pos-002\",\n" +
                "      \"name\": \"Senior CTI Operator\",\n" +
                "      \"level\": \"SENIOR\",\n" +
                "      \"department\": \"Call Center\",\n" +
                "      \"permissions\": [\"MAKE_CALLS\", \"RECEIVE_CALLS\", \"TRANSFER_CALLS\", \"MONITOR_CALLS\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String setNotification() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"notificationId\": \"notif-001\",\n" +
                "    \"callId\": \"call-001\",\n" +
                "    \"status\": \"NOTIFICATION_SET\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\",\n" +
                "    \"message\": \"Notification set successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String pprbNotification() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"notificationId\": \"pprb-notif-001\",\n" +
                "    \"type\": \"PPRB_NOTIFICATION\",\n" +
                "    \"status\": \"SENT\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\",\n" +
                "    \"message\": \"PPRB notification sent successfully\"\n" +
                "  }\n" +
                "}";
    }
}