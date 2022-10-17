package service;

import logic.DefaultDataProcessor;
import logic.DefaultDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class DataExporterTest {
    private String dataExporterValidData = "Ivan" + System.getProperty("line.separator") +
            "    Adam" + System.getProperty("line.separator") +
            "        Stjepan" + System.getProperty("line.separator") +
            "            Marko" + System.getProperty("line.separator") +
            "            Robert" + System.getProperty("line.separator") +
            "    Fran" + System.getProperty("line.separator") +
            "Luka" + System.getProperty("line.separator") +
            "    Leopold" + System.getProperty("line.separator") +
            "        Marko";
    private DataExporter dataExporter;

    public DataExporterTest(DataExporter dataExporter){
        this.dataExporter = dataExporter;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        Object[] implementationsArray = new Object[]{
                new DefaultDataExporter(new DefaultDataProcessor(new DefaultDataProvider()))
        };
        List<Object[]> implementationsArrayList = new ArrayList<>(1);
        implementationsArrayList.add(implementationsArray);
        return implementationsArrayList;
    }

    @Test
    public void generateNodeExtensionExportHierarchyTest(){
        assertEquals(this.dataExporterValidData, dataExporter.generateNodeExtensionExportHierarchy());
    }
}

