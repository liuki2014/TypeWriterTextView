package com.kino.typewritertextview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

public class FontTypeWriterTextView extends FontTextView {
    private long duration = 600L;
    public int showIndex;
    private BufferType type;
    public CharSequence charSequence;
    private ValueAnimator textAnim;

    public FontTypeWriterTextView(Context context) {
        this(context, null, 0);
    }

    public FontTypeWriterTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FontTypeWriterTextView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    private void cancelAnim() {
        if (textAnim != null) {
            textAnim.removeAllListeners();
            textAnim.cancel();
            textAnim = null;
        }
        if (!TextUtils.isEmpty(charSequence) && type != null) {
            super.setText(charSequence, type);
        }
    }

    public final void startAnim() {
        if (TextUtils.isEmpty(charSequence)) {
            cancelAnim();
            return;
        }
        this.showIndex = -1;
        textAnim.addUpdateListener(new FontAnimatorUpdateListener(this));
        textAnim.addListener(new FontAnimatorListener(this));
        textAnim.start();
    }

    public final void setTextApplyBufferType(CharSequence text) {
        if (type != null && type == BufferType.NORMAL)
            super.setText(text, type);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        cancelAnim();
        if (TextUtils.isEmpty(text)) {
            this.charSequence = "";
            super.setText(text, type);
            return;
        }
        this.charSequence = text;
        this.type = type;
        textAnim = ValueAnimator.ofInt(new int[]{0, text.length() + 1});
        textAnim.setDuration(duration);
        super.setText("", type);
    }
}