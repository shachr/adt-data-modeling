package data.modeling.adt.enums;

public enum TypeDeclarations{
        Class("class"), Interface("interface"), Enum("enum");

        private final String value;

        TypeDeclarations(String value){
            this.value = value;
        }
    public String getValue() {
        return value;
    }

    public static TypeDeclarations fromString(String str) {
        if (str == null) {
            return null;
        }
        for (TypeDeclarations e : TypeDeclarations.values()) {
            if (e.name().equalsIgnoreCase(str.trim())) {
                return e;
            }
        }
        return null;
    }
}