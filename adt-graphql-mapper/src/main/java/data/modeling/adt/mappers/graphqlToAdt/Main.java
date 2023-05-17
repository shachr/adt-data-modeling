package data.modeling.adt.mappers.graphqlToAdt;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.NamedType;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws AdtException {
        new GraphQLToAdt(
                        "" +
                                "enum Role {\n" +
                                "  ADMIN\n" +
                                "  USER\n" +
                                "  MANAGER\n" +
                                "}\n" +
                                "\n" +
                                "type Employee {\n" +
                                "  id: ID!\n" +
                                "  firstName: String!\n" +
                                "  lastName: String!\n" +
                                "  age: Int\n" +
                                "  isActive: Boolean!\n" +
                                "  salary : Float\n" +
                                "  role: Role\n" +
                                "}"
        ).stream().forEach(x -> {
            System.out.println(x);
        });
    }
}
