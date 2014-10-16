package com.example.andriy.myapplication2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
    Button mUiButtonNotification;
    private ImageView mImageView;
    private ArrayList<String> mQuotesList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image_view);
        TextView mTextView = (TextView) findViewById(R.id.text_view);

        mQuotesList.add("Don't worry");
        mQuotesList.add("Be happy");
        mQuotesList.add("Old age has deformities enough of its own. It should never add to them the deformity of vice.");


        mUiButtonNotification = (Button) findViewById(R.id.main_button_notification);

        mUiButtonNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendNotification("Author test", getRandomQuote());
            }
        });

        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();

        myTimer.schedule(myTask, 20000, 10000);//
        int x = 9;
    }

    class MyTimerTask extends TimerTask {
        public void run() {
            sendNotification("Author test", getRandomQuote());
        }
    }

    private String getRandomQuote() {
        int size = mQuotesList.size();
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomQuoteId = rand.nextInt((size - 1 - 0) + 1) + 0;
        return mQuotesList.get(randomQuoteId);
    }

    private void sendNotification(String title, String content) {
        Log.d("MY_TAG", "sendNotification 01");

        int notification_id = 001;

        // Phone App 과 연결 부분
        Intent viewIntent = new Intent(this, MainActivity.class);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Icon 이 없으니 생성 안됨.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher).setContentIntent(viewPendingIntent)
                .setContentTitle(title).setContentText(content);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notification_id, notificationBuilder.build());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
