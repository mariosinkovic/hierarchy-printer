import logic.DataProcessor;
import logic.DataProvider;
import logic.DefaultDataProcessor;
import logic.DefaultDataProvider;
import service.DataExporter;
import service.DefaultDataExporter;

public class Main {

    public static void main(String[] args){
//        DataProvider dataProvider = new DefaultDataProvider(System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataInvalid.txt");
        DataProvider dataProvider = new DefaultDataProvider();
        DataProcessor dataProcessor = new DefaultDataProcessor(dataProvider);
        DataExporter dataExporter = new DefaultDataExporter(dataProcessor);
        String result = dataExporter.generateNodeExtensionExportHierarchy();
        System.out.println(result);
    }
}
