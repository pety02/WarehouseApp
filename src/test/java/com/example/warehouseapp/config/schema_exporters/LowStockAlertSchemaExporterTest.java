package com.example.warehouseapp.config.schema_exporters;

import com.google.genai.types.Schema;
import com.google.genai.types.Type;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LowStockAlertSchemaExporterTest {

    @Test
    void exportSchema_shouldReturnNonNullSchema() {
        Schema schema = LowStockAlertSchemaExporter.exportSchema();

        assertNotNull(schema, "Schema should not be null");
    }

    @Test
    void exportSchema_shouldBeObjectType() {
        Schema schema = LowStockAlertSchemaExporter.exportSchema();

        assertEquals(
                Type.Known.OBJECT,
                schema.type().orElseThrow().knownEnum(),
                "Top-level schema type should be OBJECT"
        );
    }

    @Test
    void exportSchema_shouldContainRequiredTopLevelFields() {
        Schema schema = LowStockAlertSchemaExporter.exportSchema();
        List<String> requiredFields = schema.required().orElseThrow();

        assertNotNull(requiredFields);
        assertTrue(requiredFields.containsAll(List.of(
                "alertDate",
                "message",
                "actualCount",
                "neededCount",
                "recommendations",
                "stockAvailability",
                "employees"
        )));
    }

    @Test
    void exportSchema_shouldDefineAllTopLevelProperties() {
        Schema schema = LowStockAlertSchemaExporter.exportSchema();
        Map<String, Schema> properties = schema.properties().orElseThrow();

        assertNotNull(properties);
        assertTrue(properties.containsKey("alertDate"));
        assertTrue(properties.containsKey("message"));
        assertTrue(properties.containsKey("actualCount"));
        assertTrue(properties.containsKey("neededCount"));
        assertTrue(properties.containsKey("recommendations"));
        assertTrue(properties.containsKey("createdBy"));
        assertTrue(properties.containsKey("updatedBy"));
        assertTrue(properties.containsKey("createdAt"));
        assertTrue(properties.containsKey("updatedAt"));
        assertTrue(properties.containsKey("stockAvailability"));
        assertTrue(properties.containsKey("employees"));
    }

    @Test
    void stockAvailability_shouldBeObjectWithRequiredFields() {
        Schema schema = LowStockAlertSchemaExporter.exportSchema();
        Schema stockAvailability = schema.properties().orElseThrow().get("stockAvailability");

        assertNotNull(stockAvailability);

        List<String> required = stockAvailability.required().orElseThrow();
        assertNotNull(required);
        assertTrue(required.containsAll(List.of(
                "piecesCount",
                "item",
                "warehouseZone"
        )));
    }

    @Test
    void stockAvailability_shouldContainExpectedProperties() {
        Schema stockAvailability =
                LowStockAlertSchemaExporter.exportSchema()
                        .properties()
                        .orElseThrow()
                        .get("stockAvailability");

        Map<String, Schema> properties = stockAvailability.properties().orElseThrow();
        assertNotNull(properties);

        // Compare using .known() which returns Type.Known enum
        assertEquals(Type.Known.INTEGER, properties.get("piecesCount").type().orElseThrow().knownEnum());
        assertEquals(Type.Known.STRING, properties.get("item").type().orElseThrow().knownEnum());
        assertEquals(Type.Known.STRING, properties.get("warehouseZone").type().orElseThrow().knownEnum());
        assertEquals(Type.Known.STRING, properties.get("createdBy").type().orElseThrow().knownEnum());
        assertEquals(Type.Known.STRING, properties.get("updatedBy").type().orElseThrow().knownEnum());
        assertEquals(Type.Known.STRING, properties.get("createdAt").type().orElseThrow().knownEnum());
        assertEquals(Type.Known.STRING, properties.get("updatedAt").type().orElseThrow().knownEnum());
    }

    @Test
    void employees_shouldBeArrayOfStrings() {
        Schema employees =
                LowStockAlertSchemaExporter.exportSchema()
                        .properties()
                        .orElseThrow()
                        .get("employees");

        assertNotNull(employees);
        assertEquals(Type.Known.ARRAY, employees.type().orElseThrow().knownEnum());

        Schema itemSchema = employees.items().orElseThrow();
        assertNotNull(itemSchema);
        assertEquals(Type.Known.STRING, itemSchema.type().orElseThrow().knownEnum());
    }

    @Test
    void prompt_shouldNotBeEmpty() {
        assertNotNull(LowStockAlertSchemaExporter.PROMPT);
        assertFalse(LowStockAlertSchemaExporter.PROMPT.isBlank());
    }
}
