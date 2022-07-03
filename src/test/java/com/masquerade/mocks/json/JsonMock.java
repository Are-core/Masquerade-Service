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

    static public String getSkillJson() {
        return "{\n" +
                "        \"id\": 8,\n" +
                "        \"costId\": 0,\n" +
                "        \"descriptionEN\": \"Note\",\n" +
                "        \"descriptionFR\": \"none\",\n" +
                "        \"specializations\": [],\n" +
                "        \"hasSpecialization\": false,\n" +
                "        \"noteEN\": \"none\",\n" +
                "        \"noteFR\": \"none\",\n" +
                "        \"updatable\": true,\n" +
                "        \"duplicable\": false\n" +
                "    }";
    }

    static public String getBadSkillJson() {
        return "{\n" +
                "        \"id\": 8,\n" +
                "        \"costId\":\n" +
                "        \"descriptionEN\": \"Note\",\n" +
                "        \"descriptionFR\": \"none\",\n" +
                "        \"specializations\": [],\n" +
                "        \"hasSpecialization\": false,\n" +
                "        \"noteEN\": \"none\",\n" +
                "        \"noteFR\": \"none\",\n" +
                "        \"updatable\": true,\n" +
                "        \"duplicable\": false\n" +
                "    }";
    }

    static public String getMissingSkillJson() {
        return "{\n" +
                "        \"id\": 7,\n" +
                "        \"costId\": 0,\n" +
                "        \"descriptionEN\": \"Note\",\n" +
                "        \"descriptionFR\": \"none\",\n" +
                "        \"specializations\": [],\n" +
                "        \"hasSpecialization\": false,\n" +
                "        \"noteEN\": \"none\",\n" +
                "        \"noteFR\": \"none\",\n" +
                "        \"updatable\": true,\n" +
                "        \"duplicable\": false\n" +
                "    }";
    }

}
