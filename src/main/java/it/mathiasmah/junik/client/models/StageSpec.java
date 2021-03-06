package it.mathiasmah.junik.client.models;

/**
 * A Pojo that holds the specification of an image
 *
 * @see Image
 */
public class StageSpec {
    private ImageFormat imageFormat;
    private XenVirtualizationType xenVirtualizationType;

    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.imageFormat = imageFormat;
    }

    public XenVirtualizationType getXenVirtualizationType() {
        return xenVirtualizationType;
    }

    public void setXenVirtualizationType(XenVirtualizationType xenVirtualizationType) {
        this.xenVirtualizationType = xenVirtualizationType;
    }

    @Override
    public String toString() {
        return "StageSpec{" +
                "imageFormat=" + imageFormat +
                ", xenVirtualizationType=" + xenVirtualizationType +
                '}';
    }
}
