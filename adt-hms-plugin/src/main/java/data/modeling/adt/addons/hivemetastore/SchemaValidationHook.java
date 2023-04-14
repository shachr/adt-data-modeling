package data.modeling.adt.addons.hivemetastore;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.MetaStorePreEventListener;
import org.apache.hadoop.hive.metastore.api.*;
import org.apache.hadoop.hive.metastore.events.*;

public class SchemaValidationHook extends MetaStorePreEventListener {

    public SchemaValidationHook(Configuration config) {
        super(config);
    }

    @Override
    public void onEvent(PreEventContext preEventContext) {
        if (preEventContext.getEventType() == PreEventContext.PreEventType.CREATE_TABLE) {
            PreCreateTableEvent preCreateTableEvent = (PreCreateTableEvent)preEventContext;

            String tableName = preCreateTableEvent.getTable().getTableName();
            // Add validation logic for create table operation
        } else if (preEventContext.getEventType() == PreEventContext.PreEventType.DROP_TABLE) {

            PreDropTableEvent preDropTableEvent = (PreDropTableEvent)preEventContext;
            String tableName = preDropTableEvent.getTable().getTableName();
            // Add validation logic for drop table operation
        }
    }
}







