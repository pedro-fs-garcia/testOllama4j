package com.ollama.testeollama4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;

/**
 *
 * @author pedro
 */
public class TesteModelos {

    public static void main(String[] args) throws Exception {
        // testar os modelos: moondream, moondream:v2, llava, llava-llama3, llava-phi3, minicpm-v
        String imageModel = "llava";
        String imagePrompt = "transcribe the text in the image";
        String filePath = "/home/pedro/Imagens/Capturas de tela/db01.png";
        float temperatura = 08f;
        try {
        testFromImage(imageModel, imagePrompt, filePath, temperatura);
            
        } catch (OllamaBaseException e) {
            e.printStackTrace();
        }

        //testar os modelos: llama2, llama3.1, gemma2, gemma2:2b, phi3:mini, mistral
        String textModel = "gemma2:2b";
        String textPrompt = "describe the image in ten words";
        String imagePath = "/home/pedro/Imagens/Capturas de tela/db01.png";
        float temperatura2 = 08f;
        // testFromPrompt(textModel, textPrompt, imagePath, temperatura2);
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
        results.put("precisao", 0);

        return results;
    }

    public static void testFromImage(String modelName, String prompt, String filePath, float temperatura) throws Exception{
        HashMap<String, Object> resposta =  generateFromImage(modelName, prompt, filePath, temperatura);
        
        Gson gson = new Gson();
        String json = gson.toJson(resposta);
        try (FileWriter file = new FileWriter("respostaImagem.json")) {
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
        results.put("precisao", 0);

        return results;
    }

    public static void testFromPrompt(String model, String prompt, String imagePath, float temperatura) throws Exception{
        HashMap<String, Object> resposta =  generateFromPrompt (model, prompt, imagePath, temperatura);
        Gson gson = new Gson();
        String json = gson.toJson(resposta);
        try (FileWriter file = new FileWriter("respostaPrompt.json")) {
            file.write(json);
            System.out.println("Arquivo JSON salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
