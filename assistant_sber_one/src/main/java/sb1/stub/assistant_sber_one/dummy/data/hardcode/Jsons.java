package sb1.stub.assistant_sber_one.dummy.data.hardcode;

public class Jsons {
    public static String getSbpContextInit() {
        return  "{\n" +
                "    \"success\": true,\n" +
                "    \"body\": {\n" +
                "        \"sbpcontext_token\": \"12345678\"\n" +
                "    }\n" +
                "}";
    }

    public static String getEmployeeInfo() {
        return  "{\n" +
                "   \"success\" : true,\n" +
                "   \"body\" : {\n" +
                "   \"employeeInfoDto\": {\n" +
                "       \"lastName\": \"Счастьев\",\n" +
                "       \"firstName\": \"Андрей\",\n" +
                "       \"middleName\": \"Андреевич\",\n" +
                "       \"personalNumber\": \"00447106\",\n" +
                "       \"sudirGroups\": [\n" +
                "           \"EFS_AKM_VIP\"\n"  +
                "       ],\n" +
                "       \"channel\": \"WEB_SBOLPRO\",\n" +
                "       \"login\": \"schastev_aa\",\n" +
                "       \"tbank\": \"038\",\n" +
                "       \"branch\": \"9038\",\n" +
                "       \"office\": \"01665\",\n" +
                "       \"subbranchCode\": \"38903801665\",\n" +
                "       \"branchName\": \"Доп.офис №9038/01665\",\n" +
                "       \"isWorkShift\": false,\n" +
                "       \"snils\": null,\n" +
                "       \"identifierSessionEmployee\": null,\n" +
                "       \"position\": \"Операционный менеджер\",\n" +
                "       \"sudirsegment\": \"ALPHA\",\n" +
                "       \"operatorId\": null,\n" +
                "       \"operatorODDONumber\": null\n" +
                "   },\n" +
                "   \"vspInfoDto\": {\n" +
                "       \"isOneHand\": false,\n" +
                "       \"isVip\": true,\n" +
                "       \"vipType\": \"МВС,МС\",\n" +
                "       \"timeZone\": 3\n" +
                "   },\n" +
                "   \"uovInfoDto\": {\n" +
                "       \"isOdOpen\": false,\n" +
                "       \"integrationStatus\": null,\n" +
                "       \"operDay\": \"2024-09-05\", \n" +
                "       \"timeZone\": 3,\n" +
                "       \"tb\": 38,\n" +
                "       \"osb\": 9038,\n" +
                "       \"vsp\": 1665,\n" +
                "       \"divisionId\": \"38903801665\"\n" +
                "   }\n" +
                "   }\n" +
                "}";
    }
}

