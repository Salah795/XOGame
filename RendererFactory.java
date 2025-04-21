public class RendererFactory {
    public RendererFactory() {}

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
