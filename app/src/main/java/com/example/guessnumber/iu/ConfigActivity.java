package com.example.guessnumber.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.guessnumber.data.Game;
import com.example.guessnumber.data.User;
import com.example.guessnumber.R;
import com.example.guessnumber.databinding.ActivityConfigBinding;

/**
 * <h1>Proyecto GuessNumber</h1>
 * Este proyecto es un juego donde el usuario deberá de adivinar un número aleatorio entre el 1 y el 100 en un número
 * de intentos establecido por el mismo.
 *
 * @author Christian Gaitán
 * @version 1.0
 */
public class ConfigActivity extends AppCompatActivity {
    // Variable de tipo ActivityConfigBinding donde se recogen los elementos del XML activity_config.
    private ActivityConfigBinding binding;

    /**
     * Método que se ejecuta al lanzar la Activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btStartGame.setOnClickListener(view -> StartGame());
        binding.setUser(new User());
    }

    /**
     * Método que se llama cuando se pulsa sobre el botón btStartGame definido en el XML.
     */
    private void StartGame() {
        if (binding.getUser().getName() == null || binding.getUser().getName().isEmpty() || binding.getUser().getNtries() == null  || binding.getUser().getNtries().isEmpty() || Integer.parseInt(binding.getUser().getNtries()) < 0) {
            Toast.makeText(this, "Introduzca nombre de usuario y número de intentos válido", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("user", binding.getUser());

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}