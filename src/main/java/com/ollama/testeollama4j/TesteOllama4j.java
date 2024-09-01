/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ollama.testeollama4j;

import java.util.List;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.models.Model;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.types.OllamaModelType;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;

/**
 *
 * @author pedro
 */
public class TesteOllama4j {

    public static void main(String[] args) throws Exception {
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(60);
        System.out.println("ollama is running: " + ollamaAPI.ping());
        List<Model> models = ollamaAPI.listModels();
        System.out.println("models: ");
        models.forEach(model -> System.out.println(model.getName()));
        OptionsBuilder options = new OptionsBuilder();
        OllamaResult result = ollamaAPI.generate(OllamaModelType.GEMMA2, "who are you?", false, options.build());
        System.out.println(result.getResponse());

    }

}
