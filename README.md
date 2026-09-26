# TP2 - Programación de Paradigmas

**Alumno:** Valentino Ramos Lapetta
**Legajo:** 53776
**Materia:** Paradigmas de Programación - UTN FRM

## Descripción general

Este proyecto extiende el sistema de gestión de eventos universitarios
desarrollado en el TP1, incorporando manejo de excepciones, persistencia
de objetos, interfaces, genéricos, clases anidadas e hilos.


## Ejercicios resueltos

### Ejercicio 1 - Excepciones y persistencia
- Se creó la excepción personalizada `CupoExcedidoException` (checked,
  extiende de `Exception`).
- El método `inscribir()` de `Actividad` lanza esta excepción cuando se
  supera el cupo máximo de la actividad.
- Se implementó persistencia de objetos mediante serialización:
  `persistirEvento()` guarda un `EventoUniversitario` en un archivo `.dat`,
  y `recuperarEvento(String id)` lo recupera desde ese archivo.
- Todas las clases del modelo implementan `Serializable`.

### Ejercicio 2 - Interfaces
- Se creó la interfaz `Certificable`, con el método `generarCertificado()`
  y la constante `ENTIDAD_EMISORA`.
- `Taller` y `Curso` implementan `Certificable` (son actividades que
  otorgan certificado); `Charla` no la implementa (no es certificable).
- Se agregó la nueva actividad `Curso`, con atributo `nivel` que impacta
  en el costo de materiales.

### Ejercicio 3 - Genéricos
- `filtrarActividadesPorTipo(Class<T> tipo)`: método genérico con límite
  (`<T extends Actividad>`) que filtra las actividades de un evento según
  su tipo concreto (Charla, Taller o Curso), usando `Class<T>` para
  identificar y castear en tiempo de ejecución.
- `calcularCostoMateriales(List<? extends Actividad> actividades)`: método
  con wildcard que suma el costo de materiales de una lista de actividades
  de cualquier subtipo de `Actividad`.

### Ejercicio 4 - Clases anidadas e hilos
- `Inscripcion.TicketDeAcceso`: clase anidada de instancia (no estática)
  dentro de `Inscripcion`, que representa el ticket de acceso emitido una
  vez confirmada la inscripción. Solo se puede emitir un ticket si la
  inscripción está en estado `"confirmada"`.
- `EnvioTicketsThread`: clase que extiende `Thread`, recorre todas las
  actividades e inscripciones confirmadas de un evento y envía sus
  tickets de forma concurrente (en paralelo con el hilo principal).
