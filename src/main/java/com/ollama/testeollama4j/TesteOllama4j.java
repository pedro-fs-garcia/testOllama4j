/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ollama.testeollama4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.models.Model;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;

/**
 *
 * @author pedro
 */
public class TesteOllama4j {

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> resposta =  generateFromImage("llava", "describe the image briefly", "C:\\Users\\Home\\Pictures\\ai\\001.jpg", 0.8f);
        
        Gson gson = new Gson();
        String json = gson.toJson(resposta);
        try (FileWriter file = new FileWriter("resposta.json")) {
            file.write(json);
            System.out.println("Arquivo JSON salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void testPrompt() throws Exception{
        String model1 = "gemma2:2b";
        String prompt1 = "why is the Mona Lisa in Paris?";
        String response1 = generateWithPrompt(model1, prompt1);
        System.out.println(prompt1);
        System.out.println(response1);
    }

    public static void testImage() throws Exception{
        String model2 = "moondream";
        String prompt2 = "extract the text from this image";
        String filePath = "/home/pedro/Imagens/Capturas de tela/db01.png";
        HashMap<String, Object> response2 = generateFromImage(model2, prompt2, filePath, 0.8f);
        System.out.println(prompt2);
        System.out.println(response2);

    }

    public static String generateWithPrompt(String modelName, String prompt) throws Exception {
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(60);
        System.out.println("ollama is running: " + ollamaAPI.ping());

        List<Model> models = ollamaAPI.listModels();
        System.out.println("models: ");
        models.forEach(model -> System.out.println(model.getName()));
        
        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(0.8f);

        OllamaResult result = ollamaAPI.generate(modelName, prompt, false, options.build());

        return result.getResponse();
    }

    
    public static HashMap<String, Object> generateFromImage(String modelName, String prompt, String filePath, float temperatura) throws Exception{
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

    // public static HashMap<String, Object> generateFromMultipleImages(String modelName, String prompt, List<String> filePath, float temperatura) throws Exception{
    //     String host = "http://localhost:11434/";
    //     OllamaAPI ollamaAPI = new OllamaAPI(host);
    //     ollamaAPI.setRequestTimeoutSeconds(240);
    //     HashMap<String, Object> results = new HashMap<>();
    //     results.put("testes", filePath.size());

    //     OptionsBuilder options = new OptionsBuilder();
    //     options.setTemperature(temperatura);


    //     List<File> fileList = new LinkedList<>();
    //     for (String file : filePath){
    //         fileList.add(new File(file));
    //     }

    //     OllamaResult result = ollamaAPI.generateWithImageFiles(modelName, prompt, fileList, options.build());
    //     String response = result.getResponse();
    //     long responseTime = result.getResponseTime();

    //     results.put("modelo", modelName);
    //     results.put("temperatura", temperatura);
    //     results.put("prompt", prompt);
    //     results.put("imagem", fileList);
    //     results.put("resposta", response);
    //     results.put("tempo", responseTime);

    //     System.out.println(result.getResponse());

    //     return results;
    // }

}