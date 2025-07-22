package sb1.stub.armsbmock.data;

/**
 * JSON responses for ClientCard subsystem
 * Migrated from armsb_client_card service
 */
public class ClientCardJsonData {
    
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
                "    \"position\": \"Client Manager\",\n" +
                "    \"department\": \"Client Services\"\n" +
                "  }\n" +
                "}";
    }

    public static String getClientCard_new() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"clientId\": \"client-001\",\n" +
                "    \"epkId\": \"13859200020000000000000000000000\",\n" +
                "    \"ucpId\": 1234567890123456789,\n" +
                "    \"personalInfo\": {\n" +
                "      \"lastName\": \"Иванов\",\n" +
                "      \"firstName\": \"Иван\",\n" +
                "      \"middleName\": \"Иванович\",\n" +
                "      \"birthDate\": \"1980-01-01\",\n" +
                "      \"segment\": \"VIP\"\n" +
                "    },\n" +
                "    \"contactInfo\": {\n" +
                "      \"phones\": [\"+7 (495) 123-45-67\"],\n" +
                "      \"emails\": [\"ivanov@example.com\"],\n" +
                "      \"address\": \"Moscow, Russia\"\n" +
                "    },\n" +
                "    \"positions\": [\n" +
                "      {\n" +
                "        \"positionId\": \"pos-001\",\n" +
                "        \"type\": \"ACCOUNT\",\n" +
                "        \"number\": \"40817810123456789012\",\n" +
                "        \"currency\": \"RUB\",\n" +
                "        \"balance\": 1000000.00,\n" +
                "        \"status\": \"ACTIVE\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String getSbpContextInit() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"contextId\": \"context-001\",\n" +
                "    \"sessionId\": \"session-12345\",\n" +
                "    \"userId\": \"user-001\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\",\n" +
                "    \"permissions\": [\"READ_CLIENT_DATA\", \"MODIFY_CLIENT_DATA\"],\n" +
                "    \"status\": \"INITIALIZED\"\n" +
                "  }\n" +
                "}";
    }

    public static String forSend() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"messageId\": \"msg-client-card-001\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\"\n" +
                "  }\n" +
                "}";
    }

    public static String send() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"deliveryId\": \"delivery-001\",\n" +
                "    \"status\": \"SENT\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\"\n" +
                "  }\n" +
                "}";
    }

    public static String getCompanies() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"companyId\": \"company-001\",\n" +
                "      \"name\": \"OOO Test Company\",\n" +
                "      \"inn\": \"1234567890\",\n" +
                "      \"kpp\": \"123456789\",\n" +
                "      \"status\": \"ACTIVE\",\n" +
                "      \"registrationDate\": \"2020-01-01\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getInfoByCompanyId() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"companyId\": \"company-001\",\n" +
                "    \"name\": \"OOO Test Company\",\n" +
                "    \"legalAddress\": \"Moscow, Russia\",\n" +
                "    \"actualAddress\": \"Moscow, Russia\",\n" +
                "    \"director\": \"Иванов Иван Иванович\",\n" +
                "    \"phone\": \"+7 (495) 123-45-67\",\n" +
                "    \"email\": \"info@testcompany.ru\",\n" +
                "    \"status\": \"ACTIVE\"\n" +
                "  }\n" +
                "}";
    }

    public static String getTeamsGet() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"team-card-001\",\n" +
                "      \"name\": \"Client Card Team\",\n" +
                "      \"channel\": \"VIP\",\n" +
                "      \"kmCode\": [],\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getClientCardFromCRM() {
        return "{\n" +
                "  \"HeaderInfo\": {\n" +
                "    \"@RqUID\": \"00000000000000000000000000000000\",\n" +
                "    \"@RqTm\": \"2024-01-01T10:00:00\",\n" +
                "    \"@SPName\": \"urn:sbrfsystems:99-crm\",\n" +
                "    \"@SystemId\": \"sbrfsystems:99-ufs\",\n" +
                "    \"@ErrorCode\": \"SUCCESS\",\n" +
                "    \"@ErrorMessage\": \"\"\n" +
                "  },\n" +
                "  \"ListOfClient\": [\n" +
                "    {\n" +
                "      \"ClientId\": \"client-crm-001\",\n" +
                "      \"LastName\": \"Иванов\",\n" +
                "      \"FirstName\": \"Иван\",\n" +
                "      \"MiddleName\": \"Иванович\",\n" +
                "      \"BirthDate\": \"1980-01-01\",\n" +
                "      \"Segment\": \"VIP\",\n" +
                "      \"Status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getClientCardFromEPK_ORGIGINAL() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"ucpId\": 1234567890123456789,\n" +
                "    \"epkId\": \"13859200020000000000000000000000\",\n" +
                "    \"personalInfo\": {\n" +
                "      \"lastName\": \"Иванов\",\n" +
                "      \"firstName\": \"Иван\",\n" +
                "      \"middleName\": \"Иванович\",\n" +
                "      \"birthDate\": \"1980-01-01\",\n" +
                "      \"segment\": \"VIP\"\n" +
                "    },\n" +
                "    \"riskProfile\": {\n" +
                "      \"level\": \"LOW\",\n" +
                "      \"lastUpdate\": \"2024-01-01T10:00:00Z\"\n" +
                "    },\n" +
                "    \"bankingProducts\": [\n" +
                "      {\n" +
                "        \"productType\": \"ACCOUNT\",\n" +
                "        \"productNumber\": \"40817810123456789012\",\n" +
                "        \"currency\": \"RUB\",\n" +
                "        \"status\": \"ACTIVE\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String getAddDelSatellite() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"satelliteId\": \"satellite-001\",\n" +
                "    \"operation\": \"ADD_SATELLITE\",\n" +
                "    \"message\": \"Satellite operation completed successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String getUpdateCard() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"clientId\": \"client-001\",\n" +
                "    \"updateDateTime\": \"2024-01-01T10:00:00Z\",\n" +
                "    \"message\": \"Client card updated successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String getNotesUpdate() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"noteId\": \"note-001\",\n" +
                "    \"message\": \"LoadRunner obnovlenie notes - updated successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String getNotesGet() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"pagination\": {\n" +
                "      \"limit\": 10,\n" +
                "      \"offset\": 0,\n" +
                "      \"total\": 1\n" +
                "    },\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"noteId\": \"note-001\",\n" +
                "        \"content\": \"Important client note\",\n" +
                "        \"author\": \"manager-001\",\n" +
                "        \"createdDate\": \"2024-01-01T10:00:00Z\",\n" +
                "        \"type\": \"GENERAL\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    public static String getAdditionalInfo() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"epkId\": \"13859200020000000000000000000000\",\n" +
                "    \"additionalData\": {\n" +
                "      \"loyaltyLevel\": \"GOLD\",\n" +
                "      \"preferredChannel\": \"ONLINE\",\n" +
                "      \"lastContactDate\": \"2024-01-01T10:00:00Z\",\n" +
                "      \"notes\": \"VIP client with special requirements\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    public static String getUpdateAdditionalInfo() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"criteriaId\": \"8051b304-73a4-4907-abad-82b4eafece39\",\n" +
                "    \"epkId\": \"13859200020000000000000000000000\",\n" +
                "    \"changedBy\": \"manager-001\",\n" +
                "    \"message\": \"Additional info updated successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String getMarkingTeam() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"a10945a4-cfde-4531-acdb-d10f04a97d3b\",\n" +
                "      \"name\": \"Marking Team\",\n" +
                "      \"kmCode\": [],\n" +
                "      \"staffUnitId\": [],\n" +
                "      \"status\": [],\n" +
                "      \"ossPositionCode\": []\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String notifOfferAccept() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"sysSenderUrn\": \"urn:sbrfsystems:99-ufs-sr\",\n" +
                "    \"subSystemCode\": \"ARMSB_NOTIFICATION\",\n" +
                "    \"message\": \"Notification accepted\"\n" +
                "  }\n" +
                "}";
    }

    public static String notifOfferAccept2() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"sysSenderUrn\": \"urn:sbrfsystems:99-ufs-sr\",\n" +
                "    \"subSystemCode\": \"ARMSB_NOTIFICATION\",\n" +
                "    \"packageId\": \"package-001\",\n" +
                "    \"message\": \"Notification with package accepted\"\n" +
                "  }\n" +
                "}";
    }

    public static String sbpContextInit() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"sbpServiceFormat\": \"REMOTE\",\n" +
                "    \"sbpFormatType\": \"APPEAL\",\n" +
                "    \"dt\": \"2024-01-01T10:00:00Z\",\n" +
                "    \"sessionId\": \"session-12345\",\n" +
                "    \"status\": \"INITIALIZED\"\n" +
                "  }\n" +
                "}";
    }

    public static String qliksense() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"RqUID\": \"request-12345\",\n" +
                "    \"SPName\": \"qliksense-service\",\n" +
                "    \"hash_salt_epk_id\": \"hashed_epk_12345\",\n" +
                "    \"encryptedData\": \"encrypted_data_for_qliksense\",\n" +
                "    \"timestamp\": \"2024-01-01T10:00:00Z\"\n" +
                "  }\n" +
                "}";
    }

    public static String getInfoClient() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"channel\": \"VIP\",\n" +
                "      \"kmCode\": [],\n" +
                "      \"id\": [],\n" +
                "      \"type\": \"DEF\",\n" +
                "      \"employeeNumber\": \"12345\",\n" +
                "      \"clientInfo\": {\n" +
                "        \"firstName\": \"Иван\",\n" +
                "        \"lastName\": \"Иванов\",\n" +
                "        \"segment\": \"VIP\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getTypedTeams2() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"channel\": \"VIP\",\n" +
                "      \"kmCode\": [],\n" +
                "      \"id\": [],\n" +
                "      \"type\": \"DEF\",\n" +
                "      \"staffUnitId\": [],\n" +
                "      \"status\": [\"ACTIVE\"],\n" +
                "      \"ossPositionCode\": [\"20010601\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getTypedTeams3() {
        return "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"result\": {\n" +
                "    \"pagination\": {\n" +
                "      \"offset\": 0.0,\n" +
                "      \"limit\": 1.0\n" +
                "    },\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"method\": \"searchAssignment\",\n" +
                "        \"parameters\": {\n" +
                "          \"modifier\": \"IN\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"id\": \"2\"\n" +
                "}";
    }

    public static String teams_addLink() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"teamId\": \"e47346a1-7048-4576-9523-df395f214fc1\",\n" +
                "    \"linkTypeId\": \"d5ab0589-70f3-4200-95ab-1d16c14a01c1\",\n" +
                "    \"changedBy\": \"manager-001\",\n" +
                "    \"message\": \"Team link added successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String teams_id() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": \"team-id-001\",\n" +
                "      \"name\": \"Team by ID\",\n" +
                "      \"status\": \"ACTIVE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String teams_unlinkTeam() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"epkId\": \"13859200020000000000000000000000\",\n" +
                "    \"changedBy\": \"manager-001\",\n" +
                "    \"linkTypeId\": [],\n" +
                "    \"links\": [\"cf623bd8-bd7f-4bb5-9055-8daea22ca7cc\"],\n" +
                "    \"message\": \"Team unlinked successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String createStructure() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"structureId\": \"structure-001\",\n" +
                "    \"message\": \"ECM structure created successfully\"\n" +
                "  }\n" +
                "}";
    }

    public static String setRegionConfig() {
        return "{\n" +
                "  \"error\": null,\n" +
                "  \"result\": {\n" +
                "    \"success\": true,\n" +
                "    \"regionId\": \"region-001\",\n" +
                "    \"configId\": \"config-001\",\n" +
                "    \"message\": \"NetScan region config set successfully\"\n" +
                "  }\n" +
                "}";
    }
}