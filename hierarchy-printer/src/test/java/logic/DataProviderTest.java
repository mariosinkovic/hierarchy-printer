package logic;

import data.ParentChildPair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class DataProviderTest {

    public DataProvider dataProvider;

    public DataProviderTest(DataProvider dataProvider){
        this.dataProvider = dataProvider;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        Object[] implementationsArray = new Object[]{
                new DefaultDataProvider()
        };
        List<Object[]> implementationsArrayList = new ArrayList<>(1);
        implementationsArrayList.add(implementationsArray);
        return implementationsArrayList;
    }

    /**
     * @return List<ParentChildPair> with valid "ParentChildPairsList" for
     * resource "TestDataValid.txt"
     */
    public List<ParentChildPair> getValidParentChildPairsListOfTestDataValid(){
        return Arrays.asList(new ParentChildPair[]{
                new ParentChildPair("Ivan", "Adam"),
                new ParentChildPair("Stjepan", "Marko"),
                new ParentChildPair("Adam", "Stjepan"),
                new ParentChildPair("Stjepan", "Robert"),
                new ParentChildPair("Ivan", "Fran"),
                new ParentChildPair("Luka", "Leopold"),
                new ParentChildPair("Leopold", "Marko")
                }
        );
    }

    /**
     * @return List<ParentChildPair> with valid "ParentChildPairsList" for
     * resource "TestDataInvalid.txt"
     */
    public List<ParentChildPair> getValidParentChildPairsListOfTestDataInvalid(){
        return Arrays.asList(new ParentChildPair[]{
                        new ParentChildPair("Ivan", "Adam"),
                        new ParentChildPair("Stjepan", "Marko"),
                        new ParentChildPair("Adam", "Stjepan"),
                        new ParentChildPair("Stjepan", "Robert"),
                        new ParentChildPair("Robert", "Ivan"),
                        new ParentChildPair("Ivan", "Fran"),
                        new ParentChildPair("Luka", "Leopold"),
                        new ParentChildPair("Leopold", "Marko")
                }
        );
    }

    @Test
    public void refreshDataSourceTest(){
        List<ParentChildPair> getList = dataProvider.getParentChildPairsList();
        dataProvider.refreshDataSource(System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataInvalid.txt");
        getList = dataProvider.getParentChildPairsList();
        assertArrayEquals(getValidParentChildPairsListOfTestDataInvalid().toArray(), getList.toArray());
    }

    @Test
    public void getParentChildPairsListTest(){
        dataProvider.refreshDataSource();List<ParentChildPair> getList = dataProvider.getParentChildPairsList();
        assertArrayEquals(getValidParentChildPairsListOfTestDataValid().toArray(), getList.toArray());
    }
}
