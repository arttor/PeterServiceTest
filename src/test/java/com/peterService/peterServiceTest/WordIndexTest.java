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

    @Test
    public void testLoadFile() {
        WordIndex wi = new WordIndex();
        wi.loadFile(filename);
        Set<Integer> positions = wi.getIndexes4Word("Федот");
        assertNotNull(positions);
        Iterator<Integer> itr = positions.iterator();
        while (itr.hasNext()) {
            String fedot = fileReader.readWord(filename, itr.next(), "Федот".length());
            assertTrue("Федот".equals(fedot));
        }
    }
}
