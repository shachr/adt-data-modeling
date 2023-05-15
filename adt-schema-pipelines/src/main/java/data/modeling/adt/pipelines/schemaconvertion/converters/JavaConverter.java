package data.modeling.adt.pipelines.schemaconvertion.converters;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.javabeansFromAdt.JavaBeansFromAdt;
import data.modeling.adt.mappers.javabeansFromAdt.util.JavaFile;
import data.modeling.adt.messages.SchemaConvertedMessage;
import data.modeling.adt.messages.SchemaConvertionMessage;
import data.modeling.processing.abstraction.Task;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaConverter implements Task<SchemaConvertionMessage, SchemaConvertedMessage> {
    @Override
    public boolean shouldExecute(SchemaConvertionMessage message) {
        return message.getContentType().equals("binary/java");
    }

    @Override
    public SchemaConvertedMessage execute(SchemaConvertionMessage message) throws Exception {
        AdtToPojo adtToSimplerObjects = new AdtToPojo(message.getSchemaContext());
        SchemaContext schemaContext = adtToSimplerObjects.apply();

        JavaBeansFromAdt javaBeansFromAdt = new JavaBeansFromAdt(schemaContext);
        Set<JavaFile> files = javaBeansFromAdt.stream().collect(Collectors.toCollection(LinkedHashSet::new));
        return new SchemaConvertedMessage(message.getContentType(), files);
    }
}
