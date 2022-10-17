package service;

import logic.DataProcessor;

public interface DataExporter {

    String generateNodeExtensionExportHierarchy();

    DataProcessor getDataProcessor();
}
