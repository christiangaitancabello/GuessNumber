package com.example.guessnumber.iu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;

import com.example.guessnumber.R;
import com.example.guessnumber.databinding.ActivityEndGameBinding;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;

/**
 * Clase donde se muestra el resultado del juego.
 */
public class EndGameActivity extends AppCompatActivity {
    // Variable de tipo ActivityEndGameBinding donde se recogen los elementos del XML activity_end_game.
    private ActivityEndGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        binding.btnPlayAgain.setOnClickListener(view -> PlayAgain());

        binding.setGame(bundle.getParcelable("game"));
        ShowResult();
    }

    /**
     * Método para mostrar el resultado del juego.
     */
    private void ShowResult() {
        if (binding.getGame().getWin()) {
            binding.konfettiView.setOnClickListener(view -> explode());
            binding.tvWinLose.setTextColor(ContextCompat.getColor(this, R.color.green));
            binding.tvWinLose.setText(getResources().getString(R.string.VictoryTitle));
            binding.tvMessage.setText(getResources().getString(R.string.VictoryMessage) + " " + binding.getGame().getnTries());

        }
        else {
            binding.tvWinLose.setTextColor(ContextCompat.getColor(this, R.color.red));
            binding.tvWinLose.setText(getResources().getString(R.string.LoseTitle));
            binding.tvMessage.setText(getResources().getString(R.string.LoseMessage) + " " + binding.getGame().getnToGuess());
        }
    }

    private void PlayAgain() {
        Intent intent = new Intent(this, ConfigActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }

    /**
     * Este método es el que se llama cuando se pulsa sobre la vista konfettiView definido en el XML,
     * en caso de que el usuario haya ganado, en el que se hace uso de la librería konfetti.
     *
     * @see nl.dionsegijn.konfetti
     */
    public void explode() {
        Random r = new Random();

        EmitterConfig emitterConfig = new Emitter(100L, TimeUnit.MILLISECONDS).max(100);
        binding.konfettiView.start(
                new PartyFactory(emitterConfig)
                        .spread(360)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, new Shape.DrawableShape(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart), true)))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 30f)
                        .position(new Position.Relative(r.nextDouble(), r.nextDouble()))
                        .build()
        );
    }
}