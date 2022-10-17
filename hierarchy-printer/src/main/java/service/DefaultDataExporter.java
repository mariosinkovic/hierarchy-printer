package service;

import data.NodeExtension;
import logic.CyclicRelationshipException;
import logic.DataProcessor;

public class DefaultDataExporter implements DataExporter{

    private static final String DEFAULT_RECURSION_DEPT_LEADING_STRING = "    ";
    private StringBuilder stringBuilderExport;
    private DataProcessor dataProcessor;

    public DefaultDataExporter(DataProcessor dataProcessor){
        this.dataProcessor = dataProcessor;
        this.stringBuilderExport = new StringBuilder();
    }

    private void generateNodeExtensionExportRowRecursively(NodeExtension nodeExtension, String leadingString){
        stringBuilderExport.append(System.getProperty("line.separator") + leadingString + nodeExtension.getParent());
        for (NodeExtension node : nodeExtension.getNodeExtensions()){
            generateNodeExtensionExportRowRecursively(node, leadingString + DEFAULT_RECURSION_DEPT_LEADING_STRING);
        }
    }

    @Override
    public String generateNodeExtensionExportHierarchy(){
        // we are not printing root element, in that case wee need to print each one of his child
        try {
            for (NodeExtension node : dataProcessor.getNodeExtensionRoot().getNodeExtensions()){
                generateNodeExtensionExportRowRecursively(node, "");
            }
        } catch (CyclicRelationshipException e) {
            e.printStackTrace();
        }
        return stringBuilderExport.toString().trim();
    }

    @Override
    public DataProcessor getDataProcessor() {
        return dataProcessor;
    }
}
