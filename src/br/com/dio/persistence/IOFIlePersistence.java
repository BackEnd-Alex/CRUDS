package br.com.dio.persistence;

import java.io.*;

public class IOFIlePersistence implements FilePersistence {

    private final String currentDir = System.getProperty("user.dir");
    private final String storeDir = "/managedFiles_IO";
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
        try (
                var fileWhiter = new FileWriter(currentDir + storeDir + fileName, true);
                var bufferedWriter = new BufferedWriter(fileWhiter);
                var printWriter = new PrintWriter(bufferedWriter);
        ) {
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Implementation for writing data to a file
        return  data;
    }

    @Override
    public boolean remove(final String sentence) {
        // Implementation for removing a sentence from a file
        return true; // Assuming the removal was successful
    }

    @Override
    public String replace(final String oldContent, final String newContent) {
        // Implementation for replacing content in a file
        return "Replaced '" + oldContent + "' with '" + newContent + "'";
    }

    @Override
    public String findAll() {
        // Implementation for finding all content in a file
        return "All content found";
    }

    @Override
    public String findBy(final String sentence) {
        // Implementation for finding a specific sentence in a file
        return "Found: " + sentence;
    }

    private void clearFile() {
        try (OutputStream outputStream = new FileOutputStream(currentDir + storeDir + "/" + fileName)) {
            System.out.printf("Inicializando recursos (%s) \n", currentDir + storeDir + "/" + fileName);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
