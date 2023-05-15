package data.modeling.adt.compatibility;

public record Difference(DifferenceTypes differenceType, String jsonPointer, Object expected, Object actual) {

    @Override
    public String toString() {
        return "Difference{" +
                "message='" + differenceType.toString() + '\'' +
                ", jsonPointer='" + jsonPointer + '\'' +
                ", expected=" + expected +
                ", actual=" + actual +
                '}';
    }
}