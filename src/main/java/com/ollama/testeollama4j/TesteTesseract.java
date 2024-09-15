// https://ivanqueiroz.dev/2020/07/2020-08-30-ocr-java-tesseract-tess4j.html

package com.ollama.testeollama4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesteTesseract {

    public static void main(String[] args) throws Exception {
        Tesseract tess = new Tesseract();
        
        // datapath for windows
        tess.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tess.setLanguage("por");

        // datapath for linux
        // tess.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
        // tess.setLanguage("por");
        
        File imgFile = new File("/home/pedro/Imagens/2col.png");

        // File imgFile = new File("C:\\Users\\Home\\Pictures\\matricula\\rg.jpg");
        // File convert = new File("C:\\Users\\Home\\Pictures\\matricula\\rg.png");
        // File convertedFile = convertToPNG(imgFile, convert);

        try{
            String result = tess.doOCR(imgFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    public static File convertToPNG(File inputImageFile, File outputImageFile) throws IOException {
        // Carregar a imagem do arquivo de entrada
        BufferedImage inputImage = ImageIO.read(inputImageFile);

        // Verificar se a imagem foi carregada com sucesso
        if (inputImage == null) {
            throw new IOException("Não foi possível ler a imagem do arquivo: " + inputImageFile.getPath());
        }

        // Escrever a imagem no formato PNG
        ImageIO.write(inputImage, "png", outputImageFile);

        // Retornar o arquivo PNG gerado
        return outputImageFile;
    }

}
