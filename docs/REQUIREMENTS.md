# Requirements — Gestión de Activos Fijos (EAM / ITAM)

**Dominio:** Activos Fijos y Serializados (Vehículos, Maquinaria, Hardware IT)

---

## Categoría 1: Identidad y Unicidad de Instancia

**RNI-01: Obligatoriedad de Identificador Único Universal.**
Todo activo registrado debe poseer al menos un identificador único e irrepetible en el sistema (Placa de Activo corporativo / *Asset Tag*). Los activos IT o vehiculares deben exigir adicionalmente su identificador de fabricante (Número de Serie, IMEI o VIN). El sistema rechazará cualquier intento de duplicar estos identificadores, incluso si el activo original ha sido dado de baja o destruido.

**RNI-02: Integridad del Identificador Padre-Hijo.**
Un "Activo Componente" (ej. un motor o una tarjeta gráfica) no puede estar asignado a más de un "Activo Padre" (ej. dos camiones o dos servidores) de forma simultánea.

---

## Categoría 2: Cadena de Custodia (Check-in / Check-out)

**RNI-03: Exclusividad de Custodia (Regla 1:1).**
Un activo no puede estar asignado a más de un custodio (Empleado, Contratista o Departamento) de forma simultánea. Para transferir un activo del Custodio A al Custodio B, el sistema debe forzar un evento de "Devolución" (Check-in) por parte de A, seguido de una "Asignación" (Check-out) hacia B.

**RNI-04: Firma de Responsabilidad (Aceptación).**
El estado de un activo transferido permanecerá en "Asignación Pendiente" hasta que el receptor confirme la recepción (firma digital o aceptación en el sistema). Solo tras esa confirmación la responsabilidad se transfiere oficialmente.

---

## Categoría 3: Estados y Ciclo de Vida

**RNI-05: Prohibición de Eliminación Física (Hard Delete).**
Ningún activo puede ser borrado de la base de datos. Los registros creados por error se marcan como "Anulado". Los activos que terminan su vida útil se marcan como "Dado de Baja" (Retired/Disposed). El historial de cada activo es inmutable para fines de auditoría legal y contable.

**RNI-06: Restricción de Disposición de Activos en Uso.**
Un activo no puede cambiar su estado a "Vendido", "Donado", "Robado" o "Destruido" si su estado operativo actual es "En Uso" o "Asignado". Debe ser devuelto formalmente al inventario central antes de ejecutar la baja.

---

## Categoría 4: Mantenimiento e Intervenciones

**RNI-07: Bloqueo de Operación por Falla Crítica o Mantenimiento.**
Si un activo entra en estado "Mantenimiento Correctivo", "Falla Crítica" o "Inspección Expirada", el sistema bloqueará automáticamente cualquier intento de asignación o reserva del mismo.

**RNI-08: Trazabilidad de Intervenciones.**
Todo evento de mantenimiento (preventivo o correctivo) debe quedar registrado en la bitácora del activo con fecha, técnico responsable y costo de la intervención. Estos registros son de solo lectura y no pueden modificarse de forma retroactiva.

---

## Categoría 5: Valoración y Depreciación (Vínculo Contable)

**RNI-09: Suelo del Valor Residual.**
El motor de cálculo de depreciación no permitirá que el "Valor Contable" (Net Book Value) de un activo sea inferior a su "Valor de Rescate" o "Valor Residual" configurado. Este valor puede ser cero, pero nunca negativo.

**RNI-10: Capitalización de Mejoras.**
Si a un activo se le añade un componente que aumenta su vida útil o valor (ej. cambio de batería principal en un vehículo eléctrico), el sistema debe permitir sumar ese costo al valor del activo padre y recalcular la depreciación restante sin alterar los registros históricos anteriores.