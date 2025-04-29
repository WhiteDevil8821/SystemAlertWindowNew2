package in.jvapps.system_alert_window;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import in.jvapps.system_alert_window.utils.LogUtils;
import java.util.Objects;
import in.jvapps.system_alert_window.utils.Constants;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.android.FlutterTextureView;



public class BubbleActivity extends AppCompatActivity {

    private Context mContext;

    private final String TAG = "SAW:Plugin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        mContext = this;
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            configureUI();
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            String flutterEngineId = null;
            if (paramsMap.containsKey("flutter_engine_id")) {
                Object id = paramsMap.get("flutter_engine_id");
                if (id instanceof String) {
                    flutterEngineId = (String) id;
                }
            }
            String engineId = flutterEngineId != null ? flutterEngineId : Constants.FLUTTER_CACHE_ENGINE;
            FlutterEngine engine = FlutterEngineCache.getInstance().get(engineId);
            if (engine == null) {
                throw new IllegalStateException("FlutterEngine not available");
            }
            engine.getLifecycleChannel().appIsResumed();
        } catch (Exception ex) {
            LogUtils.getInstance().e(TAG,"onResume " +  ex.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            String flutterEngineId = null;
            if (paramsMap.containsKey("flutter_engine_id")) {
                Object id = paramsMap.get("flutter_engine_id");
                if (id instanceof String) {
                    flutterEngineId = (String) id;
                }
            }
            String engineId = flutterEngineId != null ? flutterEngineId : Constants.FLUTTER_CACHE_ENGINE;
            FlutterEngine engine = FlutterEngineCache.getInstance().get(engineId);
            if (engine == null) {
                throw new IllegalStateException("FlutterEngine not available");
            }
            engine.getLifecycleChannel().appIsInactive();
        }
        catch (Exception ex){
            LogUtils.getInstance().e(TAG, "onPause " + ex.getMessage());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{
            String flutterEngineId = null;
            if (paramsMap.containsKey("flutter_engine_id")) {
                Object id = paramsMap.get("flutter_engine_id");
                if (id instanceof String) {
                    flutterEngineId = (String) id;
                }
            }
            String engineId = flutterEngineId != null ? flutterEngineId : Constants.FLUTTER_CACHE_ENGINE;
            FlutterEngine engine = FlutterEngineCache.getInstance().get(engineId);
            if (engine == null) {
                throw new IllegalStateException("FlutterEngine not available");
            }
            engine.getLifecycleChannel().appIsPaused();
        }
        catch (Exception ex){
            LogUtils.getInstance().e(TAG, "onStop " + ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            String flutterEngineId = null;
            if (paramsMap.containsKey("flutter_engine_id")) {
                Object id = paramsMap.get("flutter_engine_id");
                if (id instanceof String) {
                    flutterEngineId = (String) id;
                }
            }
            String engineId = flutterEngineId != null ? flutterEngineId : Constants.FLUTTER_CACHE_ENGINE;
            FlutterEngine engine = FlutterEngineCache.getInstance().get(engineId);
            if (engine == null) {
                throw new IllegalStateException("FlutterEngine not available");
            }
            engine.getLifecycleChannel().appIsDetached();
        }
        catch (Exception ex){
            LogUtils.getInstance().e(TAG,  "onDestroy " + ex.getMessage());
        }
    }

    void configureUI(){
        try{
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.VERTICAL); // Set the orientation if needed
            linearLayout.setBackgroundColor(Color.WHITE);
            String flutterEngineId = null;
            if (paramsMap.containsKey("flutter_engine_id")) {
                Object id = paramsMap.get("flutter_engine_id");
                if (id instanceof String) {
                    flutterEngineId = (String) id;
                }
            }
            String engineId = flutterEngineId != null ? flutterEngineId : Constants.FLUTTER_CACHE_ENGINE;
            FlutterEngine engine = FlutterEngineCache.getInstance().get(engineId);
            if (engine == null) {
                throw new IllegalStateException("FlutterEngine not available");
            }
            engine.getLifecycleChannel().appIsResumed();
            FlutterView flutterView = new FlutterView(getApplicationContext(), new FlutterTextureView(getApplicationContext()));
            flutterView.attachToFlutterEngine(Objects.requireNonNull(FlutterEngineCache.getInstance().get(Constants.FLUTTER_CACHE_ENGINE)));
            flutterView.setFitsSystemWindows(true);
            flutterView.setFocusable(true);
            flutterView.setFocusableInTouchMode(true);
            flutterView.setBackgroundColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            flutterView.setLayoutParams(params);
            linearLayout.addView(flutterView);
            setContentView(linearLayout);
        }
        catch (Exception ex){
            LogUtils.getInstance().e(TAG, "configureUi " + ex.getMessage());
        }
    }
}
