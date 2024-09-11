package com.ollama.testeollama4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.models.Model;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;

/**
 *
 * @author pedro
 */
public class TesteModelos {

    public static void main(String[] args) throws Exception {
        String model = "gemma2:2b";
        String prompt = "describe the image in ten words";
        String imagePath = "C:\\Users\\Home\\Pictures\\ai\\001.jpg";
        float temperatura = 08f;

        testFromImage(model, prompt, imagePath, temperatura);

    }

    


    public static HashMap<String, Object> generateFromImage(String modelName, String prompt, String filePath, float temperatura) throws Exception{
        // método para os modelos llava e moondream
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(240);

        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(temperatura);

        List<File> fileList =  List.of(new File(filePath));
        OllamaResult result = ollamaAPI.generateWithImageFiles(modelName, prompt, fileList, options.build());

        String response = result.getResponse();
        long responseTime = result.getResponseTime();

        HashMap<String, Object> results = new HashMap<>();
        results.put("modelo", modelName);
        results.put("temperatura", temperatura);
        results.put("prompt", prompt);
        results.put("imagem", filePath);
        results.put("resposta", response);
        results.put("tempo", responseTime);

        return results;
    }

    public static void testFromImage(String modelName, String prompt, String filePath, float temperatura) throws Exception{
        HashMap<String, Object> resposta =  generateFromImage(modelName, prompt, filePath, temperatura);
        
        Gson gson = new Gson();
        String json = gson.toJson(resposta);
        try (FileWriter file = new FileWriter("resposta.json")) {
            file.write(json);
            System.out.println("Arquivo JSON salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Object> generateFromPrompt(String modelName, String prompt, String imagePath, float temperatura) throws Exception{
        // método para os modelos llava e moondream
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(240);

        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(temperatura);

        OllamaResult result = ollamaAPI.generate(modelName, imagePath + "\n" + prompt, true, options.build());

        String response = result.getResponse();
        long responseTime = result.getResponseTime();

        HashMap<String, Object> results = new HashMap<>();
        results.put("modelo", modelName);
        results.put("temperatura", temperatura);
        results.put("prompt", prompt);
        results.put("imagem", imagePath);
        results.put("resposta", response);
        results.put("tempo", responseTime);

        return results;
    }

    public static void testFromPrompt(String model, String prompt, String imagePath, float temperatura) throws Exception{
        HashMap<String, Object> resposta =  generateFromPrompt (model, prompt, imagePath, temperatura);
        Gson gson = new Gson();
        String json = gson.toJson(resposta);
        try (FileWriter file = new FileWriter("resposta.json")) {
            file.write(json);
            System.out.println("Arquivo JSON salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
