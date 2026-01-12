package com.example.warehouseapp.config.schema_exporters;

import com.google.genai.types.Schema;
import com.google.genai.types.Type;

import java.util.List;
import java.util.Map;

public class LowStockAlertSchemaExporter {
    public static final String PROMPT = """
            Generate a low stock alert in JSON format with the following fields:
            - alertDate (string): The date when the alert is generated.
            - message (string): A descriptive message about the low stock situation.
            - actualCount (integer): The current number of items in stock.
            - neededCount (integer): The number of items needed to replenish stock.
            - recommendations (string): Suggestions for addressing the low stock issue.
            - createdBy (string): The name of the person who created the alert.
            - updatedBy (string): The name of the person who last updated the alert.
            - createdAt (string): The timestamp when the alert was created.
            - updatedAt (string): The timestamp when the alert was last updated.
            - stockAvailability (object): An object containing details about stock availability:
                - piecesCount (integer): The number of pieces available in stock.
                - createdBy (string): The name of the person who created the stock availability record.
                - updatedBy (string): The name of the person who last updated the stock availability record.
                - createdAt (string): The timestamp when the stock availability record was created.
                - updatedAt (string): The timestamp when the stock availability record was last updated.
                - item (string): The identifier or name of the item.
                - warehouseZone (string): The zone in the warehouse where the item is stored.
            - employees (array of strings): A list of employee names responsible for managing the low stock alert.
            
            Use these data for analysis:
            """;

    private LowStockAlertSchemaExporter() {}

    public static Schema exportSchema() {
        Schema.Builder builder = Schema.builder()
                .type(Type.Known.OBJECT)
                .properties(
                        Map.ofEntries(
                                Map.entry("alertDate", Schema.builder()
                                        .type(Type.Known.STRING)
                                        .build()),
                                Map.entry("message", Schema.builder()
                                        .type(Type.Known.STRING)
                                        .build()),
                                Map.entry("actualCount", Schema.builder()
                                        .type(Type.Known.INTEGER)
                                        .build()),
                                Map.entry("neededCount", Schema.builder()
                                        .type(Type.Known.INTEGER)
                                        .build()),
                                Map.entry("recommendations", Schema.builder()
                                        .type(Type.Known.STRING)
                                        .build()),
                                Map.entry("createdBy", Schema.builder()
                                        .type(Type.Known.STRING)
                                        .build()),
                                Map.entry("updatedBy", Schema.builder()
                                        .type(Type.Known.STRING)
                                        .build()),
                                Map.entry("createdAt", Schema.builder()
                                        .type(Type.Known.STRING)
                                        .build()),
                                Map.entry("updatedAt", Schema.builder()
                                        .type(Type.Known.STRING)
                                        .build()),

                                Map.entry("stockAvailability", Schema.builder()
                                        .type(Type.Known.OBJECT)
                                        .properties(
                                                Map.ofEntries(
                                                        Map.entry("piecesCount", Schema.builder().type(Type.Known.INTEGER).build()),
                                                        Map.entry("createdBy", Schema.builder().type(Type.Known.STRING).build()),
                                                        Map.entry("updatedBy", Schema.builder().type(Type.Known.STRING).build()),
                                                        Map.entry("createdAt", Schema.builder().type(Type.Known.STRING).build()),
                                                        Map.entry("updatedAt", Schema.builder().type(Type.Known.STRING).build()),
                                                        Map.entry("item", Schema.builder().type(Type.Known.STRING).build()),
                                                        Map.entry("warehouseZone", Schema.builder().type(Type.Known.STRING).build())
                                                )
                                        )
                                                .required(List.of(
                                                        "piecesCount",
                                                        "item",
                                                        "warehouseZone"
                                                ))
                                        .build()
                                ),

                                Map.entry("employees", Schema.builder()
                                        .type(Type.Known.ARRAY)
                                        .items(Schema.builder()
                                                .type(Type.Known.STRING)
                                                .build())
                                        .build())
                        )
                )
                .required(List.of(
                        "alertDate",
                        "message",
                        "actualCount",
                        "neededCount",
                        "recommendations",
                        "stockAvailability",
                        "employees"
                ));

        return builder.build();
    }
}
