package com.smartinvestor.smartinvestor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.out;
import static sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums.TAG;

public class JsonToCsv {


    public void convertJsonToCsv(String jsonfileName, Object data) throws IOException {
        out.println(jsonfileName);
            try {

                if (data== null|| jsonfileName==null) {
                    out.println("No data to convert");
                    return;
                }
                FileWriter file = new FileWriter(jsonfileName);

                file.write(String.valueOf(data));
                out.println(jsonfileName + " converted to csv");
                file.flush();

                file.close();
            } catch (IOException io) {
                Log.error(io.getMessage());
        }


        JsonNode jsonTree = new ObjectMapper().readTree(new File(jsonfileName));
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(jsonfileName.substring(0,jsonfileName.length()-4)+".csv"), jsonTree);
        out.println(jsonfileName + " converted to csv");
        Log.info(String.valueOf(TAG),jsonfileName + " converted to csv");


    }


}
