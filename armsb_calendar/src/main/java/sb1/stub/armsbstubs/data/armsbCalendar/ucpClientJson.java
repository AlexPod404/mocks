package sb1.stub.armsbstubs.data.armsbCalendar;

public class ucpClientJson {
    public static String ClientGet() {
        return "{\n" +
                " \"responses\": [\n" +
                "  {\n" +
                "   \"ucpId\": ${id},\n" +
                "   \"individual\": {\n" +
                "    \"id\": ${id},\n" +
                "    \"version\": 15,\n" +
                "    \"individualName\": {\n" +
                "     \"id\": ${id},\n" +
                "     \"name\": \"Александра\",\n" +
                "     \"surname\": \"Карпенко\",\n" +
                "     \"patronymic\": \"Петровна\"\n" +
                "    },\n" +
                "    \"birthDate\": {\n" +
                "     \"birthDatestamp\": {\n" +
                "      \"day\": null,\n" +
                "      \"month\": 8,\n" +
                "      \"year\": 1981\n" +
                "     }\n" +
                "    },\n" +
                "    \"gender\": {\n" +
                "     \"genderTypeCode\": \"1\"\n" +
                "    },\n" +
                "    \"segments\": [\n" +
                "     \"3\"\n" +
                "    ]\n" +
                "   },\n" +
                "   \"error\": []\n" +
                "  }\n" +
                " ],\n" +
                " \"error\": []\n" +
                "}";
    }
}
