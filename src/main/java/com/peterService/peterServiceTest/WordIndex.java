/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peterService.peterServiceTest;

import com.peterService.peterServiceTest.structures.Trie;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artem
 */
public class WordIndex {

    private static final String PROP_FILE_NAME = "indexConf.properties";
    public static final String ENCODING = loadEncoding();
    public static final Integer BATCH_SIZE = loadBatchSize();
    private Trie trie;
    private int position;
    private String filename;

    public String getFilename() {
        return filename;
    }

    private static String loadEncoding() {
        try (InputStream input = new FileInputStream(PROP_FILE_NAME)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("encoding", "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(WordIndex.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return "UTF-8";
        }
    }

    private static Integer loadBatchSize() {
        try (InputStream input = new FileInputStream(PROP_FILE_NAME)) {
            Properties prop = new Properties();
            prop.load(input);
            return Integer.parseInt(prop.getProperty("batchSize", "512"));
        } catch (IOException ex) {
            Logger.getLogger(WordIndex.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return 512;
        }
    }

    public void loadFile(String filename) {
        this.filename = filename;
        trie = new Trie();
        position = 0;
        int status = 1;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), ENCODING))) {
            while (status != -1) {
                char[] chars = new char[BATCH_SIZE];
                status = reader.read(chars);
                StringBuilder sb = new StringBuilder();
                sb.append(chars);
                if (Character.isLetter(chars[chars.length - 1])) {
                    int read = 0;
                    while ((read = reader.read()) != -1) {
                        char ch = (char) read;
                        sb.append(ch);
                        if (!Character.isLetter(ch)) {
                            break;
                        }
                    }
                }
                indexString(sb.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(WordIndex.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public Set<Integer> getIndexes4Word(String searchWord) {
        return trie.get(searchWord);
    }

    private void indexString(String str) {
        int curr = position;
        int startPos = position;
        boolean started = false;
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (!started) {
                    startPos = curr;
                    started = true;
                    sb = new StringBuilder();
                }
                sb.append(ch);
            } else {
                if (started) {
                    trie.put(sb.toString(), startPos);
                    started = false;
                }
            }
            curr++;
        }
        position += str.length();
    }
}
