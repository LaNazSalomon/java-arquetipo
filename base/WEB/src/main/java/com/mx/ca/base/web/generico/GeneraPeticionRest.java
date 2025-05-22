/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.web.generico;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jbecerril
 */
public class GeneraPeticionRest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final Logger logger = LogManager.getLogger(UtilServicios.class.getName());

    public static <T> T generaPeticionGet(String url, Class<T> definitionRef) {
        T t = null;
        int contador = 1;
        Client cliente = ClientBuilder.newClient();
        while (contador < 4) {
            try {

                cliente = ClientBuilder.newClient();
                //cliente.property("javax.ws.rs.client.Client", 300000);
                System.out.println("############### url de envio: " + url);

                t = cliente.target(url).request().accept(MediaType.APPLICATION_JSON_TYPE).get(definitionRef);
                System.out.println("############### json de respuesta: " + objectMapper.writeValueAsString(t));

                contador = 4;
            } catch (Exception e) {
                System.out.println("############### intento de envio: " + contador);

                System.out.println("############### url: " + url);
                logger.error(e.getMessage(), e);
                contador++;
            } finally {
                if (cliente != null) {
                    cliente.close();
                    cliente = null;
                }
            }

        }

        return t;

    }

    public static <T> List<T> generaPeticionGetList(String url, Class<T> definitionRef) {
        List<T> t = null;
        int contador = 1;
        Client cliente = ClientBuilder.newClient();

        while (contador < 4) {
            try {

                cliente = ClientBuilder.newClient();
                //cliente.property("javax.ws.rs.client.Client", 300000);
                System.out.println("############### url: " + url);

                String tmp = cliente.target(url).request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
                if (!tmp.equals("") && !tmp.isEmpty()) {
                    t = objectMapper.readValue(tmp, objectMapper.getTypeFactory().constructCollectionType(List.class, definitionRef));

                }
                System.out.println("############### json de respuesta: " + t);

                contador = 4;

            } catch (Exception e) {
                System.out.println("############### intento de envio: " + contador);

                System.out.println("############### url: " + url);
                logger.error(e.getMessage(), e);
                contador++;

            } finally {
                if (cliente != null) {
                    cliente.close();
                    cliente = null;
                }
            }
        }

        return t;

    }

    public static <T> T generaPeticionPost(String url, Object obj, Class<T> definitionRef) {
        T t = null;
        int contador = 1;
        Client cliente = ClientBuilder.newClient();

        while (contador < 4) {
            try {
                cliente = ClientBuilder.newClient();
                //cliente.property("javax.ws.rs.client.Client", 300000);
                System.out.println("############### url: " + url);

                t = cliente.target(url).request().post(Entity.json(obj), definitionRef);
                contador = 4;
                System.out.println("############### json de respuesta: " + t);
            } catch (Exception e) {
                System.out.println("############### intento de envio: " + contador);

                System.out.println("############### url: " + url);
                logger.error(e.getMessage(), e);
                contador++;
            } finally {
                if (cliente != null) {
                    cliente.close();
                    cliente = null;
                }
            }
        }
        return t;

    }

    public static void generaPeticionPostAsync(String url, Object obj) {
        int contador = 1;
        Client cliente = ClientBuilder.newClient();

        while (contador < 4) {
            try {

                cliente = ClientBuilder.newClient();
                //cliente.property("javax.ws.rs.client.Client", 300000);
                System.out.println("############### url: " + url);

                cliente.target(url).request().post(Entity.json(obj), obj.getClass());
                contador = 4;
                // System.out.println("############### json de respuesta: " + t);

            } catch (Exception e) {

                System.out.println("############### intento de envio: " + contador);
                System.out.println("############### url de envio: " + url);
                Client clienteTemp = ClientBuilder.newClient();
                clienteTemp.property("javax.ws.rs.client.Client", 300000);
                clienteTemp.target(url).request().post(Entity.json(obj));
                clienteTemp.close();
                contador = 4;
            }
        }
    }

    public static <T> T generaPeticionPut(String url, Object obj, Class<T> definitionRef) {
        T t = null;
        int contador = 1;
        Client cliente = ClientBuilder.newClient();

        while (contador < 4) {
            try {

                cliente = ClientBuilder.newClient();
                //cliente.property("javax.ws.rs.client.Client", 300000);
                System.out.println("############### url: " + url);

                t = cliente.target(url).request().put(Entity.json(obj), definitionRef);
                contador = 4;
                System.out.println("############### json de respuesta: " + t);
            } catch (Exception e) {
                System.out.println("############### intento de envio: " + contador);
                System.out.println("############### url: " + url);
                logger.error(e.getMessage(), e);
                contador++;

            } finally {
                if (cliente != null) {
                    cliente.close();
                    cliente = null;
                }
            }
        }

        return t;

    }

    public static <T> T generaPeticionDelete(String url, Object obj, Class<T> definitionRef) {
        T t = null;
        int contador = 1;
        Client cliente = ClientBuilder.newClient();

        while (contador < 4) {
            try {
                cliente = ClientBuilder.newClient();
                //cliente.property("javax.ws.rs.client.Client", 300000);

                t = cliente.target(url).request().put(Entity.json(obj), definitionRef);
                contador = 4;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                contador++;

            } finally {
                if (cliente != null) {
                    cliente.close();
                    cliente = null;
                }
            }
        }
        return t;
    }

    public static boolean generaPeticionDelete(String url) {
        boolean t = false;
        int contador = 1;
        Client cliente = ClientBuilder.newClient();
        while (contador < 4) {
            try {
                cliente = ClientBuilder.newClient();
                //cliente.property("javax.ws.rs.client.Client", 300000);
                t = cliente.target(url).request().delete(boolean.class);
                contador = 4;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                contador++;

            } finally {
                if (cliente != null) {
                    cliente.close();
                    cliente = null;
                }
            }
        }
        return t;
    }



    




}
