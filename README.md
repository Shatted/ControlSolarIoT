# Control Solar IoT — Guía de pruebas y entrega (U2)

App Android (Kotlin) que simula el control y monitoreo de un arco solar IoT.
6 pantallas: **Inicio → Login → Descripción → Sensores → Control** (+ Historial).

---

## 1. Crear el proyecto en Android Studio (una sola vez)

1. En la pantalla de bienvenida: **New Project**.
2. Elige la plantilla **"Empty Views Activity"** (¡NO "Empty Activity", esa genera Compose!).
3. Configura:
   - **Name**: `Control Solar IoT`
   - **Package name**: `com.example.controlsolariot` (si usas otro, ajusta la línea `package` en los 6 archivos .kt)
   - **Minimum SDK**: API 24 o superior
4. Finish y espera a que sincronice Gradle (primera vez tarda varios minutos).

## 2. Copiar los archivos entregados

| Archivo entregado | Lo pegas en (dentro del proyecto) | Acción |
|---|---|---|
| `MainActivity.kt` | `app/src/main/java/com/example/controlsolariot/` | **Reemplazar** contenido |
| `LoginActivity.kt` | `app/src/main/java/com/example/controlsolariot/` | Archivo nuevo |
| `DescripcionActivity.kt` | `app/src/main/java/com/example/controlsolariot/` | Archivo nuevo |
| `SensoresActivity.kt` | `app/src/main/java/com/example/controlsolariot/` | Archivo nuevo |
| `ControlActivity.kt` | `app/src/main/java/com/example/controlsolariot/` | Archivo nuevo |
| `DetalleActivity.kt` | `app/src/main/java/com/example/controlsolariot/` | Archivo nuevo |
| `activity_main.xml` | `app/src/main/res/layout/` | **Reemplazar** contenido |
| `activity_login.xml` | `app/src/main/res/layout/` | Archivo nuevo |
| `activity_descripcion.xml` | `app/src/main/res/layout/` | Archivo nuevo |
| `activity_sensores.xml` | `app/src/main/res/layout/` | Archivo nuevo |
| `activity_control.xml` | `app/src/main/res/layout/` | Archivo nuevo |
| `activity_detalle.xml` | `app/src/main/res/layout/` | Archivo nuevo |

Truco: clic derecho sobre el paquete `com.example.controlsolariot` → **New → Kotlin Class** y pega el contenido. Igual con **res/layout → New → Layout Resource File**.

3. En `AndroidManifest.xml` **NO borres nada**: solo agrega los 5 bloques `<activity>` (Login, Descripcion, Sensores, Control, Detalle) dentro de `<application>`, como indica el archivo entregado.

4. **Personaliza** en `activity_main.xml` el nombre del integrante (busca "Integrantes").

## 3. Ejecutarla

**Opción A — Emulador:**
1. Botón **More Actions → Device Manager** (o el ícono de teléfono arriba a la derecha).
2. **Create Virtual Device** → Pixel 7 → imagen **API 34+** (descárgala si pide).
3. Pulsa el triángulo verde **Run ▶** y espera a que compile e instale.

**Opción B — Celular físico:**
1. Ajustes → Acerca del teléfono → toca 7 veces "Número de compilación" → vuelve → Opciones de desarrollador → activa **Depuración USB**.
2. Conecta el cable, acepta el diálogo "¿Permitir depuración USB?".
3. Elige tu dispositivo en la barra superior y pulsa **Run ▶**.

## 4. Checklist de pruebas (verifica cada punto)

| # | Prueba | Resultado esperado |
|---|---|---|
| 1 | Abrir la app | Pantalla Inicio: título del proyecto + integrantes |
| 2 | Botón **INICIAR** | Abre el login |
| 3 | Login vacío → INGRESAR | Mensaje rojo "Complete usuario y contraseña" |
| 4 | Login `admin` / clave incorrecta | "Usuario o contraseña incorrectos" y limpia la clave |
| 5 | Login `admin` / `1234` | Toast "Bienvenido, admin" → abre Descripción |
| 6 | Botón atrás tras iniciar sesión | NO vuelve al login (está cerrado con `finish()`) |
| 7 | Pantalla Descripción | Texto del proyecto + sensores/actuadores |
| 8 | Botón **SIGUIENTE** | Grilla de sensores: 36°C, 60%, 36 cm, CLARA |
| 9 | Tocar la tarjeta de iluminación | Alterna CLARA ↔ OSCURA con Toast |
| 10 | Botón **HISTORIAL** | Lista de lecturas; **VOLVER** regresa |
| 11 | Botón **CONTROL** | Panel de control |
| 12 | Botón **OFF** | Cambia a ON verde + Toast "Sistema ENCENDIDO" |
| 13 | **SUBIR** / **BAJAR** | Toast "Moviendo el arco solar hacia ARRIBA/ABAJO" |
| 14 | **ACTIVAR ALARMA** | Diálogo con Aceptar/Cancelar; Aceptar → Toast alarma activada |

**Credenciales: usuario `admin` — contraseña `1234`**

## 5. Solución de problemas

- **"Unresolved reference: LoginActivity"** → falta registrar la clase o el nombre no coincide; revisa que el archivo se llame igual que la clase.
- **La app crashea al abrir** → revisa Logcat (filtro: *Error*): casi siempre es un `findViewById` con ID que no existe en el layout.
- **`activity_login not found`** → el XML quedó en otra carpeta; debe estar en `res/layout/`.
- **Colores raros/tema distinto** → verifica que no borraste el `<application>` original del manifest.
