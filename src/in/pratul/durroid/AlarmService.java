package in.pratul.durroid;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import com.commonsware.cwac.wakeful.WakefulIntentService;



public class AlarmService extends WakefulIntentService {
    public AlarmService() {
        super("AlarmReceiver");
    }

    @Override
    protected void doWakefulWork(Intent intent) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = { 0, 40, 80, 40 };
        v.vibrate(pattern, -1);
    }
}
