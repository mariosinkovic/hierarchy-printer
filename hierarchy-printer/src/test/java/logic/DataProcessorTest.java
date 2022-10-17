package logic;

import data.NodeExtension;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class DataProcessorTest {

    private DataProcessor dataProcessor;
    private String serializedValidNodeExtensionRoot = "rO0ABXNyABJkYXRhLk5vZGVFeHRlbnNpb24Fz9AzcAhCOQIAAlsADm5vZGVFe" +
            "HRlbnNpb25zdAAVW0xkYXRhL05vZGVFeHRlbnNpb247TAAGcGFyZW50dAASTGphdmEvbGFuZy9TdHJpbmc7eHB1cgAVW0xkYXRhLk5v" +
            "ZGVFeHRlbnNpb2470o/4X745nykCAAB4cAAAAAJzcQB+AAB1cQB+AAQAAAACc3EAfgAAdXEAfgAEAAAAAXNxAH4AAHVxAH4ABAAAAAJ" +
            "zcQB+AAB1cQB+AAQAAAAAdAAFTWFya29zcQB+AAB1cQB+AAQAAAAAdAAGUm9iZXJ0dAAHU3RqZXBhbnQABEFkYW1zcQB+AAB1cQB+AA" +
            "QAAAAAdAAERnJhbnQABEl2YW5zcQB+AAB1cQB+AAQAAAABc3EAfgAAdXEAfgAEAAAAAXNxAH4AAHVxAH4ABAAAAAB0AAVNYXJrb3QAB" +
            "0xlb3BvbGR0AARMdWthdAAEUm9vdA==";

    public DataProcessorTest(DataProcessor dataProcessor){
        this.dataProcessor = dataProcessor;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        Object[] implementationsArray = new Object[]{
                new DefaultDataProcessor(new DefaultDataProvider())
        };
        List<Object[]> implementationsArrayList = new ArrayList<>(1);
        implementationsArrayList.add(implementationsArray);
        return implementationsArrayList;
    }

    // Todo -> make reading from resource, use SerializedDataValidNodeExtensionRoot.txt
    public NodeExtension getValidNodeExtensionRoot() throws IOException, ClassNotFoundException {
        byte[] dataArr = Base64.getDecoder().decode(this.serializedValidNodeExtensionRoot);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(dataArr));
        NodeExtension nodeExtension = (NodeExtension) ois.readObject();
        ois.close();
        return nodeExtension;
    }

    @Test
    public void getNodeExtensionRootTest() throws IOException, ClassNotFoundException, CyclicRelationshipException {
        dataProcessor.getNodeExtensionRoot();
        assertEquals(getValidNodeExtensionRoot(), dataProcessor.getNodeExtensionRoot());
    }

    @Test(expected = CyclicRelationshipException.class)
    public void getNodeExtensionRootExceptionTest() throws IOException, ClassNotFoundException, CyclicRelationshipException {
        dataProcessor.getDataProvider().refreshDataSource(System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataInvalid.txt");
        dataProcessor.getNodeExtensionRoot();
        assertEquals(getValidNodeExtensionRoot(), dataProcessor.getNodeExtensionRoot());
    }

//    // TOOL method, used for serialization
//    @Test
//    public void serializeValidNodeExtensionRoot() throws IOException {
//        // SerializedDataValidNodeExtensionRoot.txt
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream( baos );
//        oos.writeObject( dataProcessor.getNodeExtensionRoot() );
//        oos.close();
//        System.out.println(Base64.getEncoder().encodeToString(baos.toByteArray()));
//    }
}
