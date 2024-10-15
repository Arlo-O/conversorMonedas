package com.arlo.conversormonedas;

import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ConversorMonedas {

    private static final List<String> MONEDAS_INTERES = Arrays.asList(
            "USD", "COP", "BRL", "ARS", "CLP", "BOB");

    public static void main(String[] args) {

        Scanner lecturaTeclado = new Scanner(System.in).useLocale(Locale.US);  // Forzamos el uso de punto como decimal
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String direccionAPI = "https://v6.exchangerate-api.com/v6/710018af9c6c277811ce9fe6/latest/";

        String menu = """
                ******************************************
                1. Dolar Estadounidense
                2. Peso Colombiano
                3. Real Brasileño
                4. Peso Argentino
                5. Peso Chileno
                6. Boliviano Boliviano
                0. Salir

                Digite una opcion valida.
                """;

        while (true) {
            try {
                System.out.println("Bienvenid@ al conversor de Monedas");
                System.out.println(menu);
                int opcion = lecturaTeclado.nextInt();
                lecturaTeclado.nextLine(); // Limpiar buffer

                if (opcion == 0) break;

                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
                }

                String monedaSelecc = MONEDAS_INTERES.get(opcion - 1);
                HttpClient cliente = HttpClient.newHttpClient();
                HttpRequest solicitud = HttpRequest.newBuilder()
                        .uri(URI.create(direccionAPI + monedaSelecc))
                        .build();
                HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

                JsonObject respuestaJson = gson.fromJson(respuesta.body(), JsonObject.class);
                JsonObject tasas = respuestaJson.getAsJsonObject("conversion_rates");

                Map<String, Double> tasasFiltradas = new HashMap<>();
                for (String moneda : MONEDAS_INTERES) {
                    if (tasas.has(moneda)) {
                        tasasFiltradas.put(moneda, tasas.get(moneda).getAsDouble());
                    }
                }

                Moneda monedaOrigen = new Moneda(monedaSelecc, tasasFiltradas);

                System.out.println("Seleccione la moneda destino");
                System.out.println(menu);
                int opcionDestino = lecturaTeclado.nextInt();
                lecturaTeclado.nextLine(); // Limpiar buffer

                if (opcionDestino < 1 || opcionDestino > 6) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
                }

                Moneda monedaDestino = new Moneda(MONEDAS_INTERES.get(opcionDestino - 1), new HashMap<>());

                System.out.println("Ingrese la cantidad a convertir: ");
                String entrada = lecturaTeclado.nextLine();  // Leer como cadena
                double cantidad = Double.parseDouble(entrada.replace(",", "."));  // Reemplazar coma por punto

                if (!tasasFiltradas.containsKey(monedaDestino.getMoneda())) {
                    System.out.println("No se pudo encontrar la moneda de destino.");
                    continue;
                }

                double resultado = monedaOrigen.conversion(monedaDestino, cantidad);
                String monedaOrigenNombre = MONEDAS_INTERES.get(opcion - 1);
                String monedaDestinoNombre = MONEDAS_INTERES.get(opcionDestino - 1);

                System.out.printf("%.2f %s equivalen a %.2f %s\n",
                        cantidad, monedaOrigenNombre, resultado, monedaDestinoNombre);

            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                lecturaTeclado.nextLine(); // Limpiar el buffer
            } catch (NumberFormatException e) {
                System.out.println("Formato numérico incorrecto. Use punto o coma según corresponda.");
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al conectar con la API: " + e.getMessage());
            } catch (JsonSyntaxException e) {
                System.out.println("Error al procesar la respuesta JSON: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }
}
