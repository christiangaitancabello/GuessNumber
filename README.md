# GuessNumber

## Para que sirve
Este proyecto es un juego en el que el usuario es desafíado a adivinar un número aleatorio entre 1 y 100.

## Estructura del proyecto
El juego cuenta con tres Activities:
+ ConfigActivity: Se le pide al jugador un nombre de usuario y el número de intentos máximos con los que contará para adivinar el número (Los datos son guardados en un POJO 'User' que se comparte con la siguiente Activity).
+ PlayActivity: En esta Activity es donde transcurre el juego. El usuario trata de averiguar el número mientras recibe pistas de si el número es menor o mayor. Esta clase cuenta con otro POJO 'Game' donde se guarda:
  + Número a adivinar.
  + Número de intentos que lleva el usuario.
  + Booleano que refleja si el usuario ha salido victorioso del juego o no.
Se pasa a la siguiente Activity cuando el jugador adivine el número o se quede sin intentos.
+ EndPlayActivity: Esta Activity muestra el texto "VICTORIA" y el número de intento consumidos hasta el acierto por el jugador en caso de ganar, y "DERROTA" más la revelación del número que tenía que ser averiguado en caso de que el usuario fuese incapaz de acertarlo.

## Innovación
### Librería Konfetti
En caso de que el usuario consiga la victoria, se activará en la última Activity un añadido en el que, cada vez que la pantalla es pulsada, se lanza una explosión de confeti en un punto aleatorio de la pantalla para que el jugador pueda celebrar su victoria como es debido.

<p align="center">
<img src="https://user-images.githubusercontent.com/113918779/195713156-ea1565a9-6edd-4d77-a2cf-c95d6320b55b.jpg" height="450" width="220" >
</p>

Esto se ha hecho gracias a la librería [Konfetti](http://https://github.com/DanielMartinus/Konfetti "Konfetti") de @DanielMartinus.

### Relative Layout
Con el motivo de implementar la librería Konfetti mencionada anteriormente, hice uso de la vista '**RelativeLayout**', en la que las diferentes vistas dependen de su posición por su margen, no se tienen en cuenta las posiciones de otras vistas.
<br/>
El motivo de su uso fue poder añadir una vista que ocupase toda la pantalla para las explosiones de confeti, mientras que también se pudiese mostrar el texto de victoria correspondiente.

      <RelativeLayout xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context=".iu.EndGameActivity">
        
            ...
            
      </RelativeLayout>
