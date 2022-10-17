package logic;

import data.ParentChildPair;

import java.util.List;

public interface DataProvider {

    void refreshDataSource();
    void refreshDataSource(String dataAddress);
    List<ParentChildPair> getParentChildPairsList();
}
