# 🚀 Comparativa de Performance: Spring MVC vs WebFlux

Este proyecto evalúa el rendimiento de tres enfoques para construir APIs REST con Spring:

- ✅ Spring MVC (imperativo no esta en este repo)
- ⚛️ Spring WebFlux con anotaciones (reactivo)
- 🧪 Spring WebFlux funcional (reactivo + programación funcional)

El objetivo es entender en qué escenarios WebFlux realmente ofrece ventajas en rendimiento, y cuándo puede ser contraproducente.

---

## 📊 Resultados

Se midió el **throughput** (peticiones por segundo) bajo carga usando [JMeter](https://jmeter.apache.org/). El escenario incluye acceso a una entidad `Movie` y su relación con `Reviews`.

| Enfoque                | Throughput aproximado |
|------------------------|------------------------|
| Spring MVC             | ~30 req/s              |
| WebFlux (anotaciones)  | ~30 req/s              |
| WebFlux (funcional)    | **~110 req/s**         |

> 🔥 Al evitar `.flatMap()` y trabajar con flujos no bloqueantes correctamente, WebFlux funcional mostró un gran salto de rendimiento.

---

## 🧪 Herramientas

- 🧰 **Spring Boot** (MVC y WebFlux)
- 📦 **Reactor Core**
- ⚙️ **JMeter** para pruebas de carga
- 🗃️ **H2** / **MongoDB** como persistencia (según el perfil)
- 📈 Resultados visualizados en gráficos (ver carpeta `/results`)

---

## 🧠 Lecciones Aprendidas

- WebFlux **no es automáticamente más rápido** que MVC.
- Si introduces operaciones bloqueantes o usas mal el operador `.flatMap()`, **puede rendir peor** que el enfoque imperativo.
- WebFlux **brilla con alta concurrencia y no bloqueantes reales**.
- El enfoque funcional con rutas y handlers puede ofrecer un rendimiento superior cuando se optimiza correctamente.

---
