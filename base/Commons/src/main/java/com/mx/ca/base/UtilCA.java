/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;

import java.security.SecureRandom;

import java.util.Arrays;

/**
 *
 * @author jbecerril
 */
public class UtilCA {

    public static <T> T convertirDtoModel(Object obj, Class<T> definitionRef) {
        T t = null;

        ModelMapper objectMapper = new ModelMapper();
        t = objectMapper.map(obj, definitionRef);
//       ObjectMapper objectMapper = new ObjectMapper();
//        t = objectMapper.convertValue(obj, definitionRef);

        return t;

    }

    public static <T> List<T> convertirDtoModelList(List<T> objs, Class<T> targetClass) {
        ModelMapper objectMapper = new ModelMapper();

        return objs
                .stream()
                .map(element -> objectMapper.map(element, targetClass))
                .collect(Collectors.toList());

    }

    public static String Encriptar(String texto) {
        String encript = DigestUtils.sha512Hex(texto);

        return encript;
    }

    public static String generarTokenApiRest(String cadena) {
        Date horaActual = obtenerHoraMexico();
        cadena = cadena + horaActual.toString();
        cadena = EncriptarLlAVE(cadena);
        return Encriptar(cadena);

    }

    public static Date obtenerHoraMexico() {

        try {
            Date date = new Date(new Date().getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
            SimpleDateFormat formatterNY = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tzNY = TimeZone.getDefault();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(new Date().getTime());
            cal.setTimeZone(tz);
            formatter.setCalendar(cal);
            String strDate = formatter.format(date);
            formatterNY.setTimeZone(tzNY);

            Date respuesta = formatterNY.parse(strDate);

            return respuesta;
        } catch (ParseException ex) {
            Logger.getLogger(UtilCA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();

    }

    public static Date obtenerSoloFechaMexico() {

        try {
            Date date = new Date(new Date().getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
            SimpleDateFormat formatterNY = new SimpleDateFormat("yyyy-MM-dd");
            TimeZone tzNY = TimeZone.getDefault();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(new Date().getTime());
            cal.setTimeZone(tz);
            formatter.setCalendar(cal);
            String strDate = formatter.format(date);
            formatterNY.setTimeZone(tzNY);

            Date respuesta = formatterNY.parse(strDate);

            return respuesta;
        } catch (ParseException ex) {
            Logger.getLogger(UtilCA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();

    }

    public static Date parseStringToDate(String date, String patter) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(patter);
            return formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(UtilCA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date parseStringToDate(String date) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat();
            return formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(UtilCA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String formatDate(Date date, String patter) {

        SimpleDateFormat formatter = new SimpleDateFormat(patter);
        return formatter.format(date);

    }

    public static boolean validaContrasena(String pass) {
        /*
        String regex = "^(?=.*[A-Za-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,12}$";

        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(pass);
        return mat.matches();
         */
        boolean valida = false;
        String clave = pass.trim();
        //Valida si la contraseÃ±a estÃ¡ entre 8 y 12 caracteres
        if (clave.length() >= 8 && clave.length() <= 12) {
            //Valida si posee al menos una mayuscula
            for (int i = 65; i < 91; i++) {
                for (int j = 0; j < clave.length(); j++) {
                    if (clave.charAt(j) == i) {
                        valida = true;
                    }
                }
            }

            //Valida si posee al menos una minuscula
            if (valida == true) {
                valida = false;
                for (int i = 97; i < 123; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
            }

            //Valida si posee un nÃºmero
            if (valida == true) {
                valida = false;
                for (int i = 48; i < 58; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
            }

            //Valida si posee caracteres especiales
            if (valida == true) {
                valida = false;
                for (int i = 33; i < 48; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
                for (int i = 58; i < 65; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
                for (int i = 91; i < 97; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
                for (int i = 123; i < 255; i++) {
                    for (int j = 0; j < clave.length(); j++) {
                        if (clave.charAt(j) == i) {
                            valida = true;
                        }
                    }
                }
            }
        }
        return valida;
    }

    public static String quitarAcentos(String original) {

        String cadenaNormalize = Normalizer.normalize(original, Normalizer.Form.NFD);
        String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
        System.out.println("Resultado: " + cadenaSinAcentos);
        return cadenaSinAcentos;
    }

    public static String generateRandomPassword(int len) {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!$%&/()=?¿+*}{][-";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String EncriptarLlAVE(String texto) {

        String secretKey = Constantes.KEYENCRIPTION; //llave para encriptar datos  
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance(Constantes.SHA256);
            byte[] digestOfPassword = md.digest(secretKey.getBytes(Constantes.UTF8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, Constantes.ALGORITMO);
            Cipher cipher = Cipher.getInstance(Constantes.ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes(Constantes.UTF8);
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String DesencriptarLlave(String textoEncriptado) throws Exception {

        String secretKey = Constantes.KEYENCRIPTION; //llave para encriptar datos   
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes(Constantes.UTF8));
            MessageDigest md = MessageDigest.getInstance(Constantes.SHA256);
            byte[] digestOfPassword = md.digest(secretKey.getBytes(Constantes.UTF8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, Constantes.ALGORITMO);

            Cipher decipher = Cipher.getInstance(Constantes.ALGORITMO);
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, Constantes.UTF8);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

}
