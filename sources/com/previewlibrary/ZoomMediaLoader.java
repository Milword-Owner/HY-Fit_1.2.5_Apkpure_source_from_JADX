package com.previewlibrary;

import com.previewlibrary.loader.IZoomMediaLoader;

public class ZoomMediaLoader {
    private volatile IZoomMediaLoader loader;

    public static ZoomMediaLoader getInstance() {
        return Holder.holder;
    }

    private ZoomMediaLoader() {
    }

    private static class Holder {
        static ZoomMediaLoader holder = new ZoomMediaLoader();

        private Holder() {
        }
    }

    public void init(IZoomMediaLoader iZoomMediaLoader) {
        this.loader = iZoomMediaLoader;
    }

    public IZoomMediaLoader getLoader() {
        if (this.loader != null) {
            return this.loader;
        }
        throw new NullPointerException("ZoomMediaLoader loader  no init");
    }
}
