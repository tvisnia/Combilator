package utils;

import android.support.design.widget.FloatingActionButton;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by tomek on 21.09.15.
 */
public class AnimationUtils {
    private static final long ANIMATION_DURATION = 800;

    public static void animateButton(FloatingActionButton button, boolean success) {
        Techniques technique = (success ? Techniques.RotateIn : Techniques.Wobble);
        YoYo.with(technique)
                .duration(ANIMATION_DURATION)
                .playOn(button);
    }
}
