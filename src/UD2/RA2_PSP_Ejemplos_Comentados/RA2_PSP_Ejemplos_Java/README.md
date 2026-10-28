# RA2 PSP – Programación multihilo: ejemplos Java

Basado en la presentación adjunta «UD2 PSP P1 (3)» (40 diapositivas).
Cada carpeta es un ejemplo independiente, con uno o varios ficheros .java y un LEEME.txt.

## Uso
- Requisitos: JDK 17 o superior (se ha comprobado con JDK 21).
- Abre cada carpeta en Visual Studio Code o navega a ella desde una terminal.
- Compila con `javac *.java` y ejecuta `java NombreClasePrincipal` (se indica en cada LEEME).
- No hay paquetes ni dependencias externas; los ejemplos pueden compilarse por separado.
- Algunos resultados cambian entre ejecuciones por el planificador de hilos.
- El ejemplo deadlock es intencional: los hilos daemon quedan bloqueados, pero la JVM termina al cerrar main.

## Índice de ejemplos
- **ejemplo01_hiloPrincipal** · diap. 2-5 · `java EjemploHiloPrincipal` · Identificar el hilo main, su nombre, ID, prioridad, estado y si sigue activo.
- **ejemplo02_herenciaThread** · diap. 6-7 · `java EjemploHerenciaThread` · Crear una clase que extienda Thread y sobrescriba run().
- **ejemplo03_interfazRunnable** · diap. 8-10 · `java EjemploRunnable` · Separar la definición de la tarea (Runnable) del hilo (Thread).
- **ejemplo04_multiplesHilos** · diap. 7, 11 · `java EjemploMultiplesHilos` · Lanzar tres tareas concurrentes y observar su intercalado.
- **ejemplo05_startVsRun** · diap. 12 · `java EjemploStartVsRun` · Demostrar que run() es una llamada ordinaria y start() inicia otro hilo.
- **ejemplo06_sleepDescargas** · diap. 13-14 · `java EjemploSleep` · Pausar únicamente el hilo que llama a Thread.sleep().
- **ejemplo07_estadosDeHilo** · diap. 15 · `java EjemploEstados` · Mostrar de forma controlada NEW, TIMED_WAITING, WAITING, BLOCKED, TERMINATED.
- **ejemplo08_joinEsperar** · diap. 16 · `java EjemploJoin` · Usar join() para que main espere realmente al trabajador.
- **ejemplo09_interrupcionCooperativa** · diap. 17-19, 27 · `java EjemploInterrupcion` · Solicitar la interrupción, capturar InterruptedException y restaurar bandera.
- **ejemplo10_nombresHilos** · diap. 20 · `java EjemploNombres` · Utilizar constructores y getName/setName para identificar tareas.
- **ejemplo11_prioridades** · diap. 21-22 · `java EjemploPrioridades` · Consultar y asignar prioridades de hilos sin asumir orden de ejecución.
- **ejemplo12_hiloDaemon** · diap. 23 · `java EjemploDaemon` · Mostrar por qué un hilo daemon no mantiene por sí solo viva la JVM.
- **ejemplo13_lambdasRunnable** · diap. 24-25 · `java EjemploLambdas` · Comparar una clase anónima, una lambda y una clase propia Runnable.
- **ejemplo14_cicloVidaUnaVez** · diap. 15, 26 · `java EjemploCicloVida` · Demostrar que un Thread solo permite ejecutar start() una vez.
- **ejemplo15_condicionCarrera** · diap. 28-30 · `java EjemploCondicionCarrera` · Reproducir el problema de contador++ compartido sin sincronización.
- **ejemplo16_atomicInteger** · diap. 30-31, 38 · `java EjemploAtomicInteger` · Corregir el contador con AtomicInteger e incrementar de forma atómica.
- **ejemplo17_synchronizedContador** · diap. 32, 35, 38 · `java EjemploSynchronized` · Resolver condición de carrera protegiendo método con synchronized.
- **ejemplo18_deadlockDemostracion** · diap. 33 · `java EjemploDeadlock` · Provocar interbloqueo de dos monitores y diagnosticarlo sin dejar proceso colgado.
- **ejemplo19_evitarDeadlock** · diap. 33, 35 · `java EjemploEvitarDeadlock` · Prevenir deadlock obteniendo los recursos siempre en el mismo orden.
- **ejemplo20_volatileVisibilidad** · diap. 38 · `java EjemploVolatile` · Comparar visibilidad de volatile con atomicidad de los contadores.

## Orientaciones de aula
1. Empezar por el hilo principal, extends Thread, Runnable y start/run.
2. Ejecutar varias veces los ejemplos concurrentes y comparar el orden de consola.
3. Contrastar secuencialmente condición de carrera, AtomicInteger y synchronized.
4. Utilizar el deadlock controlado y después su prevención.
5. Usar volatile solo para visibilidad, no como solución a contador++.

## Notas importantes
- La diapositiva 23 muestra una captura con indicaciones del IDE; el código correcto utiliza `setDaemon(true)`.
- En la presentación no hay una práctica específica completa de todos los métodos de resumen; se incluyen ejemplos aclaratorios de `getState()`, `isAlive()`, `volatile` y `isDaemon()` para que el material sea ejecutable.
- `Thread.getId()` se conserva para compatibilidad con Java 17; en versiones modernas existe `threadId()`.
- En la demostración de condiciones de carrera, el resultado incorrecto es posible pero no obligatorio.
