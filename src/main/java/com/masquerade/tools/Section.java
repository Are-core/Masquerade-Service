package com.masquerade.tools;

public class Section {

    public static final String CrossOriginUrl = "http://localhost:4200";

    private static final String CharacterSheetParameterPrefix = "/characterSheet";


    /* CHARACTER SHEET */
    public static final String CharacterPrefix = "";

    /* CHARACTER PARAMETERS */

        /* GLOBAL */
        public static final String ArchetypePrefix = CharacterSheetParameterPrefix + "/archetype";
        public static final String ClanPrefix = CharacterSheetParameterPrefix + "/clan";
        public static final String SectPrefix = CharacterSheetParameterPrefix + "/sect";
        public static final String TitlePrefix = CharacterSheetParameterPrefix + "/title";

        /* PARAMETER */
        public static final String JurisdictionPrefix = CharacterSheetParameterPrefix + "/jurisdiction";
        public static final String StatusPrefix = CharacterSheetParameterPrefix + "/status";
        public static final String StatusTypePrefix = CharacterSheetParameterPrefix + "/status/type";
        public static final String CharacterHasStatusPrefix = CharacterSheetParameterPrefix + "/status";

        /* SKILL */
        public static final String SkillPrefix = CharacterSheetParameterPrefix + "/skill";
        public static final String SkillSpecializationPrefix = CharacterSheetParameterPrefix + "/skill/specialization";
        public static final String CharacterHasSkillPrefix = CharacterSheetParameterPrefix + "/skill";
}
