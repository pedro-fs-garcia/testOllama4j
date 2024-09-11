## Installing Ollama
```
curl -fsSL https://ollama.com/install.sh | sh
```
Once installed, Ollama will run on http://localhost:11434/

## Installing Ollama models
```
ollama pull llama2
ollama pull moondream
```

## Ollama
Check if Ollama is running:
```
systemctl status ollama.service
systemctl start ollama.service
systemctl stop ollama.service
```


## Installing Maven:
```
sudo apt update
sudo apt maven
```

## Installing Tesseract
```
sudo apt install tesseract-ocr
sudo apt install tesseract-oct-por
```

## clone the repository

```
git clone https://github.com/pedro-fs-garcia/testOllama4j
cd testOllama4j
mvn clean install
mvn compile
mvn exec:java
```

## TesteModelos.java
alterar o método main para testar os modelos, prompts e imagens diferentes
![main](/main.png)

- quando rodar, o projeto irá gerar um arquivo json no diretorio testOllama4j
- acesse o arquivo, leia a resposta e avalie a precisao do resultado gerado
- registre a precisao no local adequado
- copie o conteúdo desse json em um arquivo txt
- Adicionar o resultado no arquivo res_final.txt, no mesmo diretorio
- para cada teste, o arquivo será substituído pelo novo resultado. Adicione o novo resultado no arquivo txt na linha abaixo, conforme o exemplo:

![txt_exemplo](/txt_exemplo.png)

