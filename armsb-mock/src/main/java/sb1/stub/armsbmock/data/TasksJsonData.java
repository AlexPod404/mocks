package sb1.stub.armsbmock.data;

/**
 * JSON responses for Tasks subsystem
 * Migrated from armsb_tasks service
 */
public class TasksJsonData {
    
    public static String errorRequest() {
        return "{\n" +
                "    \"status\": \"Error\",\n" +
                "    \"comment\": \"Need signature in request. Требуется сигнатура в запросе\"\n" +
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
                "    \"position\": \"Менеджер по задачам\"\n" +
                "  }\n" +
                "}";
    }

    public static String getByTeamId() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"teamId\": \"team-001\",\n" +
                "      \"employeeNumber\": \"12345\",\n" +
                "      \"firstName\": \"Иван\",\n" +
                "      \"lastName\": \"Иванов\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getByClientId() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"clientId\": \"client-001\",\n" +
                "      \"taskId\": \"task-001\",\n" +
                "      \"status\": \"ACTIVE\",\n" +
                "      \"createdDate\": \"2024-01-01T10:00:00Z\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getTemplates() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"templateId\": \"template-001\",\n" +
                "      \"name\": \"Standard Task Template\",\n" +
                "      \"type\": \"TASK\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getFiltersTemplates() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"filterId\": \"filter-001\",\n" +
                "      \"name\": \"Task Status Filter\",\n" +
                "      \"criteria\": [\"ACTIVE\", \"COMPLETED\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getUpdateTemplates() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"templateId\": \"template-001\",\n" +
                "    \"message\": \"Template updated successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String tasksByFilter(String requestBody) {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalCount\": 2,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"taskId\": \"task-001\",\n" +
                "        \"type\": \"CLIENT_TASK\",\n" +
                "        \"status\": \"ACTIVE\",\n" +
                "        \"priority\": \"HIGH\",\n" +
                "        \"createdDate\": \"2024-01-01T10:00:00Z\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"taskId\": \"task-002\",\n" +
                "        \"type\": \"SYSTEM_TASK\",\n" +
                "        \"status\": \"PENDING\",\n" +
                "        \"priority\": \"MEDIUM\",\n" +
                "        \"createdDate\": \"2024-01-01T11:00:00Z\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String getTask(String requestBody) {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"taskId\": \"task-001\",\n" +
                "    \"title\": \"Client Task\",\n" +
                "    \"description\": \"Task description\",\n" +
                "    \"type\": \"CLIENT_TASK\",\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"priority\": \"HIGH\",\n" +
                "    \"assignedTo\": \"12345\",\n" +
                "    \"createdDate\": \"2024-01-01T10:00:00Z\",\n" +
                "    \"dueDate\": \"2024-01-15T10:00:00Z\"\n" +
                "  }\n" +
                "}";
    }

    public static String getOffer() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"offerId\": \"offer-001\",\n" +
                "    \"title\": \"VIP Banking Offer\",\n" +
                "    \"description\": \"Premium banking services\",\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"validFrom\": \"2024-01-01T00:00:00Z\",\n" +
                "    \"validTo\": \"2024-12-31T23:59:59Z\"\n" +
                "  }\n" +
                "}";
    }

    public static String getFilters() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"filterId\": \"status-filter\",\n" +
                "      \"name\": \"Task Status\",\n" +
                "      \"values\": [\"ACTIVE\", \"COMPLETED\", \"PENDING\", \"CANCELLED\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"filterId\": \"priority-filter\",\n" +
                "      \"name\": \"Priority\",\n" +
                "      \"values\": [\"LOW\", \"MEDIUM\", \"HIGH\", \"CRITICAL\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getMarkingById() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"markingId\": \"marking-001\",\n" +
                "    \"clientId\": \"client-001\",\n" +
                "    \"type\": \"VIP_MARKING\",\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"createdDate\": \"2024-01-01T10:00:00Z\"\n" +
                "  }\n" +
                "}";
    }

    public static String premierDivisions() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"divisionId\": \"13859200020\",\n" +
                "      \"name\": \"Premier Division Moscow\",\n" +
                "      \"status\": \"ACTIVE\",\n" +
                "      \"type\": \"PREMIER\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String resultDetailGet() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"detailId\": \"detail-001\",\n" +
                "    \"taskId\": \"task-001\",\n" +
                "    \"result\": \"SUCCESS\",\n" +
                "    \"details\": \"Task completed successfully\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\"\n" +
                "  }\n" +
                "}";
    }

    public static String resultDetailUpdate() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"detailId\": \"detail-001\",\n" +
                "    \"message\": \"Result detail updated successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String getGeneralCounters() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalTasks\": 150,\n" +
                "    \"activeTasks\": 85,\n" +
                "    \"completedTasks\": 45,\n" +
                "    \"pendingTasks\": 20\n" +
                "  }\n" +
                "}";
    }

    public static String getCountersByClients() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"clientId\": \"client-001\",\n" +
                "      \"totalTasks\": 10,\n" +
                "      \"activeTasks\": 5,\n" +
                "      \"completedTasks\": 3,\n" +
                "      \"pendingTasks\": 2\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getPositions() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"positionId\": \"pos-001\",\n" +
                "      \"name\": \"Senior Manager\",\n" +
                "      \"level\": \"SENIOR\",\n" +
                "      \"department\": \"VIP\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String search() {
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
                "        \"id\": \"search-result-001\",\n" +
                "        \"name\": \"Search Result\",\n" +
                "        \"type\": \"TASK\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"id\": \"2\"\n" +
                "}";
    }

    public static String tasksGetByFilter() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"totalCount\": 2,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"ucpId\": 1428105050034864952,\n" +
                "        \"taskId\": \"task-filter-001\",\n" +
                "        \"status\": \"ACTIVE\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"ucpId\": 1426354352652271068,\n" +
                "        \"taskId\": \"task-filter-002\",\n" +
                "        \"status\": \"PENDING\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String taskUpdate() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"taskId\": \"task-001\",\n" +
                "    \"result\": \"APPROVAL\",\n" +
                "    \"message\": \"Task updated successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String getMarkingCreate() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"markingId\": \"marking-001\",\n" +
                "    \"processType\": \"VIP2MASS\",\n" +
                "    \"message\": \"Marking created successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String createMarking() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"markingId\": \"marking-001\",\n" +
                "    \"epkId\": \"13859200020000000000000000000000\"\n" +
                "  }\n" +
                "}";
    }

    public static String getFreeEmployee() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"employeeNumber\": \"12345\",\n" +
                "      \"firstName\": \"Иван\",\n" +
                "      \"lastName\": \"Иванов\",\n" +
                "      \"divisionCode\": \"13859200020\",\n" +
                "      \"status\": \"FREE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getTeams() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"team-001\",\n" +
                "      \"name\": \"VIP Tasks Team\",\n" +
                "      \"channel\": \"VIP\",\n" +
                "      \"type\": \"DEF\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getDivisionTeams() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"team-div-001\",\n" +
                "      \"name\": \"Division VIP Team\",\n" +
                "      \"divisionCodeOwner\": \"38903800566\",\n" +
                "      \"channel\": \"VIP\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getMarkingCheck() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"canCreateMarking\": true,\n" +
                "    \"epkId\": \"13859200020000000000000000000000\",\n" +
                "    \"reasons\": []\n" +
                "  }\n" +
                "}";
    }

    public static String getFreeTeams() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"teamId\": \"team-free-001\",\n" +
                "      \"name\": \"Free Team 1\",\n" +
                "      \"availableSlots\": 5,\n" +
                "      \"status\": \"AVAILABLE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getMarkingByClient() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"markingId\": \"marking-client-001\",\n" +
                "      \"epkId\": \"13859200020000000000000000000000\",\n" +
                "      \"clientId\": \"client-001\",\n" +
                "      \"type\": \"VIP_MARKING\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}