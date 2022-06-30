package com.masquerade.mocks.json;

public class JsonMock {
    static public String getSkillSpecializationJson() {
        return "{\n" +
                "        \"id\": 5,\n" +
                "        \"descriptionEN\": \"Business\",\n" +
                "        \"descriptionFR\": \"Commerce\"\n" +
                "    }";
    }

    static public String getBadSkillSpecializationJson() {
        return "{\n" +
                "        \"id\": 5,\n" +
                "        \"descriptionEN\": \"Business\"\n" +
                "        \"descriptionFR\"= Commerce\"\n" +
                "    }";
    }

    static public String getExistingSkillSpecializationJson() {
        return "{\n" +
                "        \"id\": 1,\n" +
                "        \"descriptionEN\": \"Business\",\n" +
                "        \"descriptionFR\": \"Commerce\"\n" +
                "    }";
    }
}
