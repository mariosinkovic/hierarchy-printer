package logic;

import data.ParentChildPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DefaultDataProvider implements DataProvider {

    private String dataAddress;
    private String dataRowSeparator;
    private String dataInformationSeparator;
    private List<ParentChildPair> pairList;

    public DefaultDataProvider(){
        this(System.getProperty("user.dir") + "\\src\\main\\resources\\Test.txt");
    }
    public DefaultDataProvider(String dataAddress){
        this(dataAddress, "\n", " ");
    }
    public DefaultDataProvider(String dataAddress, String dataRowSeparator, String dataInformationSeparator){
        this.dataAddress = dataAddress;
        this.dataRowSeparator = dataRowSeparator;
        this.dataInformationSeparator = dataInformationSeparator;
    }

    @Override
    public void refreshDataSource(){
        this.refreshDataSource(System.getProperty("user.dir") + "\\src\\main\\resources\\Test.txt");
    }

    @Override
    public void refreshDataSource(String dataAddress){
        this.dataAddress = dataAddress;
        this.pairList = null; // drop old processed data
    }

    @Override
    public List<ParentChildPair> getParentChildPairsList() {
        if(pairList!=null) { return pairList; }

        // new BufferedInputStream(new FileInputStream(fileName)) can be more efficient method, but this is good enough
        try (BufferedReader br = new BufferedReader(new FileReader(dataAddress))) {
            List<ParentChildPair> pairs = new LinkedList(); // internal variable to prevent memory leaks if reader snaps
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                String [] pair = strCurrentLine.split(dataInformationSeparator);
                pairs.add(new ParentChildPair(pair[1], pair[0]));
            }
            pairList = pairs;
            return pairList;
        } catch (IOException e) {
            e.printStackTrace(); // no throwing there, just print and return empty list
            return new LinkedList();
        }
    }
}
