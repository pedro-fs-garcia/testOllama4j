/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ollama.testeollama4j;

import java.io.File;
import java.util.List;

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
        // testPrompt();
        // testImage();


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
        String response2 = generateWithImage(model2, prompt2, filePath);
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

    
    public static String generateWithImage(String modelName, String prompt, String filePath) throws Exception{
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(120);

        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(0.8f);

        List<File> fileList =  List.of(new File(filePath));
        OllamaResult result = ollamaAPI.generateWithImageFiles(modelName, prompt, fileList, options.build());
        String response = result.getResponse();
        return response;
    }

}