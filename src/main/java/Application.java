import analyzer.Analyzer;


public class Application {
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();
        if (analyzer.analyze(args)) {
            analyzer.initialize();
        }
    }
}
