package Threads;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    //starts the background Thread
    @Override
    protected String doInBackground(Void... voids) {
        Log.e("asynctask", "doInBackground");
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 2000;

        // Sleep for the random amount of time
        try {
            publishProgress(s);
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    //executed after doInBackground is finished
    @Override
    protected void onPostExecute(String result) {
        Log.e("asynctask", "onPostExecute");
        mTextView.get().setText(result);
        //super.onPostExecute(s);
    }

    //executed while doInBackground is running and runs method "publishProgress(s)"
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.e("asynctask", "onProgressUpdate");
        mTextView.get().setText("im sleeping now for " + values[0] + " milliseconds");
    }
}
