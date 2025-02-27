package gh2;
import deque.*;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque61B<Double> buffer;
    public int size;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new LinkedListDeque61B<>();
        size = (int) Math.round(SR / frequency);
        for (int i = 0; i < size; i++) {
            buffer.addLast(0.0);
        }

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        buffer = new LinkedListDeque61B<>();
        while (buffer.size() < size) {
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        double first = buffer.removeFirst();
        double second = buffer.get(0);
        double newSample = DECAY * 0.5 * (first + second);
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.get(0);
    }

    public static void main(String[] args) {
        GuitarString gs = new GuitarString(440.0);
        gs.pluck();
        for (int i = 0; i < 100; i++) {
            System.out.println(gs.sample());
            gs.tic();
        }
    }
}
    // TODO: Remove all comments that say TODO when you're done.
