/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.util;

/**
 *
 * @author carlos.ribeiro
 */
public class StringUtil {
    public static boolean isNullOrWhiteSpace(String text){

        return text == null || text.isEmpty() || text.trim().isEmpty();
    }
}
