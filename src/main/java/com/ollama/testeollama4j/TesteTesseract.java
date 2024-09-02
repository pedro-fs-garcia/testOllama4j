// https://ivanqueiroz.dev/2020/07/2020-08-30-ocr-java-tesseract-tess4j.html

package com.ollama.testeollama4j;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesteTesseract {

    public static void main(String[] args) throws Exception {
        Tesseract tess = new Tesseract();
        // tess.setDatapath(datapath);
        tess.setLanguage("eng");

        File imgFile = new File("/home/pedro/Imagens/Capturas de tela/db01.png");

        try{
            String result = tess.doOCR(imgFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

}
