package Controller;

import javafx.scene.image.Image;
/**
 * Handles the background animation.
 * */
public class AnimatedImages {


    /**
     * Gets the images for the animation.
     * */
    public Image[] frames;

    /**
     * Gets the duration of the animation.
     * */
    public double duration;

    /**
     * Uses the images and their duration, to make a visibly moving background.
     * @return frame[index] gives back the animated images.
     * @param time is calculated in {@link GameController}
     * */
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }



}
