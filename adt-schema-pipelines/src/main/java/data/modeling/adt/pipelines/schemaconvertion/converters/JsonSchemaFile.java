package data.modeling.adt.pipelines.schemaconvertion.converters;

import data.modeling.adt.abstraction.artifacts.Artifact;

public record JsonSchemaFile(String name, String content) implements Artifact<String> {

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getValue() {
            return content;
        }
    }