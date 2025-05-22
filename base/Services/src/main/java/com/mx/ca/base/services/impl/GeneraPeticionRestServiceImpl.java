/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ca.base.services.GeneraPeticionRestService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jbecerril
 */
@Service("GeneraPeticionRest")
public class GeneraPeticionRestServiceImpl implements GeneraPeticionRestService {

    private  RestTemplate restTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final Logger logger = LogManager.getLogger(GeneraPeticionRestServiceImpl.class.getName());

    public  void verificaConexion() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();

        }
    }

    @Override
    public  <T> T generaPeticionGet(String url, Class<T> definitionRef) {
        T t = null;
        int contador = 1;
        while (contador < 4) {
            try {
                verificaConexion();
                t = restTemplate.getForObject(url, definitionRef);

                contador = 4;
            } catch (Exception e) {
                System.out.println("############### intento de envio: " + contador);

                System.out.println("############### url: " + url);
                logger.error(e.getMessage(), e);
                contador++;
            }

        }

        return t;

    }

}
