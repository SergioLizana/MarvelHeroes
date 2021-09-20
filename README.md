## CODE  CHALLENGE MARVEL HEROES

![](https://github.com/jparrenobonillo/marvel-app/blob/master/screeshots/marvel_heroes.png)

La aplicación se compone de 4 pantallas:

- Splash

- Lista de personajes

- Detalle de personaje

![](https://github.com/jparrenobonillo/marvel-app/blob/master/screeshots/splash.png)
![](https://github.com/jparrenobonillo/marvel-app/blob/master/screeshots/character_list.png)
![](https://github.com/jparrenobonillo/marvel-app/blob/master/screeshots/favorites_list.png)
![](https://github.com/jparrenobonillo/marvel-app/blob/master/screeshots/character_detail.png)

## Datos relevantes

* **MVVM, Single Activity, Android Architecture Components**: Activity/Fragment -> ViewModel -> Repository.

* **Patrón Repository con clean architecture** para acceder a la API de Marvel.

![](https://github.com/jparrenobonillo/marvel-app/blob/master/screeshots/repository_architecture.png)

* **Navigation + safe-args** para enviar parámetros entre fragments.

* **Hilt** para la inyección de dependencias.

* **ROOM** para gestión de favoritos en una base de datos local.
   
* **Retofit + Gson** para hacer llamadas a la API de Marvel.

* **Sealed Classes**

* **Swagger Codegen** para la generación de clases a partir de la API de Marvel.

* **Unit test y UI test con Espresso**