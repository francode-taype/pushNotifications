# Ejercicio: Sistema de Notificaciones Push con Firebase

## Objetivo:
Crear una API en Spring Boot para enviar notificaciones push a los empleados según su rol cuando el **estado de un pedido** cambia.

## Entidades:

### **Empleado**
La entidad `Empleado` tendrá los siguientes atributos:
- **id**: Identificador único del empleado.
- **nombre**: Nombre del empleado.
- **email**: Correo electrónico del empleado.
- **rol**: Rol del empleado, puede ser uno de los siguientes:
  - **Cajero**
  - **Repartidor**
  - **Gerente**

### **Token de Notificación**
La entidad `TokenNotificacion` almacenará los tokens de los dispositivos de los empleados. Tendrá los siguientes atributos:
- **id**: Identificador único del token.
- **empleado_id**: Relación con el empleado que tiene el token.
- **token_push**: Token de Firebase asociado al dispositivo.
- **dispositivo**: Tipo de dispositivo (Ej. "Android", "iOS").

### **Pedido**
La entidad `Pedido` tendrá los siguientes atributos:
- **id**: Identificador único del pedido.
- **estado**: Estado del pedido. Los estados posibles son:
  - **Nuevo**
  - **En Proceso**
  - **Listo para Entrega**
  - **Entregado**
- **empleado_id_asignado**: Relación con el empleado encargado del pedido.
- **fecha_actualizacion**: Fecha del último cambio de estado del pedido.

## Flujo del Sistema:

1. **Cambio de Estado en Pedido**:  
   Cuando un **pedido** cambie de estado, se enviarán las notificaciones.

2. **Roles y Notificaciones**:  
   Las notificaciones se envían a los empleados según su rol y el estado del pedido:
   - **Cajero**: Recibe notificación cuando el estado es **"Nuevo"** o **"Pagado"**.
   - **Repartidor**: Recibe notificación cuando el estado es **"En Proceso"** o **"Listo para Entrega"**.
   - **Gerente**: Recibe notificación cuando el estado es **"Entregado"**.

3. **Envío de Notificaciones**:  
   El backend obtiene los **tokens** de los empleados (almacenados en la tabla `TokenNotificacion`) y envía la notificación a sus dispositivos utilizando Firebase Cloud Messaging (FCM).

4. **Validación de Tokens**:  
   El backend debe validar si el **token** es válido. Si el token no es válido (por ejemplo, si el empleado ha desinstalado la aplicación o el token ha expirado), debe eliminarse de la base de datos.

## Mensaje de Notificación:
- **Cajero**: "Nuevo pedido recibido. El estado del pedido ha cambiado a **Nuevo**."
- **Repartidor**: "El estado del pedido ha cambiado a **En Proceso**. Prepárate para la entrega."
- **Gerente**: "El pedido ha sido **Entregado**."

## Consideraciones:
- **Múltiples Dispositivos**: Un empleado puede tener varios dispositivos, por lo que puede tener varios tokens.
- **Eliminación de Tokens Inválidos**: Si un token es inválido, se elimina de la base de datos y no se intenta enviar la notificación a ese token.
