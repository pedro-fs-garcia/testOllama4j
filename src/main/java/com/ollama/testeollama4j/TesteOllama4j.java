/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ollama.testeollama4j;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;

/**
 *
 * @author pedro
 */
public class TesteOllama4j {

    public static void main(String[] args) throws Exception {
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        System.out.println("ollama is running: " + ollamaAPI.ping());
    }

}
