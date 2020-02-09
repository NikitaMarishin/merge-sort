import analyzer.Analyzer;
import analyzer.AnalyzerImp;


public class Main {
    public static void main(String[] args) {
        Analyzer analyzer = new AnalyzerImp();
        if (analyzer.analyze(args)) {
            analyzer.initialize();
        }
    }
}
