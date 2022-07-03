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

    static public String getCharacterSkill() {
        return "{\n" +
                "    \"character\": {\n" +
                "        \"id\": 2\n" +
                "    },\n" +
                "    \"skill\": {\n" +
                "        \"id\": 1\n" +
                "    },\n" +
                "    \"level\":5\n" +
                "}";
    }

    static public String getBadCharacterSkill() {
        return "{\n" +
                "    \"character\": {\n" +
                "        \"id\": 2\n" +
                "    '\n" +
                "    \"skill\"\n" +
                "        \"id\": 1\n" +
                "    },\n" +
                "    \"level\":5\n" +
                "}";
    }

    static public String getMissingIdCharacterSkill() {
        return "{\n" +
                "    \"level\":5\n" +
                "}";
    }

    static public String getSkillCharacter() {
        return "{\n" +
                "\t\"character\": {\n" +
                "\t\t\"id\": 2\n" +
                "\t},\n" +
                "\t\"skill\": {\n" +
                "\t\t\"id\": 1\n" +
                "\t},\n" +
                "\t\"level\": 5\n" +
                "}";
    }

    static public String getNoMatchSkillCharacter() {
        return "{\n" +
                "\t\"character\": {\n" +
                "\t\t\"id\": 8\n" +
                "\t},\n" +
                "\t\"skill\": {\n" +
                "\t\t\"id\": 1\n" +
                "\t},\n" +
                "\t\"level\": 5\n" +
                "}";
    }

    static public String getSkillCharacterMissingId() {
        return "{\n" +
                "\t\"character\": {\n" +
                "\t\t\"id\": 2\n" +
                "\t},\n" +
                "\t\"level\": 5\n" +
                "}";
    }

    static public String getBadSkillCharacter() {
        return "{\n" +
                "\t\"character\": {\n" +
                "\t\t\"id\": 2\n" +
                "\t},\n" +
                "\t\"skill\": {\n" +
                "\t\t\"id\": 1\n" +
                "\t\"level\": 5\n" +
                "}";
    }
}
