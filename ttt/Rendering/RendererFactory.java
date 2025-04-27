package ttt.Rendering;

/**
 * Class for defining the renderer factory which helps to
 * decide the renderer that should be created for the current game
 * based on the received string.
 * It's implementing the Single Responsibility Principle (DIP).
 *
 * @author Salah Mahmied.
 */
public class RendererFactory {
    /**
     * The default constructor for the ttt.Rendering.RendererFactory.
     */
    public RendererFactory() {}

    /**
     * This method will create the chosen renderer for the current game.
     * @param type The string that represents the type of the chosen renderer.
     * @param size The game board size.
     * @return The ttt.Rendering.Renderer object that have been created based on the type parameter if the
     * type is legal, else it will return null.
     */
    public Renderer buildRenderer(String type, int size) {
        switch (type) {
            case "void":
                return new VoidRenderer();
            case "console":
                return new ConsoleRenderer(size);
            default:
                return null;
        }
    }
}
