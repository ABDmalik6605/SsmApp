package cn.pedant.SweetAlert;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class OptAnimationLoader {
    public static Animation loadAnimation(Context context, int id2) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                animation = context.getResources().getAnimation(id2);
                return createAnimationFromXml(context, animation);
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id2));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id2));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    private static Animation createAnimationFromXml(Context c, XmlPullParser parser) throws XmlPullParserException, IOException {
        return createAnimationFromXml(c, parser, null, Xml.asAttributeSet(parser));
    }

    private static Animation createAnimationFromXml(Context c, XmlPullParser parser, AnimationSet parent, AttributeSet attrs) throws XmlPullParserException, IOException {
        int depth = parser.getDepth();
        Animation translateAnimation = null;
        while (true) {
            int next = parser.next();
            if ((next == 3 && parser.getDepth() <= depth) || next == 1) {
                break;
            }
            if (next == 2) {
                String name = parser.getName();
                if (name.equals("set")) {
                    translateAnimation = new AnimationSet(c, attrs);
                    createAnimationFromXml(c, parser, (AnimationSet) translateAnimation, attrs);
                } else if (name.equals("alpha")) {
                    translateAnimation = new AlphaAnimation(c, attrs);
                } else if (name.equals("scale")) {
                    translateAnimation = new ScaleAnimation(c, attrs);
                } else if (name.equals("rotate")) {
                    translateAnimation = new RotateAnimation(c, attrs);
                } else if (name.equals("translate")) {
                    translateAnimation = new TranslateAnimation(c, attrs);
                } else {
                    try {
                        translateAnimation = (Animation) Class.forName(name).getConstructor(Context.class, AttributeSet.class).newInstance(c, attrs);
                    } catch (Exception e) {
                        throw new RuntimeException("Unknown animation name: " + parser.getName() + " error:" + e.getMessage());
                    }
                }
                if (parent != null) {
                    parent.addAnimation(translateAnimation);
                }
            }
        }
        return translateAnimation;
    }
}
