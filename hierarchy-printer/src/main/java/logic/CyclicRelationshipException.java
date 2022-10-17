package logic;

import data.ParentChildPair;

import java.util.List;
import java.util.stream.Collectors;

public class CyclicRelationshipException extends Exception {

    /**
     *
     * @param element
     * @param elementAndParentsList -> List must be sorted from grandparent to child
     * @param invalidPair
     */
    public CyclicRelationshipException(String element, List<String> elementAndParentsList, ParentChildPair invalidPair){
        super(generateErrorMessage(element, elementAndParentsList, invalidPair));
    }

    private static String generateErrorMessage(String element, List<String> elementAndParentsList, ParentChildPair invalidPair){
        return "CyclicRelationshipException: child can not be parent of its own parent: "
                + System.getProperty("line.separator")
                + "parent: " + element + System.getProperty("line.separator")
                + "child: " + invalidPair.getChild() + System.getProperty("line.separator")
                + "relationship cycle: "
                + elementAndParentsList.stream().collect(Collectors.joining(" -> "))
                + " -> " + invalidPair.getChild();
    }
}
