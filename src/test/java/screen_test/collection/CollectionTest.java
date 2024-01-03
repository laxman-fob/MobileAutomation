package screen_test.collection;

import factory.Pillar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen_finder.collection.Collection;

public class CollectionTest {

    Collection collection;
    Pillar pillar;
    @BeforeMethod
    public void beforeMethod() {
        pillar = new Pillar();
        pillar.init_TestData("collectionTestData.json");
        collection = new Collection();

    }

    @Test(priority='1')
    public void openCollectionPage() throws InterruptedException {
        collection.openCollectionPageAsFtu();

    }

    @Test(priority='2')
    public void addPlaceToCollection() throws InterruptedException {
        collection.createCollectionNameAsFtu();
        collection.addPlaceToCreatedCollection();
        collection.closeCollectionCancelButtonLastScreen();
        Assert.assertEquals(collection.collectionNameAndPlace(),"VisitEstonia2024\nPlaces: 1");
        collection.closeCollectionCancelButtonSecondLastScreen();

    }




}
