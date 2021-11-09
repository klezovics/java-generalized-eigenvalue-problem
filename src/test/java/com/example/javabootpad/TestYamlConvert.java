package com.example.javabootpad;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

public class TestYamlConvert {

    @Test
    void testYamlConvert() throws IOException {

        // Step 1: Raad in file
        var in = this.getClass().getResourceAsStream("/test.yml");
        // System.out.println(in);
        String yml = IOUtils.toString(in,"UTF-8");


        // Step 2: Do in memory replacement to standard form
        Pattern p = Pattern.compile("!!(.*)");
        Matcher m = p.matcher(yml);

        String ymlWithReplacement = yml;
        while (m.find()) {
            var nameToken = m.group().substring(2);
            // System.out.println("Name token:"+nameToken);
            ymlWithReplacement=ymlWithReplacement.replace("!!"+nameToken, "objectType: "+nameToken);
        }
        // System.out.println(ymlWithReplacement);

        // Step 3: Do the parse ...
        Yaml yaml = new Yaml();
        var myYamlMap = (Map<String,Object>) yaml.load(ymlWithReplacement);

        for(var entry : myYamlMap.entrySet()) {
            System.out.println("Key:" + entry.getKey() + "Value:" + entry.getValue());
        }
    }
}
