package com.karome.homework9;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] nameOfSongArray = new String[] {"Samurai - Never Fade Away", "Samurai - Chippin in"};
    int[] songArray = new int[] {R.raw.samurai_never_fade_away, R.raw.samurai_chippin_in};

    private int NumberOfSong; // = 0

    private Button PlayButton;
    private Button PreviousButton;
    private Button NextButton;
    private Button StopButton;
    private TextView SongName;

    private MediaPlayer player;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == 24) {
            player.setVolume(1.0f,1.0f);
        } else if (keyCode == 25) {
            player.setVolume(0.5f,0.5f);
        }

        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PlayButton = findViewById(R.id.buttonPlay);
        PreviousButton = findViewById(R.id.buttonPrevious);
        NextButton = findViewById(R.id.buttonNext);
        StopButton = findViewById(R.id.buttonStop);
        SongName = findViewById(R.id.songName);

        StopButton.setOnClickListener(listener -> {
            player.stop();
        });

        PlayButton.setOnClickListener(listener -> {

            if (player != null && player.isPlaying()) player.release(); // && == and
            // if (NumberOfSong == 0 || NumberOfSong == 1) PreviousButton.setEnabled(false); // || == or

            playSong();
        });

        PreviousButton.setOnClickListener(listener -> {
            /*
            NextButton.setEnabled(true);
            if (NumberOfSong == 0 || NumberOfSong == 1) PreviousButton.setEnabled(false);
             */

            player.release();
            NumberOfSong--;
            playSong();
        });

        NextButton.setOnClickListener(listener -> {
            /*
            if (NumberOfSong == 1) NextButton.setEnabled(false);
            PreviousButton.setEnabled(true);
             */
            player.release();
            NumberOfSong++;
            playSong();
        });
    }

    private void playSong() {
        player = MediaPlayer.create(this, songArray[NumberOfSong]);
        player.start();

        SongName.setText(nameOfSongArray[NumberOfSong]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
        player = null;
    }
}