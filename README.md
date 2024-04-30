"# ATP_LauraGarridoArredondo" 

---

# ATP_LauraGarridoArredondo

## Descripción

"ATP_LauraGarridoArredondo" es una aplicación desarrollada con Nest.js y Spring, utilizando el lenguaje de programación Java. Esta aplicación proporciona una base sólida para construir servicios web y APIs utilizando el enfoque modular y la arquitectura basada en controladores de Nest.js, junto con las capacidades de gestión de dependencias de Spring.

## Características

- **Arquitectura Modular**: La aplicación está estructurada en módulos que permiten una fácil extensibilidad y mantenimiento.
- **API RESTful**: Utiliza controladores Nest.js para exponer una API RESTful que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en los recursos de la aplicación.
- **Gestión de Dependencias**: Se aprovecha de la potente gestión de dependencias de Spring para la inyección de dependencias y la gestión de componentes.
- **Seguridad**: Implementa medidas de seguridad básicas, como autenticación y autorización, utilizando las funcionalidades proporcionadas por Spring Security.
- **Base de Datos Relacional**: Utiliza una base de datos relacional (H2) para almacenar y recuperar datos.

## Requisitos Previos

- JDK (Java Development Kit) 11 o superior.
- Node.js y npm (Node Package Manager).
- Gestor de base de datos relacional compatible (por ejemplo, MySQL, PostgreSQL, H2 o JDBC).

## Instalación

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/LauraGarridoArredondo/ATP_LauraGarridoArredondo
   ```

2. Accede al directorio de la aplicación:

   ```bash
   cd nest-basica
   ```

3. Instala las dependencias del frontend y del backend:

   ```bash
   npm install
   ```

4. Configura la base de datos editando el archivo `application.properties` en el directorio `/src/main/resources`. Asegúrate de proporcionar las credenciales correctas y la configuración de la base de datos.

5. Ejecuta la aplicación:

   ```bash
   npm run start:dev
   ```

6. La aplicación estará disponible en [http://localhost:8000](http://localhost:8000).

## Uso

Una vez que la aplicación esté en funcionamiento, puedes comenzar a interactuar con la API a través de las rutas definidas en los controladores Nest.js. Puedes utilizar herramientas como Postman o curl para realizar solicitudes HTTP a la API y probar su funcionamiento.

## Contribución

Si deseas contribuir a este proyecto, ¡te damos la bienvenida! Siéntete libre de enviar Pull Requests con mejoras, correcciones de errores o nuevas características. Sin embargo, antes de enviar tu PR, asegúrate de seguir las pautas de contribución.

## Autor

Nest Basica es mantenido por <B>Laura Garrido Arredondo</B>

## Licencia

Este proyecto está bajo la licencia CREATIVE COMMMONS (CC)
