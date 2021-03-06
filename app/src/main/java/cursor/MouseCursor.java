package cursor;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Choreographer;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.libopenmw.openmw.R;

import org.libsdl.app.SDLActivity;

import ui.activity.GameActivity;

public class MouseCursor implements Choreographer.FrameCallback {

    private Choreographer choreographer;
    private ImageView cursor;

    public MouseCursor(GameActivity activity) {
        cursor = new ImageView(activity);
        cursor.setImageResource(R.drawable.pointer_arrow);
        Resources r = activity.getResources();
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
        cursor.setLayoutParams(new RelativeLayout.LayoutParams((int) Math.round(px / 1.5), px));

        RelativeLayout layout = activity.getLayout();
        layout.addView(cursor);

        choreographer = Choreographer.getInstance();
        choreographer.postFrameCallback(this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (SDLActivity.isMouseShown() == 0) {
            cursor.setVisibility(View.GONE);
        } else {
            cursor.setVisibility(View.VISIBLE);

            int mouseX = SDLActivity.getMouseX();
            int mouseY = SDLActivity.getMouseY();

            cursor.setX(mouseX);
            cursor.setY(mouseY);
        }

        choreographer.postFrameCallback(this);
    }
}
