package in.sonraj.progressstatusbar;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

public class ProgressStatusBar extends AndroidNonvisibleComponent {

  private final Activity activity;
  private final ProgressStatusBarClass mProgressStatusBar;
  private int backgroundColor;
  private int progressColor;

  public ProgressStatusBar(ComponentContainer container) {
    super(container.$form());
    Context context = container.$context();
    activity=container.$context();
    mProgressStatusBar=new ProgressStatusBarClass(context);
  }

  @SimpleProperty(description = "Set the background color of the Progress.")
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_NONE)
  public void ProgressBackgroundColor(int color) {
    mProgressStatusBar.setProgressBackgroundColor(color);
    this.backgroundColor = color;
  }
  @SimpleProperty
  public int ProgressBackgroundColor() {
    return backgroundColor;
  }

  @SimpleProperty(description = "Set color of the Progress.")
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = DEFAULT_VALUE_COLOR_DEFAULT)
  public void ProgressColor(int color) {
    mProgressStatusBar.setProgressColor(color);
    this.progressColor = color;
  }
  @SimpleProperty
  public int ProgressColor() {
    return progressColor;
  }

  @SimpleFunction()
  public void StartFakeProgress(int duration){
    forProgress();
    mProgressStatusBar.startFakeProgress(duration);
  }

  @SimpleFunction()
  public void SetProgress(int progressValue){
    forProgress();
    mProgressStatusBar.setProgress(progressValue);
  }

  private void forProgress(){

    mProgressStatusBar.setProgressListener(new ProgressStatusBarClass.OnProgressListener() {

      public void onStart() {
        activity.runOnUiThread(() -> ProgressStart());
      }

      public void onUpdate(int progress) {
        activity.runOnUiThread(() -> ProgressChanged(progress));
      }

      public void onEnd() {
        activity.runOnUiThread(() -> ProgressEnd());
      }
    });
  }

  @SimpleEvent()
  public void ProgressStart(){
    EventDispatcher.dispatchEvent(this, "ProgressStart");
  }

  @SimpleEvent()
  public void ProgressChanged(int progress){
    EventDispatcher.dispatchEvent(this, "ProgressChanged",progress);
  }

  @SimpleEvent()
  public void ProgressEnd(){
    EventDispatcher.dispatchEvent(this, "ProgressEnd");
  }

}
