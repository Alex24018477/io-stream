package analyzer;

//1: Написать программу FileAnalyzer, которая в консоли принимает 2 параметра:
//        1) путь к файлу
//        2) слово
//        Usage:
//        java FileAnalyzer C:/test/story.txt duck
//
//        Выводит:
//        1) Кол-во вхождений искомого слова в файле
//        2) Все предложения содержащие искомое слово(предложение заканчивается символами ".", "?", "!"), каждое предложение с новой строки.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerTest {
    FileAnalyzer fileAnalyzer;

    @BeforeEach
    void setUp() {

        fileAnalyzer = new FileAnalyzer("Test word.txt", "Java");
    }

    //    @Test
//    public void testFileIsPresent(){
//        File file = new File(fileAnalyzer.getFilePath());
//        File fakeFile = new File("/task.txt");
//
//        assertNotNull(file);
//        assertNull(fakeFile);
//    }
    @Test
    public void testReadFile() throws IOException {
        assertEquals("Hello JAVA!", fileAnalyzer.readFile());
    }

    @Test
    public void testFindWordInFile() throws IOException {
        assertEquals(1, fileAnalyzer.findWord());
    }

    @Test
    public void testFindSentenceInFileWithWord() throws IOException {
        fileAnalyzer.setFilePath("Test sentence.txt");
        assertEquals("Hello Java.\n" +
                "Hello Java?\n" +
                "Hello Java!\n" +
                "Hello  Hello Java.\n" +
                "Java!", fileAnalyzer.findSentence());
    }


}