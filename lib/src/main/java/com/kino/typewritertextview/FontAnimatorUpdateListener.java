package com.kino.typewritertextview;

import android.animation.ValueAnimator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

public class FontAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    private FontTypeWriterTextView textView;

    public FontAnimatorUpdateListener(FontTypeWriterTextView textView) {
        this.textView = textView;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int curValue = ((Integer) animation.getAnimatedValue()).intValue();
        if (curValue > textView.showIndex) {
            if (curValue > 0 && curValue <= textView.charSequence.length()) {
                SpannableString spannableString = new SpannableString(textView.charSequence.toString());
                spannableString.setSpan(new ForegroundColorSpan(0), curValue - 1, textView.charSequence.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setTextApplyBufferType(spannableString);
            }
            textView.showIndex = curValue;
        }
    }
}