import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortingVisualizer extends JPanel {
    private int[] array;
    private static final int ARRAY_SIZE = 100;
    private static final int BAR_WIDTH = 6;

    public SortingVisualizer() {
        array = new int[ARRAY_SIZE];
        generateRandomArray();
    }

    private void generateRandomArray() {
        Random rand = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = rand.nextInt(300) + 10;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (int i = 0; i < array.length; i++) {
            g.fillRect(i * BAR_WIDTH, getHeight() - array[i], BAR_WIDTH, array[i]);
        }
    }

    public void bubbleSort() {
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        repaint();
                        sleep(10);
                    }
                }
            }
        }).start();
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        SortingVisualizer visualizer = new SortingVisualizer();
        frame.add(visualizer);
        frame.setSize(ARRAY_SIZE * BAR_WIDTH, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        visualizer.bubbleSort();
    }
}
