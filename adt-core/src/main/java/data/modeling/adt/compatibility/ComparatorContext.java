package data.modeling.adt.compatibility;

import data.modeling.adt.abstraction.compatibility.CompatibilityCheck;
import data.modeling.adt.util.JsonPathTraversingContext;

import java.util.List;

public class ComparatorContext {
    private List<Difference> diffs;
    private JsonPathTraversingContext jsonPathTraversingContext;

    private CompatibilityCheck compatibilityCheck;

    public ComparatorContext(CompatibilityCheck compatibilityCheck, List<Difference> diffs, JsonPathTraversingContext jsonPathTraversingContext){
        this.compatibilityCheck = compatibilityCheck;
        this.jsonPathTraversingContext = jsonPathTraversingContext;
        this.diffs = diffs;
    }

    public List<Difference> getDiffs() {
        return diffs;
    }

    public JsonPathTraversingContext getJsonPathTraversingContext() {
        return jsonPathTraversingContext;
    }

    public CompatibilityCheck getCompatibilityCheck() {
        return compatibilityCheck;
    }

    public ComparatorContext overrideJsonPathTraversingContext(JsonPathTraversingContext jsonPathTraversingContext){
        return new ComparatorContext(this.getCompatibilityCheck(), this.diffs, jsonPathTraversingContext);
    }
}
