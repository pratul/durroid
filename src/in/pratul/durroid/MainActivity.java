package in.pratul.durroid;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends Activity {

    private SharedPreferences mSharedPrefs;
    private AlarmManager mAlarmManager;

    private static final int START_TIME_IN_SECONDS = 10;
    private static final int REPEAT_TIME_IN_MILLIS = 5 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (mSharedPrefs.contains("should_durr")) {
            Calendar updateTime = Calendar.getInstance();
            updateTime.set(Calendar.SECOND, START_TIME_IN_SECONDS);
            Intent alarmIntent = new Intent(this, AlarmService.class);
            PendingIntent pi = PendingIntent.getService(this, 0, alarmIntent, 0);
            mAlarmManager.cancel(pi);
            mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, updateTime.getTimeInMillis(), REPEAT_TIME_IN_MILLIS, pi);

            Button b = (Button) findViewById(R.id.button);
            b.setText(R.string.tap_to_turn_off);
        }

    }

    public void onToggleButtonClick(View view) {
        Button button = (Button) view;
        if (mSharedPrefs.contains("should_durr")) {
            button.setText(R.string.tap_to_turn_on);
            Intent alarmIntent = new Intent(this, AlarmService.class);
            PendingIntent pi = PendingIntent.getService(this, 0, alarmIntent, 0);
            mAlarmManager.cancel(pi);

            mSharedPrefs.edit().remove("should_durr").commit();

        } else {
            button.setText(R.string.tap_to_turn_off);
            Calendar updateTime = Calendar.getInstance();
            updateTime.set(Calendar.SECOND, START_TIME_IN_SECONDS);
            Intent alarmIntent = new Intent(this, AlarmService.class);
            PendingIntent pi = PendingIntent.getService(this, 0, alarmIntent, 0);
            mAlarmManager.cancel(pi);
            mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, updateTime.getTimeInMillis(), REPEAT_TIME_IN_MILLIS, pi);

            mSharedPrefs.edit().putBoolean("should_durr", true).commit();
        }
    }
}
