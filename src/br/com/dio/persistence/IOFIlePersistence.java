package br.com.dio.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IOFIlePersistence implements FilePersistence {

    private final String currentDir = System.getProperty("user.dir");
    private final String storeDir = File.separator + "managedFiles_IO";
    private final String fileName;

    public IOFIlePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storeDir);
        if (!file.exists() && !file.mkdir())
            throw new IOException("Erro ao criar arquivo");
        clearFile();
    }

    @Override
    public String write(final String data) {
        String filePath = currentDir + storeDir + File.separator + fileName;
        try (
                var fileWriter = new FileWriter(filePath, true);
                var bufferedWriter = new BufferedWriter(fileWriter);
                var printWriter = new PrintWriter(bufferedWriter);
        ) {
            printWriter.println(data);
            System.out.println("Dados escritos com sucesso em: " + filePath);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Implementation for writing data to a file
        return data;
    }

    @Override
    public boolean remove(final String sentence) {
        // Implementation for removing a sentence from a file
        var contentList = toListString();


        if (contentList.stream().noneMatch(c -> c.contains(sentence))) return false;

        clearFile();
        contentList.stream().filter(c -> !c.contains(sentence));

        return true;
    }

    @Override
    public String replace(final String oldContent, final String newContent) {
        // Implementation for replacing content in a file
        var contentList = toListString();

        if (contentList.stream().noneMatch(c -> c.contains(oldContent))) return "";
        clearFile();
        contentList.stream().map(c -> c.contains(oldContent) ? c.replace(oldContent, newContent) : c)
                .forEach(this::write);
        return newContent;
    }

    @Override

        public String findAll () {
            // Implementation for finding all content in a file
            var content = new StringBuilder();
            try (var reader = new BufferedReader(new FileReader(currentDir + storeDir + "/" + fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
                System.out.println("Conteúdo lido com sucesso de: " + currentDir + storeDir + "/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        public String findBy ( final String sentence){
            // Implementation for finding a specific sentence in a file
            String found = "";
            try (var reader = new BufferedReader(new FileReader(currentDir + storeDir + "/" + fileName))) {
                String line = reader.readLine();
                while (line != null) {
                    if (line.contains((sentence))) {
                        found = line;
                        break;
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            return found;
        }

    private List<String> toListString() {
        var content = findAll();
        return new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());

    }


        private void clearFile() {
            try (OutputStream outputStream = new FileOutputStream(currentDir + storeDir + "/" + fileName)) {
                System.out.printf("Inicializando recursos (%s) \n", currentDir + storeDir + "/" + fileName);
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }

