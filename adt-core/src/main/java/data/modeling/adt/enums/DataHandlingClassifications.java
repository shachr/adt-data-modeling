package data.modeling.adt.enums;

public enum DataHandlingClassifications {
        Public("public"),
        Internal("Internal"),
        Restricted("Restricted"),
        Sensitive("Sensitive"),
        HighlySensitive("HighlySensitive"),
        Secret("Secret");

        private final String value;

        DataHandlingClassifications(String value) {
                this.value = value;
        }

        public String getValue() {
                return value;
        }

        public static DataHandlingClassifications fromString(String str) {
          if (str == null) {
            return null;
          }
          for (DataHandlingClassifications e : DataHandlingClassifications.values()) {
            if (e.name().equalsIgnoreCase(str.trim())) {
              return e;
            }
          }
          return null;
        }
}