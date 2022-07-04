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

    static public String getStatus() {
        return "{\n" +
                "\t\"id\": 69,\n" +
                "\t\"sect\": {\n" +
                "\t\t\"id\": 2\n" +
                "\t},\n" +
                "\t\"statusType\": {\n" +
                "\t\t\"id\": 3\n" +
                "\t},\n" +
                "\t\"descriptionEN\": \"TEST a\",\n" +
                "\t\"descriptionFR\": \"none\",\n" +
                "\t\"noteEN\": \"none\",\n" +
                "\t\"noteFR\": \"none\",\n" +
                "\t\"updatable\": true\n" +
                "}";
    }

    static public String getNotExistingStatus() {
        return "{\n" +
                "\t\"id\": 70,\n" +
                "\t\"sect\": {\n" +
                "\t\t\"id\": 2\n" +
                "\t},\n" +
                "\t\"statusType\": {\n" +
                "\t\t\"id\": 3\n" +
                "\t},\n" +
                "\t\"descriptionEN\": \"TEST a\",\n" +
                "\t\"descriptionFR\": \"none\",\n" +
                "\t\"noteEN\": \"none\",\n" +
                "\t\"noteFR\": \"none\",\n" +
                "\t\"updatable\": true\n" +
                "}";
    }

    static public String getBadStatus() {
        return "{\n" +
                "\t\"id\": 69,\n" +
                "\t\"sect\": {\n" +
                "\t\t\"id\": 2\n" +
                "\t},\n" +
                "\t\"statusType\": \n" +
                "\t\"descriptionEN\": \"TEST a\",\n" +
                "\t\"descriptionFR\": \"none\",\n" +
                "\t\"noteEN\": \"none\",\n" +
                "\t\"noteFR\": \"none\",\n" +
                "\t\"updatable\": true\n" +
                "}";
    }

    static public String getStatusType() {
        return "{\n" +
                "            \"id\": 1,\n" +
                "            \"descriptionEN\": \"Abiding\",\n" +
                "            \"descriptionFR\": \"none\",\n" +
                "            \"noteEN\": \"Abiding status traits are usually earned by holding a position within a sect. So long as a vampire continues to hold that position, abiding status refreshes at the beginning of each game session. If a character loses a sect position during a game, any unspent abiding status she was carrying from that position is immediately removed (she cannot expend it as she is losing the position). Even if the character has more than one sect position, she does not gain the second position’s abiding status during this game. Also, when a character gains a new sect position, she does not gain that position’s abiding status until the beginning of the next game.\",\n" +
                "            \"noteFR\": \"none\"\n" +
                "        }";
    }

    static public String getNotExistingStatusType() {
        return "{\n" +
                "            \"id\": 8,\n" +
                "            \"descriptionEN\": \"Abiding\",\n" +
                "            \"descriptionFR\": \"none\",\n" +
                "            \"noteEN\": \"Abiding status traits are usually earned by holding a position within a sect. So long as a vampire continues to hold that position, abiding status refreshes at the beginning of each game session. If a character loses a sect position during a game, any unspent abiding status she was carrying from that position is immediately removed (she cannot expend it as she is losing the position). Even if the character has more than one sect position, she does not gain the second position’s abiding status during this game. Also, when a character gains a new sect position, she does not gain that position’s abiding status until the beginning of the next game.\",\n" +
                "            \"noteFR\": \"none\"\n" +
                "        }";
    }

    static public String getBadStatusType() {
        return "{\n" +
                "            \"id\": 8,\n" +
                "            \"descriptionEN\": \"Abiding\",\n" +
                "            \"descripti\n" +
                "            \"noteEN\": \"Abiding status traits are usually earned by holding a position within a sect. So long as a vampire continues to hold that position, abiding status refreshes at the beginning of each game session. If a character loses a sect position during a game, any unspent abiding status she was carrying from that position is immediately removed (she cannot expend it as she is losing the position). Even if the character has more than one sect position, she does not gain the second position’s abiding status during this game. Also, when a character gains a new sect position, she does not gain that position’s abiding status until the beginning of the next game.\",\n" +
                "            \"noteFR\": \"none\"\n" +
                "        }";
    }

    static public String getJurisdiction() {
        return "{\n" +
                "            \"id\": 1,\n" +
                "            \"description\": \"Metz\",\n" +
                "            \"updatable\": true\n" +
                "        }";
    }

    static public String getBadJurisdiction() {
        return "{\n" +
                "            \"id\": 1,\n" +
                "            \"description: \"Metz\"\n" +
                "            \"updatable\": true\n" +
                "        }";
    }

    static public String getNotExistingJurisdiction() {
        return "{\n" +
                "            \"id\": 8,\n" +
                "            \"description\": \"Metz\",\n" +
                "            \"updatable\": true\n" +
                "        }";
    }

    static public String getTitle() {
        return "{\n" +
                "\t\"id\": 1,\n" +
                "\t\"sect\": {\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"description\": \"Camarilla\",\n" +
                "\t\t\"updatable\": true\n" +
                "\t},\n" +
                "\t\"status\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 1\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 18\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 63\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"descriptionEN\": \"Imperator\",\n" +
                "\t\"descriptionFR\": \"none\",\n" +
                "\t\"noteEN\": \"none\",\n" +
                "\t\"noteFR\": \"none\",\n" +
                "\t\"updatable\": true\n" +
                "}";
    }

    static public String getNotExistingTitle() {
        return "{\n" +
                "\t\"id\": 14,\n" +
                "\t\"sect\": {\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"description\": \"Camarilla\",\n" +
                "\t\t\"updatable\": true\n" +
                "\t},\n" +
                "\t\"status\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 1\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 18\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 63\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"descriptionEN\": \"Imperator\",\n" +
                "\t\"descriptionFR\": \"none\",\n" +
                "\t\"noteEN\": \"none\",\n" +
                "\t\"noteFR\": \"none\",\n" +
                "\t\"updatable\": true\n" +
                "}";
    }

    static public String getBadTitle() {
        return "{\n" +
                "\t\"id\": 1,\n" +
                "\t\"sect\": {\n" +
                "\t\t\"id\":\n" +
                "\t\t\"description\": \"Camarilla\",\n" +
                "\t\t\"updatable\": true\n" +
                "\t},\n" +
                "\t\"status [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 1\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 18\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 63\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"descriptionEN\": \"Imperator\",\n" +
                "\t\"descriptionFR\": \"none\",\n" +
                "\t\"noteEN\": \"none\",\n" +
                "\t\"noteFR\": \"none\",\n" +
                "\t\"updatable\": true\n" +
                "}";
    }
}