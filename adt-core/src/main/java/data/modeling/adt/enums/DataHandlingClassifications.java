package data.modeling.adt.enums;

public enum DataHandlingClassifications{
        Public("public"),
        Internal("Internal"),
        Restricted("Restricted"),
        Sensitive("Sensitive"),
        HighlySensitive("HighlySensitive"),
        Secret("Secret");

        DataHandlingClassifications(String value){

        }
    }