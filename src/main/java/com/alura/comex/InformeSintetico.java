package com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class InformeSintetico {

    /*
    En la clase Main, se genera un informe llamado Informe Sintético, que muestra un resumen de los pedidos realizados a través de indicadores.
    Simplifica el código de la clase Main que genera el informe sintético. Para ello:
    Extrae una nueva clase llamada InformeSintetico.
    Define atributos para cada indicador y realiza los cálculos en el constructor de la nueva clase (no te preocupes por el rendimiento ahora).
    Define getters para los atributos y úsalos en la Main para imprimir el informe.
     */

    private int totalDeProductosVendidos;
    private int totalDePedidosRealizados;
    private BigDecimal monteDeVentas;
    private Pedido pedidoMasBarato;
    private Pedido pedidoMasCaro;

    private int totalDeCategorias;

    public int getTotalDeProductosVendidos() {
        return totalDeProductosVendidos;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public BigDecimal getMonteDeVentas() {
        return monteDeVentas;
    }

    public Pedido getPedidoMasBarato() {
        return pedidoMasBarato;
    }

    public Pedido getPedidoMasCaro() {
        return pedidoMasCaro;
    }

    public int getTotalDeCategorias() {
        return totalDeCategorias;
    }

    //Definicion del constructor de la clase
    public InformeSintetico(List<Pedido> pedidos){

        this.totalDeProductosVendidos = 0;
        this.totalDePedidosRealizados = 0;
        this.monteDeVentas =BigDecimal.ZERO;
        this.pedidoMasBarato = null;
        this.pedidoMasCaro = null;
        this.totalDeCategorias = 0;


        //Refactorización del total de pedidos realizados en el informe sintétic

        this.totalDePedidosRealizados = pedidos.size();

        //Refactorización del total de productos vendidos en el informe sintético
        this.totalDeProductosVendidos = pedidos.stream().mapToInt(Pedido::getCantidad).sum();

        //Refactorización del monto de ventas en el informe sintético

        this.monteDeVentas = pedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        //Refactorización de categorías procesadas en el informe sintético

        this.totalDeCategorias = (int)pedidos.stream().map(Pedido::getCategoria).distinct().count();

        //Refactorización del pedido más barato y más caro en el informe sintético

        this.pedidoMasBarato = pedidos.stream().min(Comparator.comparing(Pedido::getValorTotal)).orElse(null);

        this.pedidoMasCaro = pedidos.stream().max(Comparator.comparing(Pedido::getValorTotal)).orElse(null);


    }




}
