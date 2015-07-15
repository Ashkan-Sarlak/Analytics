package net.sarlak.ashkan.analytics;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by ashkan on 7/14/15.
 */
public class Analytics {

    private static final String LOG_TAG = Analytics.class.getSimpleName();

    private static GoogleAnalytics mAnalytics;
    private static Tracker mTracker;

    public static void init(Context context, String trackingId) {
        mAnalytics = GoogleAnalytics.getInstance(context);
        mAnalytics.setLocalDispatchPeriod(1800);
//        analytics.setDryRun(true);
        mTracker = mAnalytics.newTracker(trackingId);
        mTracker.enableExceptionReporting(true);
        mTracker.enableAdvertisingIdCollection(true);
        mTracker.enableAutoActivityTracking(true);
    }

    public static void send(String category, String action, String label, long value) {
        try {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory(category)
                    .setAction(action)
                    .setLabel(label)
                    .setValue(value)
                    .build());
        } catch (NullPointerException npe) {
            Log.e(LOG_TAG, "You must call Analytics.init(trackingId) first.");
        }
    }

    public static void sendScreenView(String screenName) {
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

}
