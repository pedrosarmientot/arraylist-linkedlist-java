# ArrayList y LinkedList

Mi propia implementación de ArrayList y LinkedList para
la asignatura de Estructura de Datos. Las dos usan la misma interfaz, y
para probarlas resolví el problema de Josefo con ambas.

## Archivos

- `IList.java` — la interfaz que implementan las dos listas
- `ArrayList.java` — el Arraylist
- `Node.java` — el nodo de la LinkedList
- `LinkedList.java` — la LinkedList 
- `Main.java` — las pruebas y el problema de Josefo

## Cómo funciona cada una

La interfaz `IList<E>` define el contrato de ambas estructuras: `add(e)`, `add(e, index)`,
`remove(index)`, `get(index)`, `size()`, `clear()` e `isEmpty()`.

**ArrayList** guarda todo en un `Object[]` que empieza con capacidad 10.
Cuando se llena, `expand()` crea uno 10 posiciones más grande y copia lo
que había con `System.arraycopy`. Como es un arreglo, añadir o quitar algo
del medio obliga a desplazar todos los elementos que vienen después.

**LinkedList** va encadenando nodos a partir de `first`, donde cada nodo
guarda su dato y apunta al siguiente. Aquí no hay que desplazar nada, pero
para llegar a una posición hay que recorrer cada nodo desde el principio.

En las dos, `add(e, index)` acepta índices del 0 hasta `size`, así que se
puede insertar al inicio, en el medio o al final. Cualquier otro valor
lanza excepción.

## El problema de Josefo

Hay `n` personas en círculo numeradas del 1 al n. Se cuenta desde la
primera y cada `k`-ésima persona sale del círculo; se sigue contando desde
la siguiente hasta que queda una sola, que es la sobreviviente.

Lo resuelve `solveJosephus(IList<Integer> list, int n, int k)` usando nada
más los métodos de la interfaz: `add` para armar el círculo, `remove` para
ir sacando gente y `size` para saber cuándo parar. Como el parámetro es
`IList<Integer>` y no una clase concreta, el mismo método corre igual con
ArrayList que con LinkedList, y el sobreviviente sale idéntico en las dos.

## Las pruebas

`testStructure` prueba los métodos de la lista y `testJosephus` prueba el
algoritmo; los dos se ejecutan una vez con cada estructura.

Del lado positivo: insertar al final, por índice y en la última posición;
eliminar del medio revisando qué devuelve y cómo queda la lista; `size`,
`clear` e `isEmpty`; que el arreglo se expanda al pasar de 10 elementos; y
Josefo con N=7 K=3, N=1 K=3 y N=6 K=100.

Del lado de los errores: `get(-1)`, `get(size)`, `remove(100)`,
`add(valor, -5)` y `add(valor, size+1)` tienen que lanzar excepción en vez
de dejar pasar el fallo en silencio. En Josefo, N=0 y K=0 se rechazan con
`IllegalArgumentException`.

## Para correrlo

```bash
javac *.java
java Main
```
