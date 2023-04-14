//package data.modeling.adt.addons.hivemetastore;
//
//import org.apache.hadoop.hive.metastore.api.FieldSchema;
//import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
//import org.apache.hadoop.hive.metastore.api.Table;
//import org.apache.hadoop.hive.metastore.events.AlterTableEvent;
//import org.apache.hadoop.hive.metastore.events.PreEventContext;
//import org.apache.hadoop.hive.ql.hooks.HookContext;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//
//import static org.mockito.Mockito.*;
//
//public class SchemaValidationAddonTest {
//
//    private SchemaValidationAddon hook;
//    private PreEventContext context;
//    private AlterTableEvent event;
//    private Table oldTable;
//    private Table newTable;
//
//    @Before
//    public void setUp() {
//        hook = new SchemaValidationAddon();
//
//        // Create mock objects for the HookContext and PreEventContext
//        HookContext hookContext = mock(HookContext.class);
//        context = mock(PreEventContext.class);
//
//        // Create mock AlterTableEvent and TableMetadata objects
//        event = mock(AlterTableEvent.class);
//        oldTable = mock(Table.class);
//        newTable = mock(Table.class);
//
//        when(event.getOldTable()).thenReturn(oldTable);
//        when(event.getNewTable()).thenReturn(newTable);
//    }
//
//    @Test
//    public void testTableSchemaNotChanged() throws Exception {
//        // Set up the test data
//        StorageDescriptor oldSd = new StorageDescriptor();
//        oldSd.setCols(Arrays.asList(new FieldSchema("col1", "int", null)));
//        when(oldTable.getSd()).thenReturn(oldSd);
//
//        StorageDescriptor newSd = new StorageDescriptor();
//        newSd.setCols(Arrays.asList(new FieldSchema("col1", "int", null)));
//        when(newTable.getSd()).thenReturn(newSd);
//
//        // Call the hook method with the mock objects
//        hook.validateTable(event.getOldTable(), event.getNewTable());
//
//        // Verify that the expected methods were called
//        verify(oldTable, times(1)).getSd();
//        verify(newTable, times(1)).getSd();
//    }
//
//    @Test(expected = SchemaValidationAddon.ValidationException.class)
//    public void testTableSchemaChanged() throws Exception {
//        // Set up the test data
//        StorageDescriptor oldSd = new StorageDescriptor();
//        oldSd.setCols(Arrays.asList(new FieldSchema("col1", "int", null)));
//        when(oldTable.getSd()).thenReturn(oldSd);
//
//        StorageDescriptor newSd = new StorageDescriptor();
//        newSd.setCols(Arrays.asList(
//                new FieldSchema("col1", "int", null),
//                new FieldSchema("col2", "string", null)));
//        when(newTable.getSd()).thenReturn(newSd);
//
//        // Call the hook method with the mock objects
//        hook.validateTable(event.getOldTable(), event.getNewTable());
//    }
//}
//
//
