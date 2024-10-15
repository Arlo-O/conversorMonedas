package com.arlo.conversormonedas;

import java.util.Map;

public class Moneda {
    private final String moneda;
    private final Map<String, Double> tasas;

    public Moneda(String moneda, Map<String, Double> tasas) {
        this.tasas = tasas;
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public double conversion(Moneda destino, Double valor) {
        if(!tasas.containsKey(destino.getMoneda())) {
            System.out.println("No se pudo encontrar la Moneda de destino de la conversión.");
        }
        double tasaConversion = tasas.get(destino.getMoneda());
        return valor * tasaConversion;
    }
}
