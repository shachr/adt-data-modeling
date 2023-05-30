package data.modeling.adt.mappers.graphqlToAdt;

import data.modeling.adt.exceptions.AdtException;

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
                                "  roleList: [Role]\n" +
                                "  stringList: [String]\n" +
                                "}" +
                                "union SearchResult = Role | Employee\n" +
                                "input ReviewInput {\n" +
                                "  stars: Int!\n" +
                                "  commentary: String\n" +
                                "}" +
                                "interface Character {\n" +
                                "  id: ID!\n" +
                                "  name: String!\n" +
                                "  friends: [Character]\n" +
                                "  appearsIn: [Episode]!\n" +
                                "}" +
                                "type Human implements Character {\n" +
                                "  id: ID!\n" +
                                "  name: String!\n" +
                                "  friends: [Character]\n" +
                                "  appearsIn: [Episode]!\n" +
                                "  starships: [Starship]\n" +
                                "  totalCredits: Int\n" +
                                "}" +
                                "type Droid implements Character {\n" +
                                "  id: ID!\n" +
                                "  name: String!\n" +
                                "  friends: [Character]\n" +
                                "  appearsIn: [Episode]!\n" +
                                "  primaryFunction: String\n" +
                                "}" +
                                "extend type Droid {\n" +
                                "  user: Human!\n" +
                                "}\n" +
                                "scalar LocalDate\n"
        ).stream().forEach(x -> {

            System.out.println(x.getName());
        });
    }
}
