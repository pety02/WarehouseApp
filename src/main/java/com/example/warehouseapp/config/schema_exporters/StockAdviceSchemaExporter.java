package com.example.warehouseapp.config.schema_exporters;

import com.google.genai.types.Schema;
import com.google.genai.types.Type;

import java.util.List;
import java.util.Map;

public class StockAdviceSchemaExporter {

    private StockAdviceSchemaExporter() {}

    public static Schema exportSchema() {
        return Schema.builder()
                .type(Type.Known.OBJECT)
                .properties(Map.of(
                        "createdAt", Schema.builder()
                                .type(Type.Known.STRING)
                                .build(),
                        "updatedAt", Schema.builder()
                                .type(Type.Known.STRING)
                                .build(),
                        "validUntil", Schema.builder()
                                .type(Type.Known.STRING)
                                .build(),
                        "reasoning", Schema.builder()
                                .type(Type.Known.STRING)
                                .build(),
                        "confidence", Schema.builder()
                                .type(Type.Known.NUMBER)
                                .build(),
                        "isActioned", Schema.builder()
                                .type(Type.Known.BOOLEAN)
                                .build(),
                        "createdByModelVersion", Schema.builder()
                                .type(Type.Known.STRING)
                                .build(),
                        "updatedByModelVersion", Schema.builder()
                                .type(Type.Known.STRING)
                                .build(),
                        "actions", Schema.builder()
                                .type(Type.Known.ARRAY)
                                .items(Schema.builder()
                                        .type(Type.Known.OBJECT)
                                        .properties(Map.of(
                                                "item", Schema.builder()
                                                        .type(Type.Known.STRING)
                                                        .build(),
                                                "actionDescription", Schema.builder()
                                                        .type(Type.Known.STRING)
                                                        .build(),
                                                "isActioned", Schema.builder()
                                                        .type(Type.Known.BOOLEAN)
                                                        .build()
                                        ))
                                        .required(List.of("item", "actionDescription"))
                                        .build())
                                .build()
                ))
                .required(List.of(
                        "createdAt",
                        "validUntil",
                        "actions",
                        "confidence",
                        "isActioned",
                        "createdByModelVersion"
                ))
                .build();
    }
}
