package com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Extrae una nueva clase llamada ProcesadorDeCsv.
//¿Qué principio SOLID estás favoreciendo?
//Estamos favoreciendo el principio "Single Responsibility Principle" (SRP) de SOLID.
public class ProcesadorDeCsv {

    //Define en la nueva clase un método que reciba un nombre de archivo y devuelva una lista de pedidos.

    public static List<Pedido> procesarArchivoCsv(String nombreArchivo) throws URISyntaxException, IOException{

        List<Pedido> pedidos = new ArrayList<>();

        try{

            URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
            Path caminoDelArchivo = Path.of(recursoCSV.toURI());

            Scanner lectorDeLineas = new Scanner(caminoDelArchivo);
            lectorDeLineas.nextLine();

            int cantidadDeRegistros =0;

            while(lectorDeLineas.hasNext()){

                String linea = lectorDeLineas.nextLine();
                String[] registro = linea.split(",");

                String categoria = registro[0];
                String producto = registro[1];
                BigDecimal precio = new BigDecimal(registro[2]);
                int cantidad = Integer.parseInt(registro[3]);
                LocalDate fecha = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String cliente = registro[5];

                //inicializamos objeto pedido para agregarlo a la lista

                Pedido pedido = new Pedido(categoria, producto, cliente, precio, cantidad, fecha);
                pedidos.add(pedido);

                cantidadDeRegistros++;



            }

        }catch (URISyntaxException e){
                throw new RuntimeException("Archivo pedidos.csv no localizado!");

        }catch (IOException e){
                throw new RemoteException("Error al abrir Scanner para procesar archivo!");
        }

        return pedidos;
    }
}
