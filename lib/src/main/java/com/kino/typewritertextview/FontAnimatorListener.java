package com.kino.typewritertextview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/**
 * @author：kino on 06/06/2017 15:14
 * @qq：724499620
 */

public class FontAnimatorListener extends AnimatorListenerAdapter {
    private FontTypeWriterTextView textView;

    public FontAnimatorListener(FontTypeWriterTextView textView) {
        this.textView = textView;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        textView.setTextApplyBufferType(textView.charSequence);

    }
}
