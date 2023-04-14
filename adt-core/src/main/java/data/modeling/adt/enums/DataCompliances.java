package data.modeling.adt.enums;

import java.util.HashSet;
import java.util.Set;

public enum DataCompliances {
        GDPR(1),
        CCPA(2),
        PCI(4);

        private int value;

        DataCompliances(int value){

                this.value = value;
        }

        public int getValue() {
          return value;
        }

        public static Set<DataCompliances> fromBitwise(int bitwiseValue) {
          Set<DataCompliances> values = new HashSet<>();
          for (DataCompliances e : DataCompliances.values()) {
            if ((bitwiseValue & e.getValue()) == e.getValue()) {
              values.add(e);
            }
          }

          return values;
        }
    }