package com.example.warehouseapp.config.schema_exporters;

import com.google.genai.types.Schema;
import com.google.genai.types.Type;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StockAdviceSchemaExporterTest {

    @Test
    void exportSchema_containsRequiredFields() {
        Schema schema = StockAdviceSchemaExporter.exportSchema();

        assertThat(schema.type()).isEqualTo(Type.Known.OBJECT);
        assertThat(schema.required())
                .contains(List.of(
                        "createdAt",
                        "validUntil",
                        "actions",
                        "confidence",
                        "isActioned",
                        "createdByModelVersion")
                );
    }
}
