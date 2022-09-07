package service;

import model.CloudInstance;

public class CloudInstanceCreator {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.instance.numberofinstances";
    public static final String TESTDATA_INSTANCE_TYPE = "testdata.instance.type";
    public static final String TESTDATA_INSTANCE_LOCATION = "testdata.instance.location";

    public CloudInstance fourE2Standard8Frankfurt() {
        return new CloudInstance(
                TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(TESTDATA_INSTANCE_TYPE),
                TestDataReader.getTestData(TESTDATA_INSTANCE_LOCATION));
    }
}
