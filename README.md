# Conversor de Monedas con consumo de un API.

## Sobre el proyecto.

Este es un proyecto desarrollado con JAVA como lenguaje prinicpal y con el uso de la biblioteca Gson, consta de 2 clases en una se representa la Moneda,
para guardar su nombre y sus tasas de conversion a las monedas que se han filtrado (ARS - Peso argentino, BOB - Boliviano boliviano, BRL - Real brasileño, 
CLP - Peso chileno, COP - Peso colombiano, USD - Dólar estadounidense) y con el metodo que se encarga de calcular la conversión de dicha moneda en una moneda 
destino elegida.

En la clase principal se desarrolla la conexión a la API y la gestión del Json que es retornado como respuesta, además de que se instancian las monedas y se
muestra un menú legible al usuario, y finalmente se implementan ciertas salidas por terminal para diferentes errores que pueden presentarse.

## Funcionalidades.
- Conserión: Al usuario se le solicitarán 3 inputs de manera consecutiva, primeramente se le pedira que eliga una moneda origen, que será
            la moneda a la que se le hará la conversión, luego se le pedira que digite la opción para la moneda de destino para la conversión
             y finalmente se le pedirá que digita el valor de la moneda de origen para realizar la conversión.
## Manual de usuario básico.
1. Ejecute el programa.
2. Se mostrará un listado con las monedas disponibles, digite el número indicado según su elección para la moneda de origen.
3. Se mostrará ahora el mismo listado de monedas, ahora digite el número indicado según su elección para la moneda de destino, es
   decir la moneda la cual quiere conocer el valor de su monto de la moneda de origen.
4. Digite el valor de la moneda de origen para que el programa haga la conversión.
5. Se mostrará la conversión que realizo el programa, se mostrará nuevamente el menú inicial, si desea realizar una nueva conversión vaya al paso 2, sino vaya al 6.
6. Digite 0 para que el sistema termine la ejecución.
   
