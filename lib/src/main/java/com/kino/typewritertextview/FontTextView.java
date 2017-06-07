package com.kino.typewritertextview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class FontTextView
        extends AppCompatTextView {
    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FontTextView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        if (!isInEditMode()) {
            TypefaceManager.getInstance().initTextView(context, attributeSet, this);
        }
    }

    public void setFontType(TypefaceManager.FontType fontType) {
        if (!isInEditMode()) {
            TypefaceManager.getInstance().applyFontType(this, fontType);
        }
    }

    public void setFontType(int fontName) {
        if (!isInEditMode()) {
            TypefaceManager.FontType fontType = TypefaceManager.FontType.getFontTypeByName(fontName);
            if (fontType != null) {
                TypefaceManager.getInstance().applyFontType(this, fontType);
            }
        }
    }
}