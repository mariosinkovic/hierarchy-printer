package logic;

import data.NodeExtension;
import data.ParentChildPair;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultDataProcessor implements DataProcessor{

    private DataProvider dataProvider;
    private List<ParentChildPair> dataProviderPairList; // stored for case of refreshed data source
    private NodeExtension nodeExtensionRoot;

    public DefaultDataProcessor(DataProvider dataProvider){
        this.dataProvider = dataProvider;
    }

    private boolean processNeeded(){
        return dataProviderPairList == null // if object is created and getter is not yet called
                || nodeExtensionRoot == null // if in some unhandled case this is null
                || dataProviderPairList != dataProvider.getParentChildPairsList(); // if source data has changed
    }

    private void validate() throws CyclicRelationshipException{
        for (ParentChildPair pair : this.dataProviderPairList) {
            List<String> elementAndParentsList = new LinkedList<>();
            elementAndParentsList.add(pair.getParent());
            checkCyclicRelationsRecursively(pair.getParent(), elementAndParentsList);
        }
    }

    private void checkCyclicRelationsRecursively(String element, List<String> elementAndParentsList) throws CyclicRelationshipException{
        // children of element
        List<ParentChildPair> childrenOfElement = this.dataProviderPairList
                .stream()
                .filter(x -> x.getParent().equals(element))
                .collect(Collectors.toList());
        for (ParentChildPair pair : childrenOfElement){
            if (elementAndParentsList.contains(pair.getChild())){
                throw new CyclicRelationshipException(element, elementAndParentsList, pair);
            }
            elementAndParentsList.add(pair.getChild());
            checkCyclicRelationsRecursively(pair.getChild(), elementAndParentsList);
        }
    }

    // pop out when used can be more beneficial for large datastructures if collection is linkedList
    private void generateNodeExtensionRoot() throws CyclicRelationshipException{
        // fetch new data and validate it
        this.dataProviderPairList = dataProvider.getParentChildPairsList();
        this.validate();
        // take all parents and filter those who are not someone's child
        List<String> parentZeroList = dataProviderPairList.stream().map(ParentChildPair::getParent).distinct().collect(Collectors.toList());
        List<String> parentThatAreChildList = new LinkedList<>();
        for (String parent : parentZeroList){
            if(dataProviderPairList.stream().anyMatch(x -> x.getChild().equals(parent))){
                parentThatAreChildList.add(parent);
            }
        }
        parentZeroList.removeAll(parentThatAreChildList);
        // make root node extension and populate it with node extensions of parentZeroList
        NodeExtension nodeRoot = new NodeExtension("Root", new NodeExtension[parentZeroList.size()]);
        for (int cnt = 0; cnt < parentZeroList.size(); cnt++){
            parentZeroList.get(cnt);
            NodeExtension nodeCnt = new NodeExtension(parentZeroList.get(cnt), null);
            nodeRoot.getNodeExtensions()[cnt] = nodeCnt;
        }
        // set children of each child in nodeRoot
        for (NodeExtension nodeExtension : nodeRoot.getNodeExtensions()){
            populateChildNodeExtensions(nodeExtension);
        }
        this.nodeExtensionRoot = nodeRoot;
    }

    private void populateChildNodeExtensions(NodeExtension nodeExtension){
        // get list of children for given nodeExtension
        List<String> childList = dataProviderPairList
                .stream()
                .filter(x -> x.getParent().equals(nodeExtension.getParent()))
                .map(ParentChildPair::getChild).collect(Collectors.toList());
        // make new array for given nodeExtension
        nodeExtension.setNodeExtensions(new NodeExtension[childList.size()]);
        // for every child in childList make NodeExtension of it and put it into corresponding place in given nodeExtension
        for (int cnt = 0; cnt < childList.size(); cnt++){
            NodeExtension nodeCnt = new NodeExtension(childList.get(cnt), null);
            nodeExtension.getNodeExtensions()[cnt] = nodeCnt;
        }
        // set child node extensions for each node
        for (NodeExtension nodeExt : nodeExtension.getNodeExtensions()){
            populateChildNodeExtensions(nodeExt);
        }
    }

    @Override
    public DataProvider getDataProvider() {
        return dataProvider;
    }

    @Override
    public NodeExtension getNodeExtensionRoot() throws CyclicRelationshipException{
        if (processNeeded()){
            this.generateNodeExtensionRoot();
        }
        return this.nodeExtensionRoot;
    }

    @Override
    public String toString() {
        return "logic.DefaultDataProcessor{" +
                "nodeExtensionRoot=" + nodeExtensionRoot +
                '}';
    }
}

