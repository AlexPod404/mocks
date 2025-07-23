package sb1.stub.armsbmock.data;

/**
 * JSON responses for Clients subsystem
 * Migrated from armsb_clients service
 */
public class ClientsJsonData {
    
    public static String errorRequest() {
        return "{\n" +
                "    \"status\": \"Error\",\n" +
                "    \"comment\": \"Need signature in request. Требуется сигнатура в запросе\"\n" +
                "}";
    }

    public static String getEmployees() {
        return "{\n" +
                "  \"HeaderInfo\": {\n" +
                "    \"@RqUID\": \"0d5b2475d287429a8e30925f1ddfde45\",\n" +
                "    \"@RqTm\": \"2021-02-03T07:55:50\",\n" +
                "    \"@SPName\": \"urn:sbrfsystems:99-crm\",\n" +
                "    \"@SystemId\": \"sbrfsystems:99-ufs\",\n" +
                "    \"@ErrorCode\": \"SUCCESS\",\n" +
                "    \"@ErrorMessage\": \"\"\n" +
                "  },\n" +
                "  \"ListOfEmployee\": [\n" +
                "    {\n" +
                "      \"EmployeeNumber\": \"65\",\n" +
                "      \"FirstName\": \"Григорий\",\n" +
                "      \"MiddleName\": \"Рамзанович\",\n" +
                "      \"LastName\": \"Роликов\",\n" +
                "      \"Profile\": \"Клиентский менеджер Сбербанк 1\",\n" +
                "      \"PositionId\": \"39-Fx22\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"EmployeeNumber\": \"21\",\n" +
                "      \"FirstName\": \"Сергей\",\n" +
                "      \"MiddleName\": \"Адамович\",\n" +
                "      \"LastName\": \"Роликов\",\n" +
                "      \"Profile\": \"Клиентский менеджер Сбербанк 1\",\n" +
                "      \"PositionId\": \"68-Fx22\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getCompanions() {
        return "{\n" +
                "  \"HeaderInfo\": {\n" +
                "    \"@RqUID\": \"00000000000000000000000000000000\",\n" +
                "    \"@RqTm\": \"2023-01-01T00:00:00\",\n" +
                "    \"@SPName\": \"urn:sbrfsystems:99-crm\",\n" +
                "    \"@SystemId\": \"sbrfsystems:99-ufs\",\n" +
                "    \"@ErrorCode\": \"SUCCESS\",\n" +
                "    \"@ErrorMessage\": \"\"\n" +
                "  },\n" +
                "  \"ListOfEmployee\": [\n" +
                "    {\n" +
                "      \"EmployeeNumber\": \"1\",\n" +
                "      \"FirstName\": \"Иван\",\n" +
                "      \"MiddleName\": \"Иванович\",\n" +
                "      \"LastName\": \"Иванов\",\n" +
                "      \"Profile\": \"Клиентский менеджер\",\n" +
                "      \"PositionId\": \"1-Fx22\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getAllClients() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalCount\": 2,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"epkId\": \"13859200020000000000000000000000\",\n" +
                "        \"ucpId\": 1234567890123456789,\n" +
                "        \"lastName\": \"Иванов\",\n" +
                "        \"firstName\": \"Иван\",\n" +
                "        \"middleName\": \"Иванович\",\n" +
                "        \"segment\": \"MASS\",\n" +
                "        \"status\": \"ACTIVE\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"epkId\": \"13859200020000000000000000000001\",\n" +
                "        \"ucpId\": 1234567890123456790,\n" +
                "        \"lastName\": \"Петров\",\n" +
                "        \"firstName\": \"Петр\",\n" +
                "        \"middleName\": \"Петрович\",\n" +
                "        \"segment\": \"VIP\",\n" +
                "        \"status\": \"ACTIVE\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String getVipClientsByFullName() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalCount\": 1,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"epkId\": \"13859200020000000000000000000001\",\n" +
                "        \"ucpId\": 1234567890123456790,\n" +
                "        \"lastName\": \"Ivanov\",\n" +
                "        \"firstName\": \"Ivan\",\n" +
                "        \"middleName\": \"Ivanovich\",\n" +
                "        \"segment\": \"VIP\",\n" +
                "        \"status\": \"ACTIVE\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String getTeams() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"team-001\",\n" +
                "      \"name\": \"VIP Team 1\",\n" +
                "      \"divisionCode\": \"13859200020\",\n" +
                "      \"status\": \"ACTIVE\",\n" +
                "      \"channel\": \"VIP\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"team-002\",\n" +
                "      \"name\": \"Mass Team 1\",\n" +
                "      \"divisionCode\": \"13859200020\",\n" +
                "      \"status\": \"ACTIVE\",\n" +
                "      \"channel\": \"MASS\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String pprbBhepService() {
        return "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"result\": {\n" +
                "    \"pagination\": {\n" +
                "      \"offset\": 0,\n" +
                "      \"limit\": 10,\n" +
                "      \"total\": 1\n" +
                "    },\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"id\": \"client-001\",\n" +
                "        \"name\": \"Test Client\",\n" +
                "        \"status\": \"ACTIVE\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"id\": \"2\"\n" +
                "}";
    }

    public static String pprbClients() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"client-pprb-001\",\n" +
                "      \"teamId\": \"team-001\",\n" +
                "      \"linkTypeId\": \"link-type-001\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String ucpClients() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"ucpId\": 1234567890123456789,\n" +
                "      \"firstName\": \"Иван\",\n" +
                "      \"lastName\": \"Иванов\",\n" +
                "      \"middleName\": \"Иванович\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getChangeSegmentService() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"message\": \"Segment changed successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String getPageByFilter() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalCount\": 1,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"id\": \"filter-result-001\",\n" +
                "        \"dateFrom\": \"2024-04-10\",\n" +
                "        \"dateTo\": \"2024-04-11\",\n" +
                "        \"status\": \"COMPLETED\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String getMailingInfoPageByFilters() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalCount\": 1,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"massMailingId\": \"c6466dd4-f5af-40b3-8dfb-2146e8e3686b\",\n" +
                "        \"clientCount\": 100,\n" +
                "        \"status\": \"SENT\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
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
                "    \"position\": \"Клиентский менеджер\"\n" +
                "  }\n" +
                "}";
    }

    public static String tasksGetByFilter() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalCount\": 1,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"taskId\": \"task-001\",\n" +
                "        \"type\": \"CLIENT_TASK\",\n" +
                "        \"status\": \"ACTIVE\",\n" +
                "        \"createdDate\": \"2024-01-01T10:00:00Z\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String forSend() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"messageId\": \"msg-001\"\n" +
                "  }\n" +
                "}";
    }

    public static String getClientsId() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"client-001\",\n" +
                "      \"name\": \"Client Name 1\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getChangedBy() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"changedBy\": \"system\",\n" +
                "    \"status\": \"INACTIVE\",\n" +
                "    \"changeDate\": \"2024-01-01T10:00:00Z\"\n" +
                "  }\n" +
                "}";
    }
}