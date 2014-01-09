package in.pratul.durroid;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;


public class AlarmService extends IntentService {


    public AlarmService() {
        super("AlarmReceiver");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 40, 80, 40};
        v.vibrate(pattern, -1);
    }
}
