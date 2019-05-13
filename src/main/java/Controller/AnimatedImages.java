package Controller;

import javafx.scene.image.Image;
/**
 * Handles the background animation.
 * */
public class AnimatedImages {

    public Image[] frames;
    public double duration;

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }



}
