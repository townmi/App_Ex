package com.goubaa.harry.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.goubaa.harry.banner.transformer.AccordionTransformer;
import com.goubaa.harry.banner.transformer.BackgroundToForegroundTransformer;
import com.goubaa.harry.banner.transformer.CubeInTransformer;
import com.goubaa.harry.banner.transformer.CubeOutTransformer;
import com.goubaa.harry.banner.transformer.DefaultTransformer;
import com.goubaa.harry.banner.transformer.DepthPageTransformer;
import com.goubaa.harry.banner.transformer.FlipHorizontalTransformer;
import com.goubaa.harry.banner.transformer.FlipVerticalTransformer;
import com.goubaa.harry.banner.transformer.ForegroundToBackgroundTransformer;
import com.goubaa.harry.banner.transformer.RotateDownTransformer;
import com.goubaa.harry.banner.transformer.RotateUpTransformer;
import com.goubaa.harry.banner.transformer.ScaleInOutTransformer;
import com.goubaa.harry.banner.transformer.StackTransformer;
import com.goubaa.harry.banner.transformer.TabletTransformer;
import com.goubaa.harry.banner.transformer.ZoomInTransformer;
import com.goubaa.harry.banner.transformer.ZoomOutSlideTransformer;
import com.goubaa.harry.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
