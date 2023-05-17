package data.modeling.adt.mappers.javabeansFromAdt.artifacts;

import data.modeling.adt.abstraction.artifacts.Artifact;

public record JavaFile(String fileName, String content) implements Artifact<String> {
    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getValue() {
        return content;
    }
}