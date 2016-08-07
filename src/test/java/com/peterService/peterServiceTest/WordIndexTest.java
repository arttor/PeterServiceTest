/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peterService.peterServiceTest;

import com.peterService.peterServiceTest.utils.FileReader;
import java.util.Iterator;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author artem
 */
public class WordIndexTest {

    private static final FileReader fileReader = new FileReader();
    private static final String filename = "testFile.txt";
    private static final String testWord = "федот";

    /**
     * Тест для демонстрации работы приложения.
     * В тесте загружается и индексируется файл  WordIndexTest.filename и осущесвляется поиск
     * позиций слова WordIndexTest.testWord в файле. После этого из файла читаются слова 
     * с указанных позиций и сверяются с искомым словом.
     */
    @Test
    public void testLoadFile() {
        WordIndex wi = new WordIndex();
        wi.loadFile(filename);
        Set<Integer> positions = wi.getIndexes4Word(testWord);
        assertNotNull(positions);
        Iterator<Integer> itr = positions.iterator();
        while (itr.hasNext()) {
            String fedot = fileReader.readWord(filename, itr.next(), testWord.length()).toLowerCase();
            assertTrue(testWord.toLowerCase().equals(fedot));
        }
    }
}
