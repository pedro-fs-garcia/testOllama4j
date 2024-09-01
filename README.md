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
```

to start ollama:
```
ollama serve
```


## Installing Maven:
```
sudo apt update
sudo apt maven
```

## clone the repository

```
git clone https://github.com/pedro-fs-garcia/testOllama4j
cd testOllama4j
mvn clean install
mvn compile
mvn exec:java
```
