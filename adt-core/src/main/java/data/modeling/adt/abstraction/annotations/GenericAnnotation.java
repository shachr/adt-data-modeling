package data.modeling.adt.abstraction.annotations;

public class GenericAnnotation extends Annotation<Object>{
    private String name;

    public GenericAnnotation(String name, Object value) {
        super(value);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
