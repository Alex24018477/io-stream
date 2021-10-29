package analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class FileAnalyzer {
    private String filePath;
    private String word;

    public FileAnalyzer() {
    }

    public FileAnalyzer(String filePath, String word) {
        this.filePath = filePath;
        this.word = word;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

//    public String getTextFromFile() {
//        return textFromFile;
//    }

    //    public String readFromFile(){
//        return "";
//    }


    public String readFile() throws IOException {
        File file = new File(filePath);
        if (!file.isFile()) {
            return "Enter right file path";
        }
        InputStream inputStream = new FileInputStream(filePath);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = inputStream.read()) != -1) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    public int findWord() throws IOException {
        int count = 0;
        String textFromFile = readFile();
        if (textFromFile.length() != 0) {
            String[] wordsFromText = textFromFile.split(" ");
            for (String wordFromText : wordsFromText) {
                if (wordFromText.toUpperCase().contains(word.toUpperCase())) {
                    count++;
                }
            }
        }
        return count;
    }

    public String findSentence() throws IOException {
        StringJoiner sj = new StringJoiner("\n");
        for (String sentence : findAllSentences()) {
            if (sentence.toUpperCase().contains(word.toUpperCase())) {
                sj.add(sentence);
            }
        }
        return sj.toString();
    }

    private List<String> findAllSentences() throws IOException {
        List<String> allSentences = new ArrayList<String>();
        String textFromFile = readFile();
        if (textFromFile.length() != 0) {
            String[] wordsFromText = textFromFile.split(" ");
            StringJoiner sj = new StringJoiner(" ");
            for (String wordFromText : wordsFromText) {
                sj.add(wordFromText);
                if (wordFromText.endsWith(".") || wordFromText.endsWith("?") || wordFromText.endsWith("!")) {
                    allSentences.add(sj.toString());
                    sj = new StringJoiner(" ");
                }
            }
        }
        return allSentences;
    }

    public static void main(String[] args) throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer("Test sentence.txt", "Java");
        System.out.println(fileAnalyzer.findSentence());
//        for (String string: fileAnalyzer.findAllSentences()){
//            System.out.println(string);
//        }
    }
}
