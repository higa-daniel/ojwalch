import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

class Parser {
    static void parseAux(String inputPath, String inputFile) {
        String row = "";
        String outputFile = "";
        outputFile = inputFile.substring(0, inputFile.length() - 4);
        System.out.println(outputFile);
        outputFile = outputFile + "_parsed.txt";

        try {
            Files.deleteIfExists(Path.of(inputPath + outputFile));
            File file = new File(inputPath + inputFile);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            while ((row = br.readLine()) != null) {
                if (row.contains("[MSD]")) {
                    String[] output = row.split(" ");
                    BufferedWriter outputWriter = new BufferedWriter(new FileWriter(inputPath + outputFile, true));

                    for (int i = 0; i < output.length; i++) {
                        if (i == 0 || i == 1 || i == 2 || i == 4) { // Configure which indices we want to keep
                            System.out.println(output[i]);
                            outputWriter.write(output[i] + " ");
                        }
                    }
                    outputWriter.newLine();
                    outputWriter.close();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void parseInput() {
        String inputPath = "/Users/daniel-higa/development/";
        String inputPrefix = "input";
        String extension = "txt";

        // files folder
        File f = new File(inputPath);
        File[] matchingFiles = f.listFiles((dir, name) -> name.startsWith(inputPrefix) && name.endsWith(extension));
        if (matchingFiles != null) {
            System.out.println(matchingFiles.length);
            for (File matchingFile : matchingFiles) {
                System.out.println(matchingFile);
                String[] segments = matchingFile.getName().split("/");
                String inputFile = segments[segments.length - 1];
                System.out.println(inputFile);
                parseAux(inputPath, inputFile);
            }
        }
    }
}