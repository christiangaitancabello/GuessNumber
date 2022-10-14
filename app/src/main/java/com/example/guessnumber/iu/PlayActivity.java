package com.example.guessnumber.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.guessnumber.data.Game;
import com.example.guessnumber.databinding.ActivityPlayBinding;

import java.util.Random;

/**
 * Clase donde se gestiona el juego.
 */
public class PlayActivity extends AppCompatActivity {
    // Variable de tipo ActivityPlayBinding donde se recogen los elementos del XML activity_play.
    private ActivityPlayBinding binding;

    /**
     * Método que se ejecuta al lanzar la aplicación.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random random = new Random();
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();

        binding.setUser(bundle.getParcelable("user"));
        binding.setGame(new Game());
        binding.btTryAgain.setOnClickListener(view -> TryAgain());
        binding.btTryAgain.setEnabled(false);
        binding.btTry.setOnClickListener(view -> GuessTry());
        binding.getGame().setnTries(0);
        binding.getGame().setnToGuess(random.nextInt(100) + 1);
        UpdateInfoTries();
    }

    /**
     * Este método es el que se llama cuando se pulsa sobre el botón btTryAgain definido en el XML.
     */
    private void TryAgain() {
        binding.tvInfoMoreLess.setText("");
        binding.etGuessNumber.setEnabled(true);
        binding.etGuessNumber.setText("");
        binding.btTry.setEnabled(true);
        binding.btTryAgain.setEnabled(false);
    }

    /**
     * Método que informa al jugador de cuantos intentos lleva y el número de intentos que tiene en total.
     */
    private void UpdateInfoTries() {
        binding.tvInfoNTries.setText("Nº Intentos: " + binding.getGame().getnTries() + "/" + binding.getUser().getNtries());
    }

    /**
     * Este método es el que se llama cuando se pulsa sobre el botón btTry definido en el XML.
     */
    private void GuessTry() {
        if (binding.etGuessNumber.getText().toString().equals("")) {
            Toast.makeText(this, "Se debe introducir un número", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            binding.getGame().setnTries(binding.getGame().getnTries() + 1);
            if (Integer.parseInt(binding.etGuessNumber.getText().toString()) > binding.getGame().getnToGuess()) {
                binding.tvInfoMoreLess.setText("El número es menor");
            } else if (Integer.parseInt(binding.etGuessNumber.getText().toString()) < binding.getGame().getnToGuess()) {
                binding.tvInfoMoreLess.setText("El número es mayor");
            } else {
                binding.getGame().setWin(true);
                EndGame();
            }
        } catch (Exception e) {
            binding.getGame().setnTries(binding.getGame().getnTries() - 1);
        }


        UpdateInfoTries();
        binding.etGuessNumber.setEnabled(false);
        binding.btTry.setEnabled(false);
        binding.btTryAgain.setEnabled(true);

        if (binding.getGame().getnTries() == Integer.parseInt(binding.getUser().getNtries())) {
            EndGame();
        }
    }

    /**
     * Método que lanza la Activity EndGame pasando información de si el usuario ha ganado o perdido.
     */
    private void EndGame() {
        Bundle bundle = new Bundle();

        bundle.putParcelable("game", binding.getGame());

        // Para volver a la primera Activity
        //.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        Intent intent = new Intent(this, EndGameActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}