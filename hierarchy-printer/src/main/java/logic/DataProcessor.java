package logic;

import data.NodeExtension;

public interface DataProcessor {

    DataProvider getDataProvider();

    NodeExtension getNodeExtensionRoot() throws CyclicRelationshipException;

}
