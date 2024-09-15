package com.ollama.testeollama4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.models.Model;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TessOllama {
    
    public static void main (String[] args) throws Exception{
        String imagePath = "/home/pedro/Imagens/2col.png";
        String tesseractAnswer = getTesseractAnswer(imagePath).replace("\n", " ");

        String prompt = "The following text was extracted from an image. Give me a brief description of what the text is about";

        String answer = getOllamaAnswer(prompt);
        System.out.println(answer);

        Gson gson = new Gson();
        String json = gson.toJson(tesseractAnswer);
        try (FileWriter file = new FileWriter("respostaPrompt.json")) {
            file.write(json);
            System.out.println("Arquivo JSON salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getOllamaAnswer(String prompt) throws Exception{
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(180);
        System.out.println("ollama is running: " + ollamaAPI.ping());

        List<Model> models = ollamaAPI.listModels();
        System.out.println("models: ");
        models.forEach(model -> System.out.println(model.getName()));
        
        OptionsBuilder options = new OptionsBuilder();
        options.setTemperature(0.8f);

        OllamaResult result = ollamaAPI.generate("gemma2:2b", prompt, false, options.build());

        return result.getResponse();
    }


    public static String getTesseractAnswer(String imagePath) throws Exception{
        Tesseract tess = new Tesseract();

        // datapath for linux
        tess.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
        tess.setLanguage("por");
        
        File imgFile = new File(imagePath);

        try{
            String result = tess.doOCR(imgFile);
            System.out.println(result);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "pobrema aqui";
        }
    }
}
