package data.modeling.adt.messages;

import data.modeling.adt.abstraction.Message;
import data.modeling.adt.abstraction.artifacts.Artifact;

import java.util.Map;
import java.util.Set;

public class SchemaConvertedMessage implements Message {
    private Set<? extends Artifact<?>> schemaArtifact;
    private String contentType;

    public SchemaConvertedMessage(String contentType, Set<? extends Artifact<?>> schemaArtifact){
        this.contentType = contentType;
        this.schemaArtifact = schemaArtifact;
    }

    public Set<? extends Artifact<?>> getSchemaArtifact() {
        return schemaArtifact;
    }
    public <T extends Artifact> Set<T> getSchemaArtifactOf() {
        return (Set<T>)schemaArtifact;
    }

    public String getContentType() {
        return contentType;
    }
}
