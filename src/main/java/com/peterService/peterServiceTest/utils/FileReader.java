/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peterService.peterServiceTest.utils;

import com.peterService.peterServiceTest.WordIndex;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artem
 */
public class FileReader {

    /**
     * Метод для загрузки символов из файла с определенной позиции.
     * Используется в тесте для проверки работы индексирования.
     *
     * @param filename имя файла
     * @param wordPos позиция слова в файле
     * @param wordLenght длина слова
     * @return возвращает искомое слово
     */
    public String readWord(String filename, int wordPos, int wordLenght) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), WordIndex.ENCODING))) {
            char[] chars = new char[wordLenght];
            reader.skip(wordPos);
            reader.read(chars);
            return String.valueOf(chars);
        } catch (IOException ex) {
            Logger.getLogger(WordIndex.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

}
