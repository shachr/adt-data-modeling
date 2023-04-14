package data.modeling.adt.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TraversingContext {
    private Stack<Scope> stack = new Stack<>();

    public <R> R createScope(String name, Function<Scope, R> function) {
        // Create a new Scope for the current TraversingContext
        Scope scope = new Scope(name, this);
        this.stack.push(scope);
        try {
            return function.apply(scope);
        } finally {
            this.stack.pop();
        }
    }

    public class Scope {
        private String name;
        private final Map<String, Object> items = new HashMap<>();
        private TraversingContext context;

        public Scope(String name, TraversingContext context){
            this.name = name;
            this.context = context;
        }

        public String getName() {
            return name;
        }

        public String getFullQualifiedName(String separator) {
            return String.join(separator, stack.stream().map(Scope::getName).collect(Collectors.toList()));
        }

        public void addItem(String name, Object item){
            this.items.put(name, item);
        }

        public <T> T getItem(String name){
            return (T)items.get(name);
        }
    }
}
