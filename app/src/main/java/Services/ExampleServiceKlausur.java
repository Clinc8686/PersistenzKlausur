package Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

public class ExampleServiceKlausur extends Service {
    // declaring object of MediaPlayer
    private MediaPlayer player;

    //muss implementiert sein
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Wird jedes mal aufgerufen, wenn der Service mit startService(intent) gestartet wird
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );
        player.setLooping( true );
        player.start();

        // returns the status
        // of the program
        return START_STICKY;
    }

    //Service wird zerstört
    @Override
    // execution of the service will
    // stop on calling this method
    public void onDestroy() {
        super.onDestroy();

        // stopping the process
        player.stop();
    }
}