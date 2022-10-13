package com.example.guessnumber.iu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;

import com.example.guessnumber.R;
import com.example.guessnumber.databinding.ActivityEndGameBinding;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

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
            binding.tvWinLose.setText("VICTORIA");
            binding.tvMessage.setText("Has acertado en: " + binding.getGame().getnTries() + " intentos");

        }
        else {
            binding.tvWinLose.setTextColor(ContextCompat.getColor(this, R.color.red));
            binding.tvWinLose.setText("DERROTA");
            binding.tvMessage.setText("El número era: " + binding.getGame().getnToGuess());
        }
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