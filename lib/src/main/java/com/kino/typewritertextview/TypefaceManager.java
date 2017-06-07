package com.kino.typewritertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;

import com.kino.lib.R;

/**
 * @author：kino on 06/06/2017 15:36
 * @qq：724499620
 */

public class TypefaceManager {


    private static TypefaceManager instance;

    private SparseArray<Typeface> cacheTypeface = new SparseArray<>();

    public static TypefaceManager getInstance() {
        if (instance == null) {
            synchronized (TypefaceManager.class) {
                if (instance == null) {
                    instance = new TypefaceManager();
                }
            }
        }
        return instance;
    }

    public static class FontType {

        public static final int FONT_NOMAL = 0;
        public static final int FONT_BOLD = 1;
        public static final int FONT_LOBSTER = 2;
        public static final int FONT_FUTURE = 3;

        private static final String FONT_NOMAL_PATH = "fonts/FZLanTingHeiS-L-GB-Regular.TTF";
        private static final String FONT_BOLD_PATH = "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF";
        private static final String FONT_LOBSTER_PATH = "fonts/Lobster-1.4.otf";
        private static final String FONT_FUTURE_PATH = "fonts/Futura-CondensedMedium.TTF";


        private int fontType = -1;
        private String asssetsFontPath;

        private FontType(int fontType, String asssetsFontPath) {
            this.fontType = fontType;
            this.asssetsFontPath = asssetsFontPath;
        }

        public static FontType getFontTypeByName(int fontType) {
            switch (fontType) {
                case FONT_LOBSTER:
                    return new FontType(fontType, FONT_LOBSTER_PATH);
                case FONT_BOLD:
                    return new FontType(fontType, FONT_BOLD_PATH);
                case FONT_NOMAL:
                    return new FontType(fontType, FONT_NOMAL_PATH);
                case FONT_FUTURE:
                    return new FontType(fontType, FONT_FUTURE_PATH);
            }
            return null;
        }

    }

    public void initTextView(Context context, AttributeSet attributeSet, FontTextView textView) {
        TypedArray a = null;
        try {
            a = context.obtainStyledAttributes(attributeSet, R.styleable.FontTypeface, 0, 0);
            int fontType = a.getInt(R.styleable.FontTypeface_fontName, FontType.FONT_NOMAL);
            applyFontType(textView, FontType.getFontTypeByName(fontType));
        } finally {
            if (a != null) a.recycle();
        }


    }

    public void applyFontType(FontTextView textView, FontType fontType) {
        if (textView == null || fontType == null || fontType.fontType == -1 || TextUtils.isEmpty(fontType.asssetsFontPath))
            return;
        Typeface typeface = cacheTypeface.get(fontType.fontType);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(textView.getContext().getAssets(), fontType.asssetsFontPath);
            cacheTypeface.put(fontType.fontType, typeface);
        }
        textView.setTypeface(typeface);
    }

    public void clearCache() {
        cacheTypeface.clear();
    }

}
